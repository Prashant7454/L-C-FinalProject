package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
}
