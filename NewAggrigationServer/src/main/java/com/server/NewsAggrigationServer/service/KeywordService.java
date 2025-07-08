package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.KeywordDTO;

import java.util.List;

public interface KeywordService {
    KeywordDTO addKeyword(KeywordDTO dto);

    List<KeywordDTO> getAllKeywords();
}
