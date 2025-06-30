package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {
    List<NewsCategory> findByNewsId(Integer newsId);
    List<NewsCategory> findByCategoryId(Integer categoryId);
    void deleteByNewsIdAndCategoryId(Integer newsId, Integer categoryId);
    void deleteByNewsId(Integer newsId);
    NewsCategory save(NewsCategory newsCategory);
}

