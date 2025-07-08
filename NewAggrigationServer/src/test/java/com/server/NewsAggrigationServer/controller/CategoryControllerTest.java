package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.CategoryDTO;
import com.server.NewsAggrigationServer.service.CategoryService;
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
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private NewsCategoryService newsCategoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createCategory_Success() throws Exception {
        // Arrange
        CategoryDTO inputDto = new CategoryDTO();
        inputDto.setName("Technology");

        CategoryDTO expectedDto = new CategoryDTO();
        expectedDto.setId(1);
        expectedDto.setName("Technology");
        expectedDto.setIsHide(0);

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Technology"))
                .andExpect(jsonPath("$.isHide").value(0));

        verify(categoryService, times(1)).createCategory(any(CategoryDTO.class));
    }

    @Test
    void createCategory_WithExistingName_ThrowsException() throws Exception {
        // Arrange
        CategoryDTO inputDto = new CategoryDTO();
        inputDto.setName("Technology");

        when(categoryService.createCategory(any(CategoryDTO.class)))
                .thenThrow(new RuntimeException("Category already exists with name: Technology"));

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isInternalServerError());

        verify(categoryService, times(1)).createCategory(any(CategoryDTO.class));
    }

    @Test
    void getAllCategories_Success() throws Exception {
        // Arrange
        List<CategoryDTO> expectedCategories = Arrays.asList(
                createCategoryDTO(1, "Technology", 0),
                createCategoryDTO(2, "Sports", 0),
                createCategoryDTO(3, "Politics", 1)
        );

        when(categoryService.getAllCategories()).thenReturn(expectedCategories);

        // Act & Assert
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Technology"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Sports"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Politics"));

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void getCategoriesByNewsId_Success() throws Exception {
        // Arrange
        Integer newsId = 1;
        List<CategoryDTO> expectedCategories = Arrays.asList(
                createCategoryDTO(1, "Technology", 0),
                createCategoryDTO(2, "Science", 0)
        );

        when(newsCategoryService.getCategoriesByNewsId(newsId)).thenReturn(expectedCategories);

        // Act & Assert
        mockMvc.perform(get("/api/categories/news/{newsId}", newsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Technology"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Science"));

        verify(newsCategoryService, times(1)).getCategoriesByNewsId(newsId);
    }

    @Test
    void getCategoriesByIds_Success() throws Exception {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2, 3);
        List<CategoryDTO> expectedCategories = Arrays.asList(
                createCategoryDTO(1, "Technology", 0),
                createCategoryDTO(2, "Sports", 0),
                createCategoryDTO(3, "Politics", 1)
        );

        when(categoryService.getAllCategoriesByIds(categoryIds)).thenReturn(expectedCategories);

        // Act & Assert
        mockMvc.perform(post("/api/categories/by-ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3));

        verify(categoryService, times(1)).getAllCategoriesByIds(categoryIds);
    }

    @Test
    void getCategoryById_Success() throws Exception {
        // Arrange
        Integer categoryId = 1;
        CategoryDTO expectedCategory = createCategoryDTO(1, "Technology", 0);

        when(categoryService.getCategoryById(categoryId)).thenReturn(expectedCategory);

        // Act & Assert
        mockMvc.perform(get("/api/categories/{id}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Technology"))
                .andExpect(jsonPath("$.isHide").value(0));

        verify(categoryService, times(1)).getCategoryById(categoryId);
    }

    @Test
    void getCategoryById_NotFound_ThrowsException() throws Exception {
        // Arrange
        Integer categoryId = 999;

        when(categoryService.getCategoryById(categoryId))
                .thenThrow(new RuntimeException("Category not found with id: " + categoryId));

        // Act & Assert
        mockMvc.perform(get("/api/categories/{id}", categoryId))
                .andExpect(status().isInternalServerError());

        verify(categoryService, times(1)).getCategoryById(categoryId);
    }

    @Test
    void getAllVisibleCategories_Success() throws Exception {
        // Arrange
        List<CategoryDTO> expectedCategories = Arrays.asList(
                createCategoryDTO(1, "Technology", 0),
                createCategoryDTO(2, "Sports", 0)
        );

        when(categoryService.getAllVisibleCategories()).thenReturn(expectedCategories);

        // Act & Assert
        mockMvc.perform(get("/api/categories/visible"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Technology"))
                .andExpect(jsonPath("$[0].isHide").value(0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Sports"))
                .andExpect(jsonPath("$[1].isHide").value(0));

        verify(categoryService, times(1)).getAllVisibleCategories();
    }

    @Test
    void getVisibleCategoriesByIds_Success() throws Exception {
        // Arrange
        List<Integer> categoryIds = Arrays.asList(1, 2);
        List<CategoryDTO> expectedCategories = Arrays.asList(
                createCategoryDTO(1, "Technology", 0),
                createCategoryDTO(2, "Sports", 0)
        );

        when(categoryService.getVisibleCategoriesByIds(categoryIds)).thenReturn(expectedCategories);

        // Act & Assert
        mockMvc.perform(post("/api/categories/visible/by-ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].isHide").value(0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].isHide").value(0));

        verify(categoryService, times(1)).getVisibleCategoriesByIds(categoryIds);
    }

    @Test
    void hideCategory_Success() throws Exception {
        // Arrange
        Integer categoryId = 1;
        CategoryDTO expectedCategory = createCategoryDTO(1, "Technology", 1);

        when(categoryService.hideCategory(categoryId)).thenReturn(expectedCategory);

        // Act & Assert
        mockMvc.perform(put("/api/categories/{id}/hide", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Technology"))
                .andExpect(jsonPath("$.isHide").value(1));

        verify(categoryService, times(1)).hideCategory(categoryId);
    }

    @Test
    void hideCategory_NotFound_ThrowsException() throws Exception {
        // Arrange
        Integer categoryId = 999;

        when(categoryService.hideCategory(categoryId))
                .thenThrow(new RuntimeException("Category not found with id: " + categoryId));

        // Act & Assert
        mockMvc.perform(put("/api/categories/{id}/hide", categoryId))
                .andExpect(status().isInternalServerError());

        verify(categoryService, times(1)).hideCategory(categoryId);
    }

    @Test
    void unhideCategory_Success() throws Exception {
        // Arrange
        Integer categoryId = 1;
        CategoryDTO expectedCategory = createCategoryDTO(1, "Technology", 0);

        when(categoryService.unhideCategory(categoryId)).thenReturn(expectedCategory);

        // Act & Assert
        mockMvc.perform(put("/api/categories/{id}/unhide", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Technology"))
                .andExpect(jsonPath("$.isHide").value(0));

        verify(categoryService, times(1)).unhideCategory(categoryId);
    }

    @Test
    void unhideCategory_NotFound_ThrowsException() throws Exception {
        // Arrange
        Integer categoryId = 999;

        when(categoryService.unhideCategory(categoryId))
                .thenThrow(new RuntimeException("Category not found with id: " + categoryId));

        // Act & Assert
        mockMvc.perform(put("/api/categories/{id}/unhide", categoryId))
                .andExpect(status().isInternalServerError());

        verify(categoryService, times(1)).unhideCategory(categoryId);
    }

    // Helper method to create CategoryDTO objects
    private CategoryDTO createCategoryDTO(Integer id, String name, Integer isHide) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setIsHide(isHide);
        return dto;
    }
}