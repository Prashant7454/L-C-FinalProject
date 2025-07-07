package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.dto.NewsCategoryDTO;
import com.server.NewsAggrigationServer.model.NewsCategory;
import com.server.NewsAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewsAggrigationServer.service.impl.NewsCategoryServiceImpl;
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
public class NewsCategoryServiceImplTest {

    @Mock
    private NewsCategoryRepository newsCategoryRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private NewsCategoryServiceImpl newsCategoryService;

    private NewsCategory testEntity;
    private NewsCategoryDTO testDto;

    @BeforeEach
    void setUp() {
        testEntity = new NewsCategory(1, 2);
        testEntity.setId(10);
        testDto = new NewsCategoryDTO();
        testDto.setId(10);
        testDto.setNewsId(1);
        testDto.setCategoryId(2);
    }

    @Test
    void assignCategory_Success() {
        NewsCategoryDTO inputDto = new NewsCategoryDTO();
        inputDto.setNewsId(1);
        inputDto.setCategoryId(2);

        when(newsCategoryRepository.save(any(NewsCategory.class))).thenReturn(testEntity);

        NewsCategoryDTO result = newsCategoryService.assignCategory(inputDto);

        assertNotNull(result);
        assertEquals(10, result.getId());
        assertEquals(1, result.getNewsId());
        assertEquals(2, result.getCategoryId());
        verify(newsCategoryRepository, times(1)).save(any(NewsCategory.class));
    }

    @Test
    void getCategoriesByNewsId_Success() {
        List<NewsCategory> newsCategories = Arrays.asList(
                new NewsCategory(1, 2),
                new NewsCategory(1, 3)
        );
        newsCategories.get(0).setId(10);
        newsCategories.get(1).setId(11);
        List<Integer> categoryIds = Arrays.asList(2, 3);
        List<CategoryDTO> expectedCategories = Arrays.asList(new CategoryDTO(), new CategoryDTO());

        when(newsCategoryRepository.findByNewsId(1)).thenReturn(newsCategories);
        when(categoryService.getVisibleCategoriesByIds(categoryIds)).thenReturn(expectedCategories);

        List<CategoryDTO> result = newsCategoryService.getCategoriesByNewsId(1);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(newsCategoryRepository, times(1)).findByNewsId(1);
        verify(categoryService, times(1)).getVisibleCategoriesByIds(categoryIds);
    }

    @Test
    void removeCategoryFromNews_Success() {
        doNothing().when(newsCategoryRepository).deleteByNewsIdAndCategoryId(1, 2);
        newsCategoryService.removeCategoryFromNews(1, 2);
        verify(newsCategoryRepository, times(1)).deleteByNewsIdAndCategoryId(1, 2);
    }

    @Test
    void removeCategoriesByNewsId_Success() {
        doNothing().when(newsCategoryRepository).deleteByNewsId(1);
        newsCategoryService.removeCategoriesByNewsId(1);
        verify(newsCategoryRepository, times(1)).deleteByNewsId(1);
    }

    @Test
    void getAllNewsCategory_Success() {
        NewsCategory entity1 = new NewsCategory(1, 2); entity1.setId(10);
        NewsCategory entity2 = new NewsCategory(2, 3); entity2.setId(11);
        List<NewsCategory> entities = Arrays.asList(entity1, entity2);
        when(newsCategoryRepository.findAll()).thenReturn(entities);

        List<NewsCategoryDTO> result = newsCategoryService.getAllNewsCategory();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getId());
        assertEquals(11, result.get(1).getId());
        verify(newsCategoryRepository, times(1)).findAll();
    }

    @Test
    void getAllNewsCategory_EmptyList() {
        when(newsCategoryRepository.findAll()).thenReturn(Collections.emptyList());
        List<NewsCategoryDTO> result = newsCategoryService.getAllNewsCategory();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(newsCategoryRepository, times(1)).findAll();
    }

    @Test
    void getNewsIdByCategoryId_Success() {
        NewsCategory entity1 = new NewsCategory(1, 2); entity1.setId(10);
        NewsCategory entity2 = new NewsCategory(3, 2); entity2.setId(11);
        List<NewsCategory> entities = Arrays.asList(entity1, entity2);
        when(newsCategoryRepository.findByCategoryId(2)).thenReturn(entities);

        List<Integer> result = newsCategoryService.getNewsIdByCategoryId(2);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0));
        assertEquals(3, result.get(1));
        verify(newsCategoryRepository, times(1)).findByCategoryId(2);
    }
} 