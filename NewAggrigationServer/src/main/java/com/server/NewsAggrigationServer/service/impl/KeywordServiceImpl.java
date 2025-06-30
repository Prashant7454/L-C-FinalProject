package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.KeywordDTO;
import com.server.NewsAggrigationServer.model.Keyword;
import com.server.NewsAggrigationServer.repository.KeywordRepository;
import com.server.NewsAggrigationServer.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public KeywordDTO addKeyword(KeywordDTO dto) {
        Keyword keyword = new Keyword();
        keyword.setName(dto.getName());
        Keyword saved = keywordRepository.save(keyword);

        KeywordDTO response = new KeywordDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        return response;
    }

    @Override
    public List<KeywordDTO> getAllKeywords() {
        return keywordRepository.findAll().stream().map(keyword -> {
            KeywordDTO dto = new KeywordDTO();
            dto.setId(keyword.getId());
            dto.setName(keyword.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
