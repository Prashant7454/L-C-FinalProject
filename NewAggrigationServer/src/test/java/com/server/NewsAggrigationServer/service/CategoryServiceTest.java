package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.model.Category;
import com.server.NewsAggrigationServer.repository.CategoryRepository;
import com.server.NewsAggrigationServer.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category testCategory;
    private CategoryDTO testCategoryDTO;

    @BeforeEach
    void setUp() {
        testCategory = new Category();
        testCategory.setId(1);
        testCategory.setName("Technology");
        testCategory.setIsHide(0);

        testCategoryDTO = new CategoryDTO();
        testCategoryDTO.setName("Technology");
        testCategoryDTO.setIsHide(0);
    }

    @Test
    void testCreateCategory_Success() {
        // Arrange
        when(categoryRepository.existsByName("Technology")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(testCategoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testCategory.getId(), result.getId());
        assertEquals(testCategoryDTO.getName(), result.getName());
        
        verify(categoryRepository).existsByName("Technology");
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testCreateCategory_CategoryAlreadyExists() {
        // Arrange
        when(categoryRepository.existsByName("Technology")).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryService.createCategory(testCategoryDTO);
        });

        verify(categoryRepository).existsByName("Technology");
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void testCreateCategory_WithNullName() {
        // Arrange
        testCategoryDTO.setName(null);
        when(categoryRepository.existsByName(null)).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(testCategoryDTO);

        // Assert
        assertNotNull(result);
        verify(categoryRepository).existsByName(null);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testCreateCategory_WithEmptyName() {
        // Arrange
        testCategoryDTO.setName("");
        when(categoryRepository.existsByName("")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(testCategoryDTO);

        // Assert
        assertNotNull(result);
        verify(categoryRepository).existsByName("");
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testGetCategoryById_Success() {
        // Arrange
        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));

        // Act
        CategoryDTO result = categoryService.getCategoryById(1);

        // Assert
        assertNotNull(result);
        assertEquals(testCategory.getId(), result.getId());
        assertEquals(testCategory.getName(), result.getName());
        assertEquals(testCategory.getIsHide(), result.getIsHide());
        
        verify(categoryRepository).findById(1);
    }

    @Test
    void testGetCategoryById_NotFound() {
        // Arrange
        when(categoryRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(999);
        });

        verify(categoryRepository).findById(999);
    }

    @Test
    void testGetAllCategories_Success() {
        // Arrange
        Category category1 = new Category("Technology");
        category1.setId(1);
        category1.setIsHide(0);

        Category category2 = new Category("Science");
        category2.setId(2);
        category2.setIsHide(0);

        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(category1.getId(), result.get(0).getId());
        assertEquals(category1.getName(), result.get(0).getName());
        assertEquals(category2.getId(), result.get(1).getId());
        assertEquals(category2.getName(), result.get(1).getName());
        
        verify(categoryRepository).findAll();
    }

    @Test
    void testGetAllCategories_EmptyList() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        
        verify(categoryRepository).findAll();
    }

    @Test
    void testGetAllCategoriesByIds_Success() {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        Category category1 = new Category("Technology");
        category1.setId(1);
        category1.setIsHide(0);

        Category category2 = new Category("Science");
        category2.setId(2);
        category2.setIsHide(0);

        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryRepository.findByIdIn(categoryIds)).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategoriesByIds(categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(category1.getId(), result.get(0).getId());
        assertEquals(category2.getId(), result.get(1).getId());
        
        verify(categoryRepository).findByIdIn(categoryIds);
    }

    @Test
    void testGetAllCategoriesByIds_EmptyList() {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        when(categoryRepository.findByIdIn(categoryIds)).thenReturn(Arrays.asList());

        // Act
        List<CategoryDTO> result = categoryService.getAllCategoriesByIds(categoryIds);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        
        verify(categoryRepository).findByIdIn(categoryIds);
    }

    @Test
    void testGetAllVisibleCategories_Success() {
        // Arrange
        Category category1 = new Category("Technology");
        category1.setId(1);
        category1.setIsHide(0);

        Category category2 = new Category("Science");
        category2.setId(2);
        category2.setIsHide(0);

        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryRepository.findByIsHide(0)).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getAllVisibleCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(0, result.get(0).getIsHide());
        assertEquals(0, result.get(1).getIsHide());
        
        verify(categoryRepository).findByIsHide(0);
    }

    @Test
    void testGetVisibleCategoriesByIds_Success() {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        Category category1 = new Category("Technology");
        category1.setId(1);
        category1.setIsHide(0);

        Category category2 = new Category("Science");
        category2.setId(2);
        category2.setIsHide(0);

        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryRepository.findByIdInAndIsHide(categoryIds, 0)).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getVisibleCategoriesByIds(categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(0, result.get(0).getIsHide());
        assertEquals(0, result.get(1).getIsHide());
        
        verify(categoryRepository).findByIdInAndIsHide(categoryIds, 0);
    }

    @Test
    void testHideCategory_Success() {
        // Arrange
        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.hideCategory(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIsHide());
        
        verify(categoryRepository).findById(1);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testHideCategory_NotFound() {
        // Arrange
        when(categoryRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryService.hideCategory(999);
        });

        verify(categoryRepository).findById(999);
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void testUnhideCategory_Success() {
        // Arrange
        testCategory.setIsHide(1);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.unhideCategory(1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getIsHide());
        
        verify(categoryRepository).findById(1);
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testUnhideCategory_NotFound() {
        // Arrange
        when(categoryRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryService.unhideCategory(999);
        });

        verify(categoryRepository).findById(999);
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void testCreateCategory_WithSpecialCharacters() {
        // Arrange
        testCategoryDTO.setName("Tech@Science#2023");
        when(categoryRepository.existsByName("Tech@Science#2023")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(testCategoryDTO);

        // Assert
        assertNotNull(result);
        verify(categoryRepository).existsByName("Tech@Science#2023");
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testCreateCategory_WithUnicodeCharacters() {
        // Arrange
        testCategoryDTO.setName("Technology with émojis 🚀");
        when(categoryRepository.existsByName("Technology with émojis 🚀")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(testCategoryDTO);

        // Assert
        assertNotNull(result);
        verify(categoryRepository).existsByName("Technology with émojis 🚀");
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void testCreateCategory_WithLongName() {
        // Arrange
        String longName = "This is a very long category name that contains many characters and should be properly handled";
        testCategoryDTO.setName(longName);
        when(categoryRepository.existsByName(longName)).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(testCategoryDTO);

        // Assert
        assertNotNull(result);
        verify(categoryRepository).existsByName(longName);
        verify(categoryRepository).save(any(Category.class));
    }
} 