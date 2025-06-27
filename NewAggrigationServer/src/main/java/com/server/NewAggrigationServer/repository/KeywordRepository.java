package com.server.NewAggrigationServer.repository;

import com.server.NewAggrigationServer.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
}
