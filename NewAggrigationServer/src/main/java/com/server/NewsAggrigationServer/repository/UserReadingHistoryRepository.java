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
    
    /**
     * Find reading history for a specific user
     */
    List<UserReadingHistory> findByUserIdOrderByReadAtDesc(Integer userId);
    
    /**
     * Find reading history for a specific user with pagination
     */
    Page<UserReadingHistory> findByUserIdOrderByReadAtDesc(Integer userId, Pageable pageable);
    
    /**
     * Check if a user has read a specific article
     */
    boolean existsByUserIdAndNewsId(Integer userId, Integer newsId);
    
    /**
     * Find reading history for a user within a time period
     */
    List<UserReadingHistory> findByUserIdAndReadAtBetweenOrderByReadAtDesc(
        Integer userId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Get count of articles read by a user
     */
    long countByUserId(Integer userId);
    
    /**
     * Get recent reading history for a user (last 30 days)
     */
    @Query("SELECT h FROM UserReadingHistory h WHERE h.userId = :userId AND h.readAt >= :startDate ORDER BY h.readAt DESC")
    List<UserReadingHistory> findRecentReadingHistory(@Param("userId") Integer userId, @Param("startDate") LocalDateTime startDate);
    
    /**
     * Delete reading history for a specific user
     */
    void deleteByUserId(Integer userId);
    
    /**
     * Delete reading history for a specific news article
     */
    void deleteByNewsId(Integer newsId);
} 