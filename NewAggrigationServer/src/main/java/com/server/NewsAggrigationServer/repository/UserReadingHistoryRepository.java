package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.UserReadingHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserReadingHistoryRepository extends JpaRepository<UserReadingHistory, Integer> {
    List<UserReadingHistory> findByUserIdOrderByReadAtDesc(Integer userId);

    Page<UserReadingHistory> findByUserIdOrderByReadAtDesc(Integer userId, Pageable pageable);

    boolean existsByUserIdAndNewsId(Integer userId, Integer newsId);

    List<UserReadingHistory> findByUserIdAndReadAtBetweenOrderByReadAtDesc(
        Integer userId, LocalDateTime startDate, LocalDateTime endDate);

    long countByUserId(Integer userId);

    @Query("SELECT h FROM UserReadingHistory h WHERE h.userId = :userId AND h.readAt >= :startDate ORDER BY h.readAt DESC")
    List<UserReadingHistory> findRecentReadingHistory(@Param("userId") Integer userId, @Param("startDate") LocalDateTime startDate);

    void deleteByUserId(Integer userId);

    void deleteByNewsId(Integer newsId);
} 