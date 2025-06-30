package com.server.NewAggrigationServer.repository;

import com.server.NewAggrigationServer.model.News;
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
    List<News> findByIdIn(List<Integer> ids);
    List<News> findByIdInAndPublishAtBetween(List<Integer> ids, LocalDateTime start, LocalDateTime end);
}
