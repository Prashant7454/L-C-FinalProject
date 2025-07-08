package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.KeywordDTO;
import com.server.NewsAggrigationServer.model.Keyword;
import com.server.NewsAggrigationServer.repository.KeywordRepository;
import com.server.NewsAggrigationServer.service.KeywordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeywordServiceImpl implements KeywordService {

    private static final Logger log = LoggerFactory.getLogger(KeywordServiceImpl.class);

    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public KeywordDTO addKeyword(KeywordDTO dto) {
        log.info("Adding keyword: {}", dto);
        try {
            Keyword keyword = new Keyword();
            keyword.setName(dto.getName());
            Keyword saved = keywordRepository.save(keyword);
            log.info("Keyword created with ID: {}", saved.getId());
            KeywordDTO response = new KeywordDTO();
            response.setId(saved.getId());
            response.setName(saved.getName());
            return response;
        } catch (Exception ex) {
            log.error("Error adding keyword: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<KeywordDTO> getAllKeywords() {
        log.info("Fetching all keywords");
        List<Keyword> keywords = keywordRepository.findAll();
        log.info("Fetched {} keywords", keywords.size());
        return keywords.stream().map(keyword -> {
            KeywordDTO dto = new KeywordDTO();
            dto.setId(keyword.getId());
            dto.setName(keyword.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
