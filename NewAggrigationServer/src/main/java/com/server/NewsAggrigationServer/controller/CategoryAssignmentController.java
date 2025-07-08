package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.service.CategoryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-assignment")
public class CategoryAssignmentController {

    @Autowired
    private CategoryAssignmentService categoryAssignmentService;

    @PostMapping("/assign-to-uncategorized")
    public String assignCategoriesToUncategorizedNews() {
        List<News> uncategorizedNews = categoryAssignmentService.findUncategorizedNews();
        if (uncategorizedNews.isEmpty()) {
            return "No uncategorized news found.";
        }
        categoryAssignmentService.assignCategoriesToNewsList(uncategorizedNews);
        return "Successfully assigned categories to " + uncategorizedNews.size() + " news articles.";
    }

    @PostMapping("/assign/{newsId}")
    public String assignCategoriesToNews(@PathVariable Integer newsId) {
        News news = categoryAssignmentService.findUncategorizedNews().stream()
                .filter(n -> n.getId().equals(newsId))
                .findFirst()
                .orElse(null);
        if (news == null) {
            return "News not found or already categorized.";
        }
        categoryAssignmentService.assignCategoriesToNews(news);
        return "Successfully assigned categories to news ID: " + newsId;
    }

    @GetMapping("/uncategorized-count")
    public int getUncategorizedNewsCount() {
        return categoryAssignmentService.countUncategorizedNews();
    }
} 