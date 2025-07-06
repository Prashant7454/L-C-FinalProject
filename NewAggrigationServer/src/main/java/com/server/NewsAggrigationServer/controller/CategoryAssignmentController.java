package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewsAggrigationServer.repository.NewsRepository;
import com.server.NewsAggrigationServer.service.CategoryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-assignment")
public class CategoryAssignmentController {

    @Autowired
    private CategoryAssignmentService categoryAssignmentService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    /**
     * Assign categories to all news articles that don't have any categories
     */
    @PostMapping("/assign-to-uncategorized")
    public ResponseEntity<String> assignCategoriesToUncategorizedNews() {
        try {
            // Find all news articles
            List<News> allNews = newsRepository.findAll();
            
            // Filter news that don't have any categories
            List<News> uncategorizedNews = allNews.stream()
                    .filter(news -> {
                        List<Integer> categoryIds = newsCategoryRepository.findByNewsId(news.getId())
                                .stream()
                                .map(cat -> cat.getCategoryId())
                                .toList();
                        return categoryIds.isEmpty();
                    })
                    .toList();

            if (uncategorizedNews.isEmpty()) {
                return ResponseEntity.ok("No uncategorized news found.");
            }

            System.out.println("Found " + uncategorizedNews.size() + " uncategorized news articles.");
            
            // Assign categories to uncategorized news
            categoryAssignmentService.assignCategoriesToNewsList(uncategorizedNews);

            return ResponseEntity.ok("Successfully assigned categories to " + uncategorizedNews.size() + " news articles.");

        } catch (Exception e) {
            System.err.println("Error assigning categories: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error assigning categories: " + e.getMessage());
        }
    }

    /**
     * Assign categories to a specific news article by ID
     */
    @PostMapping("/assign/{newsId}")
    public ResponseEntity<String> assignCategoriesToNews(@PathVariable Integer newsId) {
        try {
            News news = newsRepository.findById(newsId)
                    .orElse(null);

            if (news == null) {
                return ResponseEntity.notFound().build();
            }

            categoryAssignmentService.assignCategoriesToNews(news);

            return ResponseEntity.ok("Successfully assigned categories to news ID: " + newsId);

        } catch (Exception e) {
            System.err.println("Error assigning categories to news " + newsId + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error assigning categories: " + e.getMessage());
        }
    }

    /**
     * Get count of uncategorized news articles
     */
    @GetMapping("/uncategorized-count")
    public ResponseEntity<Integer> getUncategorizedNewsCount() {
        try {
            List<News> allNews = newsRepository.findAll();
            
            int uncategorizedCount = (int) allNews.stream()
                    .filter(news -> {
                        List<Integer> categoryIds = newsCategoryRepository.findByNewsId(news.getId())
                                .stream()
                                .map(cat -> cat.getCategoryId())
                                .toList();
                        return categoryIds.isEmpty();
                    })
                    .count();

            return ResponseEntity.ok(uncategorizedCount);

        } catch (Exception e) {
            System.err.println("Error counting uncategorized news: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
} 