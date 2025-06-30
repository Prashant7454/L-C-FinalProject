package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.CategoryKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryKeywordRepository extends JpaRepository<CategoryKeyword, Integer> {
}
