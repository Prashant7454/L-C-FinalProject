package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.CategoryKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryKeywordRepository extends JpaRepository<CategoryKeyword, Integer> {
    List<CategoryKeyword> findByCategoryId(Integer categoryId);
    List<CategoryKeyword> findByKeywordId(Integer keywordId);
}
