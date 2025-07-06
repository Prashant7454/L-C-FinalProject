package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.service.NewsHidingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news-hiding")
public class NewsHidingController {

    @Autowired
    private NewsHidingService newsHidingService;

    /**
     * Hide news articles that contain any of the specified keywords
     */
    @PostMapping("/hide-by-keywords")
    public ResponseEntity<String> hideNewsByKeywords(@RequestBody List<String> keywords) {
        try {
            if (keywords == null || keywords.isEmpty()) {
                return ResponseEntity.badRequest().body("Keywords list cannot be empty");
            }

            // First, get count of news that would be affected
            int affectedCount = newsHidingService.getNewsCountByKeywords(keywords);
            
            if (affectedCount == 0) {
                return ResponseEntity.ok("No news articles found containing the specified keywords: " + keywords);
            }

            // Hide the news articles
            int hiddenCount = newsHidingService.hideNewsByKeywords(keywords);

            return ResponseEntity.ok("Successfully hidden " + hiddenCount + " out of " + affectedCount + 
                                   " news articles containing keywords: " + keywords);

        } catch (Exception e) {
            System.err.println("Error hiding news by keywords: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error hiding news: " + e.getMessage());
        }
    }

    /**
     * Find news articles that contain any of the specified keywords (without hiding them)
     */
    @PostMapping("/find-by-keywords")
    public ResponseEntity<List<News>> findNewsByKeywords(@RequestBody List<String> keywords) {
        try {
            if (keywords == null || keywords.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            List<News> foundNews = newsHidingService.findNewsByKeywords(keywords);
            return ResponseEntity.ok(foundNews);

        } catch (Exception e) {
            System.err.println("Error finding news by keywords: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get count of news articles that contain any of the specified keywords
     */
    @PostMapping("/count-by-keywords")
    public ResponseEntity<Integer> getNewsCountByKeywords(@RequestBody List<String> keywords) {
        try {
            if (keywords == null || keywords.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            int count = newsHidingService.getNewsCountByKeywords(keywords);
            return ResponseEntity.ok(count);

        } catch (Exception e) {
            System.err.println("Error counting news by keywords: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Hide a specific news article by ID
     */
    @PutMapping("/hide/{newsId}")
    public ResponseEntity<String> hideNewsById(@PathVariable Integer newsId) {
        try {
            boolean hidden = newsHidingService.hideNewsById(newsId);
            
            if (hidden) {
                return ResponseEntity.ok("News article ID " + newsId + " has been hidden successfully");
            } else {
                return ResponseEntity.ok("News article ID " + newsId + " was already hidden or not found");
            }

        } catch (Exception e) {
            System.err.println("Error hiding news ID " + newsId + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error hiding news: " + e.getMessage());
        }
    }

    /**
     * Unhide a specific news article by ID
     */
    @PutMapping("/unhide/{newsId}")
    public ResponseEntity<String> unhideNewsById(@PathVariable Integer newsId) {
        try {
            boolean unhidden = newsHidingService.unhideNewsById(newsId);
            
            if (unhidden) {
                return ResponseEntity.ok("News article ID " + newsId + " has been unhidden successfully");
            } else {
                return ResponseEntity.ok("News article ID " + newsId + " was already visible or not found");
            }

        } catch (Exception e) {
            System.err.println("Error unhiding news ID " + newsId + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error unhiding news: " + e.getMessage());
        }
    }
} 