package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.KeywordDTO;
import com.server.NewsAggrigationServer.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @PostMapping
    public KeywordDTO addKeyword(@RequestBody KeywordDTO dto) {
        return keywordService.addKeyword(dto);
    }

    @GetMapping
    public List<KeywordDTO> getAllKeywords() {
        return keywordService.getAllKeywords();
    }
}
