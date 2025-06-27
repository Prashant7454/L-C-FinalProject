package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.KeywordDTO;

import java.util.List;

public interface KeywordService {
    KeywordDTO addKeyword(KeywordDTO dto);
    List<KeywordDTO> getAllKeywords();
}
