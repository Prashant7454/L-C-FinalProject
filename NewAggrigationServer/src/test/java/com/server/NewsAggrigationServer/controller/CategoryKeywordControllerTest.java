package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.CategoryKeywordDTO;
import com.server.NewsAggrigationServer.service.CategoryKeywordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryKeywordControllerTest {

    @Mock
    private CategoryKeywordService categoryKeywordService;

    @InjectMocks
    private CategoryKeywordController categoryKeywordController;

    private CategoryKeywordDTO categoryKeywordDTO1;
    private CategoryKeywordDTO categoryKeywordDTO2;
    private List<CategoryKeywordDTO> categoryKeywordList;

    @BeforeEach
    void setUp() {
        categoryKeywordDTO1 = new CategoryKeywordDTO();
        categoryKeywordDTO1.setId(1);
        categoryKeywordDTO1.setCategoryId(1);
        categoryKeywordDTO1.setKeywordId(1);

        categoryKeywordDTO2 = new CategoryKeywordDTO();
        categoryKeywordDTO2.setId(2);
        categoryKeywordDTO2.setCategoryId(1);
        categoryKeywordDTO2.setKeywordId(2);

        categoryKeywordList = Arrays.asList(categoryKeywordDTO1, categoryKeywordDTO2);
    }

    @Test
    void testCreate() {
        // Arrange
        when(categoryKeywordService.createCategoryKeyword(any(CategoryKeywordDTO.class))).thenReturn(categoryKeywordDTO1);

        // Act
        CategoryKeywordDTO result = categoryKeywordController.create(categoryKeywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getCategoryId());
        assertEquals(1, result.getKeywordId());
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testCreateWithNullInput() {
        // Arrange
        when(categoryKeywordService.createCategoryKeyword(null)).thenThrow(new IllegalArgumentException("Category keyword cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.create(null);
        });
        verify(categoryKeywordService).createCategoryKeyword(null);
    }

    @Test
    void testCreateWithNullCategoryId() {
        // Arrange
        categoryKeywordDTO1.setCategoryId(null);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenThrow(new IllegalArgumentException("Category ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.create(categoryKeywordDTO1);
        });
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testCreateWithNullKeywordId() {
        // Arrange
        categoryKeywordDTO1.setKeywordId(null);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenThrow(new IllegalArgumentException("Keyword ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.create(categoryKeywordDTO1);
        });
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testCreateWithServiceException() {
        // Arrange
        when(categoryKeywordService.createCategoryKeyword(any(CategoryKeywordDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryKeywordController.create(categoryKeywordDTO1);
        });
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testGetAll() {
        // Arrange
        when(categoryKeywordService.getAllCategoryKeywords()).thenReturn(categoryKeywordList);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(1).getCategoryId());
        assertEquals(1, result.get(0).getKeywordId());
        assertEquals(2, result.get(1).getKeywordId());
        verify(categoryKeywordService).getAllCategoryKeywords();
    }

    @Test
    void testGetAllWithEmptyList() {
        // Arrange
        when(categoryKeywordService.getAllCategoryKeywords()).thenReturn(Collections.emptyList());

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryKeywordService).getAllCategoryKeywords();
    }

    @Test
    void testGetAllWithServiceException() {
        // Arrange
        when(categoryKeywordService.getAllCategoryKeywords()).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryKeywordController.getAll();
        });
        verify(categoryKeywordService).getAllCategoryKeywords();
    }

    @Test
    void testGetByCategoryId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(1)).thenReturn(categoryKeywordList);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getByCategoryId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(1).getCategoryId());
        verify(categoryKeywordService).getCategoryKeywordsByCategoryId(1);
    }

    @Test
    void testGetByCategoryIdWithEmptyList() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(1)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getByCategoryId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryKeywordService).getCategoryKeywordsByCategoryId(1);
    }

    @Test
    void testGetByCategoryIdWithNullCategoryId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(null)).thenThrow(new IllegalArgumentException("Category ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.getByCategoryId(null);
        });
        verify(categoryKeywordService).getCategoryKeywordsByCategoryId(null);
    }

    @Test
    void testGetByCategoryIdWithServiceException() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryKeywordController.getByCategoryId(1);
        });
        verify(categoryKeywordService).getCategoryKeywordsByCategoryId(1);
    }

    @Test
    void testGetByKeywordId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(1)).thenReturn(categoryKeywordList);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getByKeywordId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(0).getKeywordId());
        assertEquals(2, result.get(1).getKeywordId());
        verify(categoryKeywordService).getCategoryKeywordsByKeywordId(1);
    }

    @Test
    void testGetByKeywordIdWithEmptyList() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(1)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getByKeywordId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryKeywordService).getCategoryKeywordsByKeywordId(1);
    }

    @Test
    void testGetByKeywordIdWithNullKeywordId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(null)).thenThrow(new IllegalArgumentException("Keyword ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.getByKeywordId(null);
        });
        verify(categoryKeywordService).getCategoryKeywordsByKeywordId(null);
    }

    @Test
    void testGetByKeywordIdWithServiceException() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryKeywordController.getByKeywordId(1);
        });
        verify(categoryKeywordService).getCategoryKeywordsByKeywordId(1);
    }

    @Test
    void testCreateWithZeroValues() {
        // Arrange
        categoryKeywordDTO1.setCategoryId(0);
        categoryKeywordDTO1.setKeywordId(0);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.create(categoryKeywordDTO1);
        });
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testCreateWithNegativeValues() {
        // Arrange
        categoryKeywordDTO1.setCategoryId(-1);
        categoryKeywordDTO1.setKeywordId(-1);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.create(categoryKeywordDTO1);
        });
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testCreateWithLargeValues() {
        // Arrange
        categoryKeywordDTO1.setCategoryId(Integer.MAX_VALUE);
        categoryKeywordDTO1.setKeywordId(Integer.MAX_VALUE);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenReturn(categoryKeywordDTO1);

        // Act
        CategoryKeywordDTO result = categoryKeywordController.create(categoryKeywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getCategoryId());
        assertEquals(Integer.MAX_VALUE, result.getKeywordId());
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testGetByCategoryIdWithZeroCategoryId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(0)).thenThrow(new IllegalArgumentException("Invalid category ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.getByCategoryId(0);
        });
        verify(categoryKeywordService).getCategoryKeywordsByCategoryId(0);
    }

    @Test
    void testGetByCategoryIdWithNegativeCategoryId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(-1)).thenThrow(new IllegalArgumentException("Invalid category ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.getByCategoryId(-1);
        });
        verify(categoryKeywordService).getCategoryKeywordsByCategoryId(-1);
    }

    @Test
    void testGetByCategoryIdWithLargeCategoryId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(Integer.MAX_VALUE)).thenReturn(categoryKeywordList);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getByCategoryId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(categoryKeywordService).getCategoryKeywordsByCategoryId(Integer.MAX_VALUE);
    }

    @Test
    void testGetByKeywordIdWithZeroKeywordId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(0)).thenThrow(new IllegalArgumentException("Invalid keyword ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.getByKeywordId(0);
        });
        verify(categoryKeywordService).getCategoryKeywordsByKeywordId(0);
    }

    @Test
    void testGetByKeywordIdWithNegativeKeywordId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(-1)).thenThrow(new IllegalArgumentException("Invalid keyword ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordController.getByKeywordId(-1);
        });
        verify(categoryKeywordService).getCategoryKeywordsByKeywordId(-1);
    }

    @Test
    void testGetByKeywordIdWithLargeKeywordId() {
        // Arrange
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(Integer.MAX_VALUE)).thenReturn(categoryKeywordList);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getByKeywordId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(categoryKeywordService).getCategoryKeywordsByKeywordId(Integer.MAX_VALUE);
    }

    @Test
    void testCreateWithZeroId() {
        // Arrange
        categoryKeywordDTO1.setId(0);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenReturn(categoryKeywordDTO1);

        // Act
        CategoryKeywordDTO result = categoryKeywordController.create(categoryKeywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getId());
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testCreateWithNegativeId() {
        // Arrange
        categoryKeywordDTO1.setId(-1);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenReturn(categoryKeywordDTO1);

        // Act
        CategoryKeywordDTO result = categoryKeywordController.create(categoryKeywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(-1, result.getId());
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testCreateWithLargeId() {
        // Arrange
        categoryKeywordDTO1.setId(Integer.MAX_VALUE);
        when(categoryKeywordService.createCategoryKeyword(categoryKeywordDTO1)).thenReturn(categoryKeywordDTO1);

        // Act
        CategoryKeywordDTO result = categoryKeywordController.create(categoryKeywordDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getId());
        verify(categoryKeywordService).createCategoryKeyword(categoryKeywordDTO1);
    }

    @Test
    void testGetAllWithLargeList() {
        // Arrange
        List<CategoryKeywordDTO> largeList = Arrays.asList(
            categoryKeywordDTO1, categoryKeywordDTO2, categoryKeywordDTO1, categoryKeywordDTO2, categoryKeywordDTO1,
            categoryKeywordDTO2, categoryKeywordDTO1, categoryKeywordDTO2, categoryKeywordDTO1, categoryKeywordDTO2
        );
        when(categoryKeywordService.getAllCategoryKeywords()).thenReturn(largeList);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(10, result.size());
        verify(categoryKeywordService).getAllCategoryKeywords();
    }
} 