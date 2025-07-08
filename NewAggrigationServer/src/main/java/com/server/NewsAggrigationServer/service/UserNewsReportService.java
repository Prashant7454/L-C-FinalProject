package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.UserNewsReportDTO;

import java.util.List;

public interface UserNewsReportService {
    UserNewsReportDTO reportNews(UserNewsReportDTO dto);

    List<UserNewsReportDTO> getAllReports();

    UserNewsReportDTO getReportByUserAndNews(Integer userId, Integer newsId);
}
