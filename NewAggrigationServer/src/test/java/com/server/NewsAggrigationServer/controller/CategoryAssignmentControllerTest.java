package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.service.CategoryAssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryAssignmentControllerTest {

    @Mock
    private CategoryAssignmentService categoryAssignmentService;

    @InjectMocks
    private CategoryAssignmentController categoryAssignmentController;

    private CategoryDTO categoryDTO1;
    private CategoryDTO categoryDTO2;
    private List<CategoryDTO> categoryList;

    @BeforeEach
    void setUp() {
        categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(1);
        categoryDTO1.setName("Technology");
        categoryDTO1.setDescription("Technology news");

        categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(2);
        categoryDTO2.setName("Sports");
        categoryDTO2.setDescription("Sports news");

        categoryList = Arrays.asList(categoryDTO1, categoryDTO2);
    }

    @Test
    void testAssignCategoriesToNews() {
        // Arrange
        Integer newsId = 1;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.assignCategoriesToNews(newsId, categoryIds)).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.assignCategoriesToNews(newsId, categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(categoryAssignmentService).assignCategoriesToNews(newsId, categoryIds);
    }

    @Test
    void testAssignCategoriesToNewsWithEmptyCategoryList() {
        // Arrange
        Integer newsId = 1;
        List<Integer> categoryIds = Collections.emptyList();
        when(categoryAssignmentService.assignCategoriesToNews(newsId, categoryIds)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.assignCategoriesToNews(newsId, categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(categoryAssignmentService).assignCategoriesToNews(newsId, categoryIds);
    }

    @Test
    void testAssignCategoriesToNewsWithNullNewsId() {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.assignCategoriesToNews(null, categoryIds)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.assignCategoriesToNews(null, categoryIds);
        });
        verify(categoryAssignmentService).assignCategoriesToNews(null, categoryIds);
    }

    @Test
    void testAssignCategoriesToNewsWithNullCategoryIds() {
        // Arrange
        Integer newsId = 1;
        when(categoryAssignmentService.assignCategoriesToNews(newsId, null)).thenThrow(new IllegalArgumentException("Category IDs cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.assignCategoriesToNews(newsId, null);
        });
        verify(categoryAssignmentService).assignCategoriesToNews(newsId, null);
    }

    @Test
    void testAssignCategoriesToNewsWithServiceException() {
        // Arrange
        Integer newsId = 1;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.assignCategoriesToNews(newsId, categoryIds)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryAssignmentController.assignCategoriesToNews(newsId, categoryIds);
        });
        verify(categoryAssignmentService).assignCategoriesToNews(newsId, categoryIds);
    }

    @Test
    void testGetCategoriesByNewsId() {
        // Arrange
        Integer newsId = 1;
        when(categoryAssignmentService.getCategoriesByNewsId(newsId)).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.getCategoriesByNewsId(newsId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(categoryAssignmentService).getCategoriesByNewsId(newsId);
    }

    @Test
    void testGetCategoriesByNewsIdWithEmptyResult() {
        // Arrange
        Integer newsId = 1;
        when(categoryAssignmentService.getCategoriesByNewsId(newsId)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.getCategoriesByNewsId(newsId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(categoryAssignmentService).getCategoriesByNewsId(newsId);
    }

    @Test
    void testGetCategoriesByNewsIdWithNullNewsId() {
        // Arrange
        when(categoryAssignmentService.getCategoriesByNewsId(null)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.getCategoriesByNewsId(null);
        });
        verify(categoryAssignmentService).getCategoriesByNewsId(null);
    }

    @Test
    void testGetCategoriesByNewsIdWithServiceException() {
        // Arrange
        Integer newsId = 1;
        when(categoryAssignmentService.getCategoriesByNewsId(newsId)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryAssignmentController.getCategoriesByNewsId(newsId);
        });
        verify(categoryAssignmentService).getCategoriesByNewsId(newsId);
    }

    @Test
    void testRemoveCategoriesFromNews() {
        // Arrange
        Integer newsId = 1;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.removeCategoriesFromNews(newsId, categoryIds)).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.removeCategoriesFromNews(newsId, categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        assertEquals(1, result.getBody().get(0).getId());
        assertEquals(2, result.getBody().get(1).getId());
        verify(categoryAssignmentService).removeCategoriesFromNews(newsId, categoryIds);
    }

    @Test
    void testRemoveCategoriesFromNewsWithEmptyCategoryList() {
        // Arrange
        Integer newsId = 1;
        List<Integer> categoryIds = Collections.emptyList();
        when(categoryAssignmentService.removeCategoriesFromNews(newsId, categoryIds)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.removeCategoriesFromNews(newsId, categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
        verify(categoryAssignmentService).removeCategoriesFromNews(newsId, categoryIds);
    }

    @Test
    void testRemoveCategoriesFromNewsWithNullNewsId() {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.removeCategoriesFromNews(null, categoryIds)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.removeCategoriesFromNews(null, categoryIds);
        });
        verify(categoryAssignmentService).removeCategoriesFromNews(null, categoryIds);
    }

    @Test
    void testRemoveCategoriesFromNewsWithServiceException() {
        // Arrange
        Integer newsId = 1;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.removeCategoriesFromNews(newsId, categoryIds)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryAssignmentController.removeCategoriesFromNews(newsId, categoryIds);
        });
        verify(categoryAssignmentService).removeCategoriesFromNews(newsId, categoryIds);
    }

    @Test
    void testAssignCategoriesToNewsWithZeroNewsId() {
        // Arrange
        Integer newsId = 0;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.assignCategoriesToNews(newsId, categoryIds)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.assignCategoriesToNews(newsId, categoryIds);
        });
        verify(categoryAssignmentService).assignCategoriesToNews(newsId, categoryIds);
    }

    @Test
    void testAssignCategoriesToNewsWithNegativeNewsId() {
        // Arrange
        Integer newsId = -1;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.assignCategoriesToNews(newsId, categoryIds)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.assignCategoriesToNews(newsId, categoryIds);
        });
        verify(categoryAssignmentService).assignCategoriesToNews(newsId, categoryIds);
    }

    @Test
    void testAssignCategoriesToNewsWithLargeNewsId() {
        // Arrange
        Integer newsId = Integer.MAX_VALUE;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.assignCategoriesToNews(newsId, categoryIds)).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.assignCategoriesToNews(newsId, categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(categoryAssignmentService).assignCategoriesToNews(newsId, categoryIds);
    }

    @Test
    void testGetCategoriesByNewsIdWithZeroNewsId() {
        // Arrange
        Integer newsId = 0;
        when(categoryAssignmentService.getCategoriesByNewsId(newsId)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.getCategoriesByNewsId(newsId);
        });
        verify(categoryAssignmentService).getCategoriesByNewsId(newsId);
    }

    @Test
    void testGetCategoriesByNewsIdWithNegativeNewsId() {
        // Arrange
        Integer newsId = -1;
        when(categoryAssignmentService.getCategoriesByNewsId(newsId)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.getCategoriesByNewsId(newsId);
        });
        verify(categoryAssignmentService).getCategoriesByNewsId(newsId);
    }

    @Test
    void testGetCategoriesByNewsIdWithLargeNewsId() {
        // Arrange
        Integer newsId = Integer.MAX_VALUE;
        when(categoryAssignmentService.getCategoriesByNewsId(newsId)).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.getCategoriesByNewsId(newsId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(categoryAssignmentService).getCategoriesByNewsId(newsId);
    }

    @Test
    void testRemoveCategoriesFromNewsWithZeroNewsId() {
        // Arrange
        Integer newsId = 0;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.removeCategoriesFromNews(newsId, categoryIds)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.removeCategoriesFromNews(newsId, categoryIds);
        });
        verify(categoryAssignmentService).removeCategoriesFromNews(newsId, categoryIds);
    }

    @Test
    void testRemoveCategoriesFromNewsWithNegativeNewsId() {
        // Arrange
        Integer newsId = -1;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.removeCategoriesFromNews(newsId, categoryIds)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryAssignmentController.removeCategoriesFromNews(newsId, categoryIds);
        });
        verify(categoryAssignmentService).removeCategoriesFromNews(newsId, categoryIds);
    }

    @Test
    void testRemoveCategoriesFromNewsWithLargeNewsId() {
        // Arrange
        Integer newsId = Integer.MAX_VALUE;
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryAssignmentService.removeCategoriesFromNews(newsId, categoryIds)).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryDTO>> result = categoryAssignmentController.removeCategoriesFromNews(newsId, categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(categoryAssignmentService).removeCategoriesFromNews(newsId, categoryIds);
    }
} 