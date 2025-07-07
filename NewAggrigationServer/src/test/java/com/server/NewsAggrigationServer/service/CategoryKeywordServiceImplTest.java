package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.CategoryKeywordDTO;
import com.server.NewsAggrigationServer.model.CategoryKeyword;
import com.server.NewsAggrigationServer.repository.CategoryKeywordRepository;
import com.server.NewsAggrigationServer.service.impl.CategoryKeywordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryKeywordServiceImplTest {

    @Mock
    private CategoryKeywordRepository repository;

    @InjectMocks
    private CategoryKeywordServiceImpl categoryKeywordService;

    private CategoryKeyword testCategoryKeyword;
    private CategoryKeywordDTO testCategoryKeywordDTO;

    @BeforeEach
    void setUp() {
        testCategoryKeyword = new CategoryKeyword();
        testCategoryKeyword.setId(1);
        testCategoryKeyword.setCategoryId(1);
        testCategoryKeyword.setKeywordId(1);

        testCategoryKeywordDTO = new CategoryKeywordDTO();
        testCategoryKeywordDTO.setId(1);
        testCategoryKeywordDTO.setCategoryId(1);
        testCategoryKeywordDTO.setKeywordId(1);
    }

    @Test
    void createCategoryKeyword_Success() {
        // Arrange
        CategoryKeywordDTO inputDto = new CategoryKeywordDTO();
        inputDto.setCategoryId(1);
        inputDto.setKeywordId(1);

        when(repository.save(any(CategoryKeyword.class))).thenReturn(testCategoryKeyword);

        // Act
        CategoryKeywordDTO result = categoryKeywordService.createCategoryKeyword(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getCategoryId());
        assertEquals(1, result.getKeywordId());

        verify(repository, times(1)).save(any(CategoryKeyword.class));
    }

    @Test
    void createCategoryKeyword_WithNullValues_ThrowsException() {
        // Arrange
        CategoryKeywordDTO inputDto = new CategoryKeywordDTO();
        inputDto.setCategoryId(null);
        inputDto.setKeywordId(null);

        when(repository.save(any(CategoryKeyword.class)))
                .thenThrow(new IllegalArgumentException("Category ID and Keyword ID cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoryKeywordService.createCategoryKeyword(inputDto);
        });

        verify(repository, times(1)).save(any(CategoryKeyword.class));
    }

    @Test
    void getAllCategoryKeywords_Success() {
        // Arrange
        CategoryKeyword categoryKeyword1 = new CategoryKeyword();
        categoryKeyword1.setId(1);
        categoryKeyword1.setCategoryId(1);
        categoryKeyword1.setKeywordId(1);

        CategoryKeyword categoryKeyword2 = new CategoryKeyword();
        categoryKeyword2.setId(2);
        categoryKeyword2.setCategoryId(2);
        categoryKeyword2.setKeywordId(2);

        List<CategoryKeyword> categoryKeywords = Arrays.asList(categoryKeyword1, categoryKeyword2);

        when(repository.findAll()).thenReturn(categoryKeywords);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordService.getAllCategoryKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(0).getKeywordId());
        assertEquals(2, result.get(1).getId());
        assertEquals(2, result.get(1).getCategoryId());
        assertEquals(2, result.get(1).getKeywordId());

        verify(repository, times(1)).findAll();
    }

    @Test
    void getAllCategoryKeywords_EmptyList() {
        // Arrange
        when(repository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordService.getAllCategoryKeywords();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
    }

    @Test
    void getCategoryKeywordsByCategoryId_Success() {
        // Arrange
        Integer categoryId = 1;
        CategoryKeyword categoryKeyword1 = new CategoryKeyword();
        categoryKeyword1.setId(1);
        categoryKeyword1.setCategoryId(1);
        categoryKeyword1.setKeywordId(1);

        CategoryKeyword categoryKeyword2 = new CategoryKeyword();
        categoryKeyword2.setId(2);
        categoryKeyword2.setCategoryId(1);
        categoryKeyword2.setKeywordId(2);

        List<CategoryKeyword> categoryKeywords = Arrays.asList(categoryKeyword1, categoryKeyword2);

        when(repository.findByCategoryId(categoryId)).thenReturn(categoryKeywords);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordService.getCategoryKeywordsByCategoryId(categoryId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(0).getKeywordId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(1).getCategoryId());
        assertEquals(2, result.get(1).getKeywordId());

        verify(repository, times(1)).findByCategoryId(categoryId);
    }

    @Test
    void getCategoryKeywordsByCategoryId_EmptyList() {
        // Arrange
        Integer categoryId = 999;
        when(repository.findByCategoryId(categoryId)).thenReturn(Arrays.asList());

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordService.getCategoryKeywordsByCategoryId(categoryId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findByCategoryId(categoryId);
    }

    @Test
    void getCategoryKeywordsByKeywordId_Success() {
        // Arrange
        Integer keywordId = 1;
        CategoryKeyword categoryKeyword1 = new CategoryKeyword();
        categoryKeyword1.setId(1);
        categoryKeyword1.setCategoryId(1);
        categoryKeyword1.setKeywordId(1);

        CategoryKeyword categoryKeyword2 = new CategoryKeyword();
        categoryKeyword2.setId(2);
        categoryKeyword2.setCategoryId(2);
        categoryKeyword2.setKeywordId(1);

        List<CategoryKeyword> categoryKeywords = Arrays.asList(categoryKeyword1, categoryKeyword2);

        when(repository.findByKeywordId(keywordId)).thenReturn(categoryKeywords);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordService.getCategoryKeywordsByKeywordId(keywordId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(1, result.get(0).getCategoryId());
        assertEquals(1, result.get(0).getKeywordId());
        assertEquals(2, result.get(1).getId());
        assertEquals(2, result.get(1).getCategoryId());
        assertEquals(1, result.get(1).getKeywordId());

        verify(repository, times(1)).findByKeywordId(keywordId);
    }

    @Test
    void getCategoryKeywordsByKeywordId_EmptyList() {
        // Arrange
        Integer keywordId = 999;
        when(repository.findByKeywordId(keywordId)).thenReturn(Arrays.asList());

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordService.getCategoryKeywordsByKeywordId(keywordId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findByKeywordId(keywordId);
    }

    @Test
    void createCategoryKeyword_VerifyEntityMapping() {
        // Arrange
        CategoryKeywordDTO inputDto = new CategoryKeywordDTO();
        inputDto.setCategoryId(5);
        inputDto.setKeywordId(10);

        CategoryKeyword savedEntity = new CategoryKeyword();
        savedEntity.setId(1);
        savedEntity.setCategoryId(5);
        savedEntity.setKeywordId(10);

        when(repository.save(any(CategoryKeyword.class))).thenReturn(savedEntity);

        // Act
        CategoryKeywordDTO result = categoryKeywordService.createCategoryKeyword(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(5, result.getCategoryId());
        assertEquals(10, result.getKeywordId());

        // Verify that the repository was called with the correct entity
        verify(repository, times(1)).save(argThat(entity -> 
            entity.getCategoryId().equals(5) && 
            entity.getKeywordId().equals(10)
        ));
    }

    @Test
    void getAllCategoryKeywords_VerifyDTOMapping() {
        // Arrange
        CategoryKeyword entity1 = new CategoryKeyword();
        entity1.setId(1);
        entity1.setCategoryId(1);
        entity1.setKeywordId(1);

        CategoryKeyword entity2 = new CategoryKeyword();
        entity2.setId(2);
        entity2.setCategoryId(2);
        entity2.setKeywordId(2);

        List<CategoryKeyword> entities = Arrays.asList(entity1, entity2);

        when(repository.findAll()).thenReturn(entities);

        // Act
        List<CategoryKeywordDTO> result = categoryKeywordService.getAllCategoryKeywords();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        
        // Verify first DTO mapping
        CategoryKeywordDTO dto1 = result.get(0);
        assertEquals(1, dto1.getId());
        assertEquals(1, dto1.getCategoryId());
        assertEquals(1, dto1.getKeywordId());
        
        // Verify second DTO mapping
        CategoryKeywordDTO dto2 = result.get(1);
        assertEquals(2, dto2.getId());
        assertEquals(2, dto2.getCategoryId());
        assertEquals(2, dto2.getKeywordId());

        verify(repository, times(1)).findAll();
    }
} 