package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsCategoryDTO;
import com.server.NewsAggrigationServer.service.NewsCategoryService;
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
class NewsCategoryControllerTest {

    @Mock
    private NewsCategoryService newsCategoryService;

    @InjectMocks
    private NewsCategoryController newsCategoryController;

    private NewsCategoryDTO newsCategoryDTO1;
    private NewsCategoryDTO newsCategoryDTO2;
    private List<NewsCategoryDTO> newsCategoryList;

    @BeforeEach
    void setUp() {
        newsCategoryDTO1 = new NewsCategoryDTO();
        newsCategoryDTO1.setId(1);
        newsCategoryDTO1.setNewsId(1);
        newsCategoryDTO1.setCategoryId(1);

        newsCategoryDTO2 = new NewsCategoryDTO();
        newsCategoryDTO2.setId(2);
        newsCategoryDTO2.setNewsId(1);
        newsCategoryDTO2.setCategoryId(2);

        newsCategoryList = Arrays.asList(newsCategoryDTO1, newsCategoryDTO2);
    }

    @Test
    void testGetNewsCategoriesByNewsId() {
        // Arrange
        when(newsCategoryService.getNewsCategoriesByNewsId(1)).thenReturn(newsCategoryList);

        // Act
        List<NewsCategoryDTO> result = newsCategoryController.getNewsCategoriesByNewsId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(0).getNewsId());
        assertEquals(1, result.get(1).getNewsId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(2, result.get(1).getCategoryId());
        verify(newsCategoryService).getNewsCategoriesByNewsId(1);
    }

    @Test
    void testGetNewsCategoriesByNewsIdWithZeroNewsId() {
        // Arrange
        when(newsCategoryService.getNewsCategoriesByNewsId(0)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.getNewsCategoriesByNewsId(0);
        });
        verify(newsCategoryService).getNewsCategoriesByNewsId(0);
    }

    @Test
    void testGetNewsCategoriesByNewsIdWithNegativeNewsId() {
        // Arrange
        when(newsCategoryService.getNewsCategoriesByNewsId(-1)).thenThrow(new IllegalArgumentException("Invalid news ID"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.getNewsCategoriesByNewsId(-1);
        });
        verify(newsCategoryService).getNewsCategoriesByNewsId(-1);
    }

    @Test
    void testGetNewsCategoriesByNewsIdWithServiceException() {
        // Arrange
        when(newsCategoryService.getNewsCategoriesByNewsId(1)).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsCategoryController.getNewsCategoriesByNewsId(1);
        });
        verify(newsCategoryService).getNewsCategoriesByNewsId(1);
    }

    @Test
    void testGetNewsCategoriesByNewsIdWithEmptyList() {
        // Arrange
        when(newsCategoryService.getNewsCategoriesByNewsId(1)).thenReturn(Collections.emptyList());

        // Act
        List<NewsCategoryDTO> result = newsCategoryController.getNewsCategoriesByNewsId(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsCategoryService).getNewsCategoriesByNewsId(1);
    }

    @Test
    void testAssignCategoryToNews() {
        // Arrange
        when(newsCategoryService.assignCategoryToNews(any(NewsCategoryDTO.class))).thenReturn(newsCategoryDTO1);

        // Act
        NewsCategoryDTO result = newsCategoryController.assignCategoryToNews(newsCategoryDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getNewsId());
        assertEquals(1, result.getCategoryId());
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testAssignCategoryToNewsWithNullInput() {
        // Arrange
        when(newsCategoryService.assignCategoryToNews(null)).thenThrow(new IllegalArgumentException("News category cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.assignCategoryToNews(null);
        });
        verify(newsCategoryService).assignCategoryToNews(null);
    }

    @Test
    void testAssignCategoryToNewsWithNullNewsId() {
        // Arrange
        newsCategoryDTO1.setNewsId(null);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenThrow(new IllegalArgumentException("News ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.assignCategoryToNews(newsCategoryDTO1);
        });
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testAssignCategoryToNewsWithNullCategoryId() {
        // Arrange
        newsCategoryDTO1.setCategoryId(null);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenThrow(new IllegalArgumentException("Category ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.assignCategoryToNews(newsCategoryDTO1);
        });
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testAssignCategoryToNewsWithServiceException() {
        // Arrange
        when(newsCategoryService.assignCategoryToNews(any(NewsCategoryDTO.class))).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsCategoryController.assignCategoryToNews(newsCategoryDTO1);
        });
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testRemoveCategoryFromNews() {
        // Arrange
        doNothing().when(newsCategoryService).removeCategoryFromNews(1, 1);

        // Act
        String result = newsCategoryController.removeCategoryFromNews(1, 1);

        // Assert
        assertNotNull(result);
        assertEquals("Category removed from news successfully", result);
        verify(newsCategoryService).removeCategoryFromNews(1, 1);
    }

    @Test
    void testRemoveCategoryFromNewsWithZeroNewsId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid news ID")).when(newsCategoryService).removeCategoryFromNews(0, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.removeCategoryFromNews(0, 1);
        });
        verify(newsCategoryService).removeCategoryFromNews(0, 1);
    }

    @Test
    void testRemoveCategoryFromNewsWithZeroCategoryId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid category ID")).when(newsCategoryService).removeCategoryFromNews(1, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.removeCategoryFromNews(1, 0);
        });
        verify(newsCategoryService).removeCategoryFromNews(1, 0);
    }

    @Test
    void testRemoveCategoryFromNewsWithNegativeNewsId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid news ID")).when(newsCategoryService).removeCategoryFromNews(-1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.removeCategoryFromNews(-1, 1);
        });
        verify(newsCategoryService).removeCategoryFromNews(-1, 1);
    }

    @Test
    void testRemoveCategoryFromNewsWithNegativeCategoryId() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid category ID")).when(newsCategoryService).removeCategoryFromNews(1, -1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.removeCategoryFromNews(1, -1);
        });
        verify(newsCategoryService).removeCategoryFromNews(1, -1);
    }

    @Test
    void testRemoveCategoryFromNewsWithServiceException() {
        // Arrange
        doThrow(new RuntimeException("Service error")).when(newsCategoryService).removeCategoryFromNews(1, 1);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            newsCategoryController.removeCategoryFromNews(1, 1);
        });
        verify(newsCategoryService).removeCategoryFromNews(1, 1);
    }

    @Test
    void testGetNewsCategoriesByNewsIdWithLargeNewsId() {
        // Arrange
        when(newsCategoryService.getNewsCategoriesByNewsId(Integer.MAX_VALUE)).thenReturn(newsCategoryList);

        // Act
        List<NewsCategoryDTO> result = newsCategoryController.getNewsCategoriesByNewsId(Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsCategoryService).getNewsCategoriesByNewsId(Integer.MAX_VALUE);
    }

    @Test
    void testAssignCategoryToNewsWithZeroValues() {
        // Arrange
        newsCategoryDTO1.setNewsId(0);
        newsCategoryDTO1.setCategoryId(0);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.assignCategoryToNews(newsCategoryDTO1);
        });
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testAssignCategoryToNewsWithNegativeValues() {
        // Arrange
        newsCategoryDTO1.setNewsId(-1);
        newsCategoryDTO1.setCategoryId(-1);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenThrow(new IllegalArgumentException("Invalid IDs"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.assignCategoryToNews(newsCategoryDTO1);
        });
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testAssignCategoryToNewsWithLargeValues() {
        // Arrange
        newsCategoryDTO1.setNewsId(Integer.MAX_VALUE);
        newsCategoryDTO1.setCategoryId(Integer.MAX_VALUE);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenReturn(newsCategoryDTO1);

        // Act
        NewsCategoryDTO result = newsCategoryController.assignCategoryToNews(newsCategoryDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getNewsId());
        assertEquals(Integer.MAX_VALUE, result.getCategoryId());
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testRemoveCategoryFromNewsWithLargeValues() {
        // Arrange
        doNothing().when(newsCategoryService).removeCategoryFromNews(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Act
        String result = newsCategoryController.removeCategoryFromNews(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        assertNotNull(result);
        assertEquals("Category removed from news successfully", result);
        verify(newsCategoryService).removeCategoryFromNews(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    void testAssignCategoryToNewsWithZeroId() {
        // Arrange
        newsCategoryDTO1.setId(0);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenReturn(newsCategoryDTO1);

        // Act
        NewsCategoryDTO result = newsCategoryController.assignCategoryToNews(newsCategoryDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getId());
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testAssignCategoryToNewsWithNegativeId() {
        // Arrange
        newsCategoryDTO1.setId(-1);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenReturn(newsCategoryDTO1);

        // Act
        NewsCategoryDTO result = newsCategoryController.assignCategoryToNews(newsCategoryDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(-1, result.getId());
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testAssignCategoryToNewsWithLargeId() {
        // Arrange
        newsCategoryDTO1.setId(Integer.MAX_VALUE);
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenReturn(newsCategoryDTO1);

        // Act
        NewsCategoryDTO result = newsCategoryController.assignCategoryToNews(newsCategoryDTO1);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.MAX_VALUE, result.getId());
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testGetNewsCategoriesByNewsIdWithLargeList() {
        // Arrange
        List<NewsCategoryDTO> largeList = Arrays.asList(
            newsCategoryDTO1, newsCategoryDTO2, newsCategoryDTO1, newsCategoryDTO2, newsCategoryDTO1,
            newsCategoryDTO2, newsCategoryDTO1, newsCategoryDTO2, newsCategoryDTO1, newsCategoryDTO2
        );
        when(newsCategoryService.getNewsCategoriesByNewsId(1)).thenReturn(largeList);

        // Act
        List<NewsCategoryDTO> result = newsCategoryController.getNewsCategoriesByNewsId(1);

        // Assert
        assertNotNull(result);
        assertEquals(10, result.size());
        verify(newsCategoryService).getNewsCategoriesByNewsId(1);
    }

    @Test
    void testAssignCategoryToNewsWithDuplicateAssignment() {
        // Arrange
        when(newsCategoryService.assignCategoryToNews(newsCategoryDTO1)).thenThrow(new IllegalArgumentException("Category already assigned to this news"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.assignCategoryToNews(newsCategoryDTO1);
        });
        verify(newsCategoryService).assignCategoryToNews(newsCategoryDTO1);
    }

    @Test
    void testRemoveCategoryFromNewsWithNonExistentAssignment() {
        // Arrange
        doThrow(new IllegalArgumentException("Category assignment not found")).when(newsCategoryService).removeCategoryFromNews(1, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.removeCategoryFromNews(1, 1);
        });
        verify(newsCategoryService).removeCategoryFromNews(1, 1);
    }

    @Test
    void testRemoveCategoryFromNewsWithNullNewsId() {
        // Arrange
        doThrow(new IllegalArgumentException("News ID cannot be null")).when(newsCategoryService).removeCategoryFromNews(null, 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.removeCategoryFromNews(null, 1);
        });
        verify(newsCategoryService).removeCategoryFromNews(null, 1);
    }

    @Test
    void testRemoveCategoryFromNewsWithNullCategoryId() {
        // Arrange
        doThrow(new IllegalArgumentException("Category ID cannot be null")).when(newsCategoryService).removeCategoryFromNews(1, null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            newsCategoryController.removeCategoryFromNews(1, null);
        });
        verify(newsCategoryService).removeCategoryFromNews(1, null);
    }
} 