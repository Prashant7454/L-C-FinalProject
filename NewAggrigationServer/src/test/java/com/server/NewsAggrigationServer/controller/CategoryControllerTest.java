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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
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

    private CategoryDTO testCategoryDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        objectMapper = new ObjectMapper();

        testCategoryDTO = new CategoryDTO();
        testCategoryDTO.setId(1);
        testCategoryDTO.setName("Technology");
        testCategoryDTO.setIsHide(0);
    }

    @Test
    void testCreateCategory_Success() throws Exception {
        // Arrange
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(testCategoryDTO);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCategoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCategoryDTO.getId()))
                .andExpect(jsonPath("$.name").value(testCategoryDTO.getName()))
                .andExpect(jsonPath("$.isHide").value(testCategoryDTO.getIsHide()));
    }

    @Test
    void testCreateCategory_WithEmptyName() throws Exception {
        // Arrange
        CategoryDTO emptyNameDTO = new CategoryDTO();
        emptyNameDTO.setName("");
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(emptyNameDTO);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyNameDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCategory_WithNullName() throws Exception {
        // Arrange
        CategoryDTO nullNameDTO = new CategoryDTO();
        nullNameDTO.setName(null);
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(nullNameDTO);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nullNameDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllCategories_Success() throws Exception {
        // Arrange
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1);
        category1.setName("Technology");

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(2);
        category2.setName("Science");

        List<CategoryDTO> categories = Arrays.asList(category1, category2);
        when(categoryService.getAllCategories()).thenReturn(categories);

        // Act & Assert
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(category1.getId()))
                .andExpect(jsonPath("$[0].name").value(category1.getName()))
                .andExpect(jsonPath("$[1].id").value(category2.getId()))
                .andExpect(jsonPath("$[1].name").value(category2.getName()));
    }

    @Test
    void testGetAllCategories_EmptyList() throws Exception {
        // Arrange
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetCategoriesByNewsId_Success() throws Exception {
        // Arrange
        List<CategoryDTO> categories = Arrays.asList(testCategoryDTO);
        when(newsCategoryService.getCategoriesByNewsId(1)).thenReturn(categories);

        // Act & Assert
        mockMvc.perform(get("/api/categories/news/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(testCategoryDTO.getId()));
    }

    @Test
    void testGetCategoriesByNewsId_WithZeroId() throws Exception {
        // Arrange
        when(newsCategoryService.getCategoriesByNewsId(0)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/categories/news/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetCategoriesByIds_Success() throws Exception {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        List<CategoryDTO> categories = Arrays.asList(testCategoryDTO);
        when(categoryService.getAllCategoriesByIds(ids)).thenReturn(categories);

        // Act & Assert
        mockMvc.perform(post("/api/categories/by-ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ids)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(testCategoryDTO.getId()));
    }

    @Test
    void testGetCategoriesByIds_EmptyList() throws Exception {
        // Arrange
        List<Integer> ids = Arrays.asList();
        when(categoryService.getAllCategoriesByIds(ids)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(post("/api/categories/by-ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ids)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetCategoryById_Success() throws Exception {
        // Arrange
        when(categoryService.getCategoryById(1)).thenReturn(testCategoryDTO);

        // Act & Assert
        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCategoryDTO.getId()))
                .andExpect(jsonPath("$.name").value(testCategoryDTO.getName()));
    }

    @Test
    void testGetCategoryById_WithZeroId() throws Exception {
        // Arrange
        when(categoryService.getCategoryById(0)).thenReturn(testCategoryDTO);

        // Act & Assert
        mockMvc.perform(get("/api/categories/0"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllVisibleCategories_Success() throws Exception {
        // Arrange
        List<CategoryDTO> categories = Arrays.asList(testCategoryDTO);
        when(categoryService.getAllVisibleCategories()).thenReturn(categories);

        // Act & Assert
        mockMvc.perform(get("/api/categories/visible"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(testCategoryDTO.getId()));
    }

    @Test
    void testGetVisibleCategoriesByIds_Success() throws Exception {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2);
        List<CategoryDTO> categories = Arrays.asList(testCategoryDTO);
        when(categoryService.getVisibleCategoriesByIds(ids)).thenReturn(categories);

        // Act & Assert
        mockMvc.perform(post("/api/categories/visible/by-ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ids)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(testCategoryDTO.getId()));
    }

    @Test
    void testHideCategory_Success() throws Exception {
        // Arrange
        CategoryDTO hiddenCategory = new CategoryDTO();
        hiddenCategory.setId(1);
        hiddenCategory.setName("Technology");
        hiddenCategory.setIsHide(1);
        when(categoryService.hideCategory(1)).thenReturn(hiddenCategory);

        // Act & Assert
        mockMvc.perform(put("/api/categories/1/hide"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hiddenCategory.getId()))
                .andExpect(jsonPath("$.isHide").value(1));
    }

    @Test
    void testUnhideCategory_Success() throws Exception {
        // Arrange
        CategoryDTO visibleCategory = new CategoryDTO();
        visibleCategory.setId(1);
        visibleCategory.setName("Technology");
        visibleCategory.setIsHide(0);
        when(categoryService.unhideCategory(1)).thenReturn(visibleCategory);

        // Act & Assert
        mockMvc.perform(put("/api/categories/1/unhide"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(visibleCategory.getId()))
                .andExpect(jsonPath("$.isHide").value(0));
    }

    @Test
    void testCreateCategory_WithSpecialCharacters() throws Exception {
        // Arrange
        CategoryDTO specialCategory = new CategoryDTO();
        specialCategory.setName("Tech@Science#2023");
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(specialCategory);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(specialCategory)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCategory_WithUnicodeCharacters() throws Exception {
        // Arrange
        CategoryDTO unicodeCategory = new CategoryDTO();
        unicodeCategory.setName("Technology with émojis 🚀");
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(unicodeCategory);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unicodeCategory)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCategory_WithLongName() throws Exception {
        // Arrange
        String longName = "This is a very long category name that contains many characters and should be properly handled";
        CategoryDTO longNameCategory = new CategoryDTO();
        longNameCategory.setName(longName);
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(longNameCategory);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(longNameCategory)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCategory_WithInvalidContentType() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.TEXT_PLAIN)
                .content("invalid content"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void testCreateCategory_WithInvalidJson() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetCategoriesByIds_WithInvalidJson() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/categories/by-ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetVisibleCategoriesByIds_WithInvalidJson() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/categories/visible/by-ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetCategoriesByNewsId_WithNegativeId() throws Exception {
        // Arrange
        when(newsCategoryService.getCategoriesByNewsId(-1)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/categories/news/-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testHideCategory_WithNegativeId() throws Exception {
        // Arrange
        when(categoryService.hideCategory(-1)).thenReturn(testCategoryDTO);

        // Act & Assert
        mockMvc.perform(put("/api/categories/-1/hide"))
                .andExpect(status().isOk());
    }

    @Test
    void testUnhideCategory_WithNegativeId() throws Exception {
        // Arrange
        when(categoryService.unhideCategory(-1)).thenReturn(testCategoryDTO);

        // Act & Assert
        mockMvc.perform(put("/api/categories/-1/unhide"))
                .andExpect(status().isOk());
    }
} 