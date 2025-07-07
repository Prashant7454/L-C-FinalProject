package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.NewsCategoryDTO;
import com.server.NewsAggrigationServer.service.NewsCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NewsCategoryControllerTest {

    @Mock
    private NewsCategoryService newsCategoryService;

    @InjectMocks
    private NewsCategoryController newsCategoryController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(newsCategoryController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void assignCategoryToNews_Success() throws Exception {
        NewsCategoryDTO inputDto = new NewsCategoryDTO();
        inputDto.setNewsId(1);
        inputDto.setCategoryId(2);

        NewsCategoryDTO expectedDto = new NewsCategoryDTO();
        expectedDto.setId(10);
        expectedDto.setNewsId(1);
        expectedDto.setCategoryId(2);

        when(newsCategoryService.assignCategory(any(NewsCategoryDTO.class))).thenReturn(expectedDto);

        mockMvc.perform(post("/api/news-category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.newsId").value(1))
                .andExpect(jsonPath("$.categoryId").value(2));

        verify(newsCategoryService, times(1)).assignCategory(any(NewsCategoryDTO.class));
    }

    @Test
    void removeCategory_Success() throws Exception {
        doNothing().when(newsCategoryService).removeCategoryFromNews(1, 2);

        mockMvc.perform(delete("/api/news-category")
                .param("newsId", "1")
                .param("categoryId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Category removed from news successfully"));

        verify(newsCategoryService, times(1)).removeCategoryFromNews(1, 2);
    }

    @Test
    void removeAllCategoriesFromNews_Success() throws Exception {
        doNothing().when(newsCategoryService).removeCategoriesByNewsId(1);

        mockMvc.perform(delete("/api/news-category/delete-all")
                .param("newsId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("All categories removed from news successfully"));

        verify(newsCategoryService, times(1)).removeCategoriesByNewsId(1);
    }

    @Test
    void getAllNewsCategory_Success() throws Exception {
        NewsCategoryDTO dto1 = new NewsCategoryDTO();
        dto1.setId(1); dto1.setNewsId(1); dto1.setCategoryId(2);
        NewsCategoryDTO dto2 = new NewsCategoryDTO();
        dto2.setId(2); dto2.setNewsId(2); dto2.setCategoryId(3);
        List<NewsCategoryDTO> expectedList = Arrays.asList(dto1, dto2);

        when(newsCategoryService.getAllNewsCategory()).thenReturn(expectedList);

        mockMvc.perform(get("/api/news-category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(newsCategoryService, times(1)).getAllNewsCategory();
    }

    @Test
    void getNewsIdByCategoryId_Success() throws Exception {
        List<Integer> newsIds = Arrays.asList(1, 2, 3);
        when(newsCategoryService.getNewsIdByCategoryId(5)).thenReturn(newsIds);

        mockMvc.perform(get("/api/news-category/categoryId/{categoryId}", 5))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value(1))
                .andExpect(jsonPath("$[1]").value(2))
                .andExpect(jsonPath("$[2]").value(3));

        verify(newsCategoryService, times(1)).getNewsIdByCategoryId(5);
    }
} 