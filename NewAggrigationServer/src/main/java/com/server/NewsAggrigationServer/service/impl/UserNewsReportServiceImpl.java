package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.UserNewsReportDTO;
import com.server.NewsAggrigationServer.model.UserNewsReport;
import com.server.NewsAggrigationServer.repository.UserNewsReportRepository;
import com.server.NewsAggrigationServer.service.UserNewsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserNewsReportServiceImpl implements UserNewsReportService {

    @Autowired
    private UserNewsReportRepository repository;

    private UserNewsReportDTO mapToDTO(UserNewsReport report) {
        UserNewsReportDTO dto = new UserNewsReportDTO();
        dto.setId(report.getId());
        dto.setUserId(report.getUserId());
        dto.setNewsId(report.getNewsId());
        dto.setIsReported(report.getIsReported());
        return dto;
    }

    private UserNewsReport mapToEntity(UserNewsReportDTO dto) {
        UserNewsReport report = new UserNewsReport();
        report.setId(dto.getId());
        report.setUserId(dto.getUserId());
        report.setNewsId(dto.getNewsId());
        report.setIsReported(dto.getIsReported());
        return report;
    }

    @Override
    public UserNewsReportDTO reportNews(UserNewsReportDTO dto) {
        Optional<UserNewsReport> existing = repository.findByUserIdAndNewsId(dto.getUserId(), dto.getNewsId());

        if (existing.isPresent()) {
            UserNewsReport report = existing.get();
            report.setIsReported(dto.getIsReported());
            return mapToDTO(repository.save(report));
        }

        return mapToDTO(repository.save(mapToEntity(dto)));
    }

    @Override
    public List<UserNewsReportDTO> getAllReports() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserNewsReportDTO getReportByUserAndNews(Integer userId, Integer newsId) {
        Optional<UserNewsReport> report = repository.findByUserIdAndNewsId(userId, newsId);
        return report.map(this::mapToDTO).orElse(null);
    }
}
