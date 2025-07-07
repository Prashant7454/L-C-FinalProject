package com.server.NewsAggrigationServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.NewsAggrigationServer.dto.NewsLikeDislikeUserDTO;
import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import com.server.NewsAggrigationServer.service.NewsLikeDislikeUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NewsLikeDislikeUserControllerTest {

    @Mock
    private NewsLikeDislikeUserService newsLikeDislikeUserService;

    @InjectMocks
    private NewsLikeDislikeUserController newsLikeDislikeUserController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(newsLikeDislikeUserController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void likeOrDislike_Success() throws Exception {
        NewsLikeDislikeUserDTO dto = createDTO(1, 2, 1, 1, 0);
        NewsLikeDislikeUser expected = createModel(1, 2, 1, 1, 0);
        when(newsLikeDislikeUserService.saveOrUpdate(any(NewsLikeDislikeUserDTO.class))).thenReturn(expected);

        mockMvc.perform(post("/api/news-reactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.newsId").value(2))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.liked").value(1))
                .andExpect(jsonPath("$.disliked").value(0));

        verify(newsLikeDislikeUserService, times(1)).saveOrUpdate(any(NewsLikeDislikeUserDTO.class));
    }

    @Test
    void likeOrDislike_Error() throws Exception {
        NewsLikeDislikeUserDTO dto = createDTO(1, 2, 1, 1, 0);
        when(newsLikeDislikeUserService.saveOrUpdate(any(NewsLikeDislikeUserDTO.class)))
                .thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(post("/api/news-reactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError());

        verify(newsLikeDislikeUserService, times(1)).saveOrUpdate(any(NewsLikeDislikeUserDTO.class));
    }

    @Test
    void getReaction_Success() throws Exception {
        NewsLikeDislikeUser expected = createModel(1, 2, 1, 1, 0);
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(eq(2), eq(1))).thenReturn(expected);

        mockMvc.perform(get("/api/news-reactions/{newsId}/{userId}", 2, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(1))
                .andExpect(jsonPath(".newsId").value(2))
                .andExpect(jsonPath(".userId").value(1))
                .andExpect(jsonPath(".liked").value(1))
                .andExpect(jsonPath(".disliked").value(0));

        verify(newsLikeDislikeUserService, times(1)).getByNewsIdAndUserId(2, 1);
    }

    @Test
    void getReaction_Error() throws Exception {
        when(newsLikeDislikeUserService.getByNewsIdAndUserId(eq(2), eq(1)))
                .thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/api/news-reactions/{newsId}/{userId}", 2, 1))
                .andExpect(status().isInternalServerError());

        verify(newsLikeDislikeUserService, times(1)).getByNewsIdAndUserId(2, 1);
    }

    private NewsLikeDislikeUserDTO createDTO(Integer id, Integer newsId, Integer userId, Integer liked, Integer disliked) {
        NewsLikeDislikeUserDTO dto = new NewsLikeDislikeUserDTO();
        dto.setId(id);
        dto.setNewsId(newsId);
        dto.setUserId(userId);
        dto.setLiked(liked);
        dto.setDisliked(disliked);
        return dto;
    }

    private NewsLikeDislikeUser createModel(Integer id, Integer newsId, Integer userId, Integer liked, Integer disliked) {
        NewsLikeDislikeUser model = new NewsLikeDislikeUser();
        model.setId(id);
        model.setNewsId(newsId);
        model.setUserId(userId);
        model.setLiked(liked);
        model.setDisliked(disliked);
        return model;
    }
}
