package com.server.NewAggrigationServer.controller;

import com.server.NewAggrigationServer.dto.NewsDTO;
import com.server.NewAggrigationServer.dto.SavedNewsDTO;
import com.server.NewAggrigationServer.service.SavedNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-news")
public class SavedNewsController {

    @Autowired
    private SavedNewsService savedNewsService;

    @PostMapping
    public SavedNewsDTO saveNews(@RequestBody SavedNewsDTO dto) {
        return savedNewsService.saveNews(dto);
    }

    @DeleteMapping
    public String deleteSavedNews(@RequestParam Integer userId, @RequestParam Integer newsId) {
        savedNewsService.deleteSavedNews(userId, newsId);
        return "Saved news deleted successfully";
    }

}

