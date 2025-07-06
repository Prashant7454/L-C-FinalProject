package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.UserNewsReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserNewsReportRepository extends JpaRepository<UserNewsReport, Integer> {
    Optional<UserNewsReport> findByUserIdAndNewsId(Integer userId, Integer newsId);
}
