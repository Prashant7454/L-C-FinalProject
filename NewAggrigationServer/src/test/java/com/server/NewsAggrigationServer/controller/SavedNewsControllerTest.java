package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.SavedNewsDTO;
import com.server.NewsAggrigationServer.service.SavedNewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SavedNewsController.class)
public class SavedNewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavedNewsService savedNewsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveNews() throws Exception {
        SavedNewsDTO dto = new SavedNewsDTO();
        dto.setUserId(1);
        dto.setNewsId(2);
        dto.setId(10);

        when(savedNewsService.saveNews(any(SavedNewsDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/saved-news")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    public void testDeleteSavedNews() throws Exception {
        doNothing().when(savedNewsService).deleteSavedNews(1, 2);

        mockMvc.perform(delete("/api/saved-news")
                .param("userId", "1")
                .param("newsId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Saved news deleted successfully"));
    }
} 