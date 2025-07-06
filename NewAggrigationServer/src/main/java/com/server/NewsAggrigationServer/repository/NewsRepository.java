package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query("SELECT n FROM News n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :searchString, '%')) " +
            "OR LOWER(n.description) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    List<News> search(String searchString);
    
    @Query("SELECT n FROM News n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :searchString, '%')) " +
            "OR LOWER(n.description) LIKE LOWER(CONCAT('%', :searchString, '%')) AND n.isHide = 0")
    List<News> searchVisible(String searchString);
    
    List<News> findByIdIn(List<Integer> ids);
    List<News> findByIdInAndPublishAtBetween(List<Integer> ids, LocalDateTime start, LocalDateTime end);
    List<News> findByReportCountGreaterThan(Integer count);
    
    // Find all visible news (isHide = 0)
    List<News> findByIsHide(Integer isHide);
    
    // Find visible news by IDs
    List<News> findByIdInAndIsHide(List<Integer> ids, Integer isHide);
    
    // Find visible news by IDs and date range
    List<News> findByIdInAndPublishAtBetweenAndIsHide(List<Integer> ids, LocalDateTime start, LocalDateTime end, Integer isHide);
    
    // Find news that are not in hidden categories
    @Query("SELECT DISTINCT n FROM News n JOIN NewsCategory nc ON n.id = nc.newsId " +
           "JOIN Category c ON nc.categoryId = c.id " +
           "WHERE c.isHide = 0 AND n.isHide = 0")
    List<News> findNewsInVisibleCategories();
    
    // Find news by IDs that are in visible categories
    @Query("SELECT DISTINCT n FROM News n JOIN NewsCategory nc ON n.id = nc.newsId " +
           "JOIN Category c ON nc.categoryId = c.id " +
           "WHERE n.id IN :newsIds AND c.isHide = 0 AND n.isHide = 0")
    List<News> findNewsByIdsInVisibleCategories(List<Integer> newsIds);
}
