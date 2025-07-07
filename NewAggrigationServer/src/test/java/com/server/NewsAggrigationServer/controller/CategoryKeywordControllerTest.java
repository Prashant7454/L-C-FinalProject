package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.CategoryKeywordDTO;
import com.server.NewsAggrigationServer.service.CategoryKeywordService;
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
public class CategoryKeywordControllerTest {

    @Mock
    private CategoryKeywordService categoryKeywordService;

    @InjectMocks
    private CategoryKeywordController categoryKeywordController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryKeywordController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createCategoryKeyword_Success() throws Exception {
        // Arrange
        CategoryKeywordDTO inputDto = new CategoryKeywordDTO();
        inputDto.setCategoryId(1);
        inputDto.setKeywordId(1);

        CategoryKeywordDTO expectedDto = new CategoryKeywordDTO();
        expectedDto.setId(1);
        expectedDto.setCategoryId(1);
        expectedDto.setKeywordId(1);

        when(categoryKeywordService.createCategoryKeyword(any(CategoryKeywordDTO.class))).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(post("/api/category-keyword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.keywordId").value(1));

        verify(categoryKeywordService, times(1)).createCategoryKeyword(any(CategoryKeywordDTO.class));
    }

    @Test
    void createCategoryKeyword_WithInvalidData_ThrowsException() throws Exception {
        // Arrange
        CategoryKeywordDTO inputDto = new CategoryKeywordDTO();
        inputDto.setCategoryId(null);
        inputDto.setKeywordId(null);

        when(categoryKeywordService.createCategoryKeyword(any(CategoryKeywordDTO.class)))
                .thenThrow(new IllegalArgumentException("Category ID and Keyword ID cannot be null"));

        // Act & Assert
        mockMvc.perform(post("/api/category-keyword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isInternalServerError());

        verify(categoryKeywordService, times(1)).createCategoryKeyword(any(CategoryKeywordDTO.class));
    }

    @Test
    void getAllCategoryKeywords_Success() throws Exception {
        // Arrange
        List<CategoryKeywordDTO> expectedCategoryKeywords = Arrays.asList(
                createCategoryKeywordDTO(1, 1, 1),
                createCategoryKeywordDTO(2, 2, 2),
                createCategoryKeywordDTO(3, 3, 3)
        );

        when(categoryKeywordService.getAllCategoryKeywords()).thenReturn(expectedCategoryKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].categoryId").value(1))
                .andExpect(jsonPath("$[0].keywordId").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].categoryId").value(2))
                .andExpect(jsonPath("$[1].keywordId").value(2))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].categoryId").value(3))
                .andExpect(jsonPath("$[2].keywordId").value(3));

        verify(categoryKeywordService, times(1)).getAllCategoryKeywords();
    }

    @Test
    void getAllCategoryKeywords_EmptyList() throws Exception {
        // Arrange
        when(categoryKeywordService.getAllCategoryKeywords()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(categoryKeywordService, times(1)).getAllCategoryKeywords();
    }

    @Test
    void getCategoryKeywordsByCategoryId_Success() throws Exception {
        // Arrange
        Integer categoryId = 1;
        List<CategoryKeywordDTO> expectedCategoryKeywords = Arrays.asList(
                createCategoryKeywordDTO(1, 1, 1),
                createCategoryKeywordDTO(2, 1, 2),
                createCategoryKeywordDTO(3, 1, 3)
        );

        when(categoryKeywordService.getCategoryKeywordsByCategoryId(categoryId)).thenReturn(expectedCategoryKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword/category/{categoryId}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].categoryId").value(1))
                .andExpect(jsonPath("$[0].keywordId").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].categoryId").value(1))
                .andExpect(jsonPath("$[1].keywordId").value(2))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].categoryId").value(1))
                .andExpect(jsonPath("$[2].keywordId").value(3));

        verify(categoryKeywordService, times(1)).getCategoryKeywordsByCategoryId(categoryId);
    }

    @Test
    void getCategoryKeywordsByCategoryId_EmptyList() throws Exception {
        // Arrange
        Integer categoryId = 999;
        when(categoryKeywordService.getCategoryKeywordsByCategoryId(categoryId)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword/category/{categoryId}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(categoryKeywordService, times(1)).getCategoryKeywordsByCategoryId(categoryId);
    }

    @Test
    void getCategoryKeywordsByKeywordId_Success() throws Exception {
        // Arrange
        Integer keywordId = 1;
        List<CategoryKeywordDTO> expectedCategoryKeywords = Arrays.asList(
                createCategoryKeywordDTO(1, 1, 1),
                createCategoryKeywordDTO(2, 2, 1),
                createCategoryKeywordDTO(3, 3, 1)
        );

        when(categoryKeywordService.getCategoryKeywordsByKeywordId(keywordId)).thenReturn(expectedCategoryKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword/keyword/{keywordId}", keywordId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].categoryId").value(1))
                .andExpect(jsonPath("$[0].keywordId").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].categoryId").value(2))
                .andExpect(jsonPath("$[1].keywordId").value(1))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].categoryId").value(3))
                .andExpect(jsonPath("$[2].keywordId").value(1));

        verify(categoryKeywordService, times(1)).getCategoryKeywordsByKeywordId(keywordId);
    }

    @Test
    void getCategoryKeywordsByKeywordId_EmptyList() throws Exception {
        // Arrange
        Integer keywordId = 999;
        when(categoryKeywordService.getCategoryKeywordsByKeywordId(keywordId)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword/keyword/{keywordId}", keywordId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(categoryKeywordService, times(1)).getCategoryKeywordsByKeywordId(keywordId);
    }

    @Test
    void createCategoryKeyword_WithMissingContentType_ReturnsError() throws Exception {
        // Arrange
        CategoryKeywordDTO inputDto = new CategoryKeywordDTO();
        inputDto.setCategoryId(1);
        inputDto.setKeywordId(1);

        // Act & Assert
        mockMvc.perform(post("/api/category-keyword")
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isUnsupportedMediaType());

        verify(categoryKeywordService, never()).createCategoryKeyword(any(CategoryKeywordDTO.class));
    }

    @Test
    void createCategoryKeyword_WithInvalidJson_ReturnsError() throws Exception {
        // Arrange
        String invalidJson = "{ invalid json }";

        // Act & Assert
        mockMvc.perform(post("/api/category-keyword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());

        verify(categoryKeywordService, never()).createCategoryKeyword(any(CategoryKeywordDTO.class));
    }

    @Test
    void createCategoryKeyword_VerifyRequestMapping() throws Exception {
        // Arrange
        CategoryKeywordDTO inputDto = new CategoryKeywordDTO();
        inputDto.setCategoryId(5);
        inputDto.setKeywordId(10);

        CategoryKeywordDTO expectedDto = new CategoryKeywordDTO();
        expectedDto.setId(1);
        expectedDto.setCategoryId(5);
        expectedDto.setKeywordId(10);

        when(categoryKeywordService.createCategoryKeyword(any(CategoryKeywordDTO.class))).thenReturn(expectedDto);

        // Act & Assert
        mockMvc.perform(post("/api/category-keyword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.categoryId").value(5))
                .andExpect(jsonPath("$.keywordId").value(10));

        // Verify that the service was called with the correct DTO
        verify(categoryKeywordService, times(1)).createCategoryKeyword(argThat(dto -> 
            dto.getCategoryId().equals(5) && 
            dto.getKeywordId().equals(10)
        ));
    }

    @Test
    void getAllCategoryKeywords_VerifyServiceCall() throws Exception {
        // Arrange
        List<CategoryKeywordDTO> expectedCategoryKeywords = Arrays.asList(
                createCategoryKeywordDTO(1, 1, 1),
                createCategoryKeywordDTO(2, 2, 2)
        );

        when(categoryKeywordService.getAllCategoryKeywords()).thenReturn(expectedCategoryKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(categoryKeywordService, times(1)).getAllCategoryKeywords();
    }

    @Test
    void getCategoryKeywordsByCategoryId_VerifyPathVariable() throws Exception {
        // Arrange
        Integer categoryId = 123;
        List<CategoryKeywordDTO> expectedCategoryKeywords = Arrays.asList(
                createCategoryKeywordDTO(1, 123, 1)
        );

        when(categoryKeywordService.getCategoryKeywordsByCategoryId(categoryId)).thenReturn(expectedCategoryKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword/category/{categoryId}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(123));

        verify(categoryKeywordService, times(1)).getCategoryKeywordsByCategoryId(categoryId);
    }

    @Test
    void getCategoryKeywordsByKeywordId_VerifyPathVariable() throws Exception {
        // Arrange
        Integer keywordId = 456;
        List<CategoryKeywordDTO> expectedCategoryKeywords = Arrays.asList(
                createCategoryKeywordDTO(1, 1, 456)
        );

        when(categoryKeywordService.getCategoryKeywordsByKeywordId(keywordId)).thenReturn(expectedCategoryKeywords);

        // Act & Assert
        mockMvc.perform(get("/api/category-keyword/keyword/{keywordId}", keywordId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].keywordId").value(456));

        verify(categoryKeywordService, times(1)).getCategoryKeywordsByKeywordId(keywordId);
    }

    private CategoryKeywordDTO createCategoryKeywordDTO(Integer id, Integer categoryId, Integer keywordId) {
        CategoryKeywordDTO dto = new CategoryKeywordDTO();
        dto.setId(id);
        dto.setCategoryId(categoryId);
        dto.setKeywordId(keywordId);
        return dto;
    }
} 