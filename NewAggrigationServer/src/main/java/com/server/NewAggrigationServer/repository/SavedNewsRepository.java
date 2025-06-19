package com.server.NewAggrigationServer.repository;

import com.server.NewAggrigationServer.model.SavedNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedNewsRepository extends JpaRepository<SavedNews, Integer> {
    List<SavedNews> findByUserId(Integer userId);
    boolean existsByUserIdAndNewsId(Integer userId, Integer newsId);
    void deleteByUserIdAndNewsId(Integer userId, Integer newsId);
}

