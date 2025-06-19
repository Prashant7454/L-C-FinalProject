package com.server.NewAggrigationServer.repository;

import com.server.NewAggrigationServer.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {
    List<NewsCategory> findByNewsId(Integer newsId);
    void deleteByNewsIdAndCategoryId(Integer newsId, Integer categoryId);
    void deleteByNewsId(Integer newsId);
}

