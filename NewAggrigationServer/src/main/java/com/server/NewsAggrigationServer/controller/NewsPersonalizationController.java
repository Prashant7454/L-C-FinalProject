package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.service.NewsPersonalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personalization")
public class NewsPersonalizationController {

    @Autowired
    private NewsPersonalizationService personalizationService;

    /**
     * Get personalized news recommendations for a user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NewsDTO>> getPersonalizedNews(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<NewsDTO> personalizedNews = personalizationService.getPersonalizedNews(userId, limit);
            return ResponseEntity.ok(personalizedNews);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get personalized news recommendations with pagination
     */
    @GetMapping("/user/{userId}/page")
    public ResponseEntity<List<NewsDTO>> getPersonalizedNewsPaginated(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<NewsDTO> personalizedNews = personalizationService.getPersonalizedNewsPaginated(userId, page, size);
            return ResponseEntity.ok(personalizedNews);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Calculate user's interest score for a specific news article
     */
    @GetMapping("/user/{userId}/news/{newsId}/score")
    public ResponseEntity<Double> getUserInterestScore(
            @PathVariable Integer userId,
            @PathVariable Integer newsId) {
        try {
            double score = personalizationService.calculateUserInterestScore(userId, newsId);
            return ResponseEntity.ok(score);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get user's top interest categories
     */
    @GetMapping("/user/{userId}/top-categories")
    public ResponseEntity<List<Integer>> getUserTopInterestCategories(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "5") int limit) {
        try {
            List<Integer> topCategories = personalizationService.getUserTopInterestCategories(userId, limit);
            return ResponseEntity.ok(topCategories);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Record that a user has read an article
     */
    @PostMapping("/user/{userId}/news/{newsId}/read")
    public ResponseEntity<String> recordArticleRead(
            @PathVariable Integer userId,
            @PathVariable Integer newsId) {
        try {
            personalizationService.recordArticleRead(userId, newsId);
            return ResponseEntity.ok("Article read recorded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error recording article read: " + e.getMessage());
        }
    }
} 