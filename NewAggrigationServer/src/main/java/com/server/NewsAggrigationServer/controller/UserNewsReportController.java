package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.UserNewsReportDTO;
import com.server.NewsAggrigationServer.service.UserNewsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-news-report")
public class UserNewsReportController {

    @Autowired
    private UserNewsReportService reportService;

    // Report or update a report
    @PostMapping("/report")
    public UserNewsReportDTO reportNews(@RequestBody UserNewsReportDTO dto) {
        return reportService.reportNews(dto);
    }

    // Get all reports
    @GetMapping
    public List<UserNewsReportDTO> getAllReports() {
        return reportService.getAllReports();
    }

    // Get specific report by user and news
    @GetMapping("/check")
    public UserNewsReportDTO getReportByUserAndNews(@RequestParam Integer userId, @RequestParam Integer newsId) {
        return reportService.getReportByUserAndNews(userId, newsId);
    }
}
