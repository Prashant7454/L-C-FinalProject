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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

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
        testCategoryDTO.setId(1);
        testCategoryDTO.setName("Technology");
        testCategoryDTO.setIsHide(0);
    }

    @Test
    void createCategory_Success() {
        // Arrange
        CategoryDTO inputDto = new CategoryDTO();
        inputDto.setName("Technology");

        when(categoryRepository.existsByName("Technology")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Technology", result.getName());

        verify(categoryRepository, times(1)).existsByName("Technology");
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_WithExistingName_ThrowsException() {
        // Arrange
        CategoryDTO inputDto = new CategoryDTO();
        inputDto.setName("Technology");

        when(categoryRepository.existsByName("Technology")).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryService.createCategory(inputDto);
        });

        assertEquals("Category already exists with name: Technology", exception.getMessage());
        verify(categoryRepository, times(1)).existsByName("Technology");
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void getCategoryById_Success() {
        // Arrange
        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));

        // Act
        CategoryDTO result = categoryService.getCategoryById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Technology", result.getName());
        assertEquals(0, result.getIsHide());

        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void getCategoryById_NotFound_ThrowsException() {
        // Arrange
        when(categoryRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(999);
        });

        assertEquals("Category not found with id: 999", exception.getMessage());
        verify(categoryRepository, times(1)).findById(999);
    }

    @Test
    void getAllCategories_Success() {
        // Arrange
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Technology");
        category1.setIsHide(0);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Sports");
        category2.setIsHide(1);

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Technology", result.get(0).getName());
        assertEquals(0, result.get(0).getIsHide());
        assertEquals(2, result.get(1).getId());
        assertEquals("Sports", result.get(1).getName());
        assertEquals(1, result.get(1).getIsHide());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getAllCategories_EmptyList() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getAllCategoriesByIds_Success() {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Technology");
        category1.setIsHide(0);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Sports");
        category2.setIsHide(1);

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findByIdIn(categoryIds)).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategoriesByIds(categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Technology", result.get(0).getName());
        assertEquals(2, result.get(1).getId());
        assertEquals("Sports", result.get(1).getName());

        verify(categoryRepository, times(1)).findByIdIn(categoryIds);
    }

    @Test
    void getAllVisibleCategories_Success() {
        // Arrange
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Technology");
        category1.setIsHide(0);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Sports");
        category2.setIsHide(0);

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findByIsHide(0)).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getAllVisibleCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Technology", result.get(0).getName());
        assertEquals(0, result.get(0).getIsHide());
        assertEquals(2, result.get(1).getId());
        assertEquals("Sports", result.get(1).getName());
        assertEquals(0, result.get(1).getIsHide());

        verify(categoryRepository, times(1)).findByIsHide(0);
    }

    @Test
    void getVisibleCategoriesByIds_Success() {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Technology");
        category1.setIsHide(0);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Sports");
        category2.setIsHide(0);

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findByIdInAndIsHide(categoryIds, 0)).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryService.getVisibleCategoriesByIds(categoryIds);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Technology", result.get(0).getName());
        assertEquals(0, result.get(0).getIsHide());
        assertEquals(2, result.get(1).getId());
        assertEquals("Sports", result.get(1).getName());
        assertEquals(0, result.get(1).getIsHide());

        verify(categoryRepository, times(1)).findByIdInAndIsHide(categoryIds, 0);
    }

    @Test
    void hideCategory_Success() {
        // Arrange
        Category categoryToHide = new Category();
        categoryToHide.setId(1);
        categoryToHide.setName("Technology");
        categoryToHide.setIsHide(0);

        Category hiddenCategory = new Category();
        hiddenCategory.setId(1);
        hiddenCategory.setName("Technology");
        hiddenCategory.setIsHide(1);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryToHide));
        when(categoryRepository.save(any(Category.class))).thenReturn(hiddenCategory);

        // Act
        CategoryDTO result = categoryService.hideCategory(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Technology", result.getName());
        assertEquals(1, result.getIsHide());

        verify(categoryRepository, times(1)).findById(1);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void hideCategory_NotFound_ThrowsException() {
        // Arrange
        when(categoryRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryService.hideCategory(999);
        });

        assertEquals("Category not found with id: 999", exception.getMessage());
        verify(categoryRepository, times(1)).findById(999);
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void unhideCategory_Success() {
        // Arrange
        Category categoryToUnhide = new Category();
        categoryToUnhide.setId(1);
        categoryToUnhide.setName("Technology");
        categoryToUnhide.setIsHide(1);

        Category unhiddenCategory = new Category();
        unhiddenCategory.setId(1);
        unhiddenCategory.setName("Technology");
        unhiddenCategory.setIsHide(0);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryToUnhide));
        when(categoryRepository.save(any(Category.class))).thenReturn(unhiddenCategory);

        // Act
        CategoryDTO result = categoryService.unhideCategory(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Technology", result.getName());
        assertEquals(0, result.getIsHide());

        verify(categoryRepository, times(1)).findById(1);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void unhideCategory_NotFound_ThrowsException() {
        // Arrange
        when(categoryRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryService.unhideCategory(999);
        });

        assertEquals("Category not found with id: 999", exception.getMessage());
        verify(categoryRepository, times(1)).findById(999);
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void createCategory_WithNullName_ThrowsException() {
        // Arrange
        CategoryDTO inputDto = new CategoryDTO();
        inputDto.setName(null);

        when(categoryRepository.existsByName(null)).thenReturn(false);

        // Act & Assert
        assertThrows(Exception.class, () -> {
            categoryService.createCategory(inputDto);
        });

        verify(categoryRepository, times(1)).existsByName(null);
    }

    @Test
    void createCategory_WithEmptyName_ThrowsException() {
        // Arrange
        CategoryDTO inputDto = new CategoryDTO();
        inputDto.setName("");

        when(categoryRepository.existsByName("")).thenReturn(false);

        // Act & Assert
        assertThrows(Exception.class, () -> {
            categoryService.createCategory(inputDto);
        });

        verify(categoryRepository, times(1)).existsByName("");
    }
} 