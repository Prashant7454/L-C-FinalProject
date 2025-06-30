package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.ExternalNewsSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalNewsSourceRepository extends JpaRepository<ExternalNewsSource,Integer> {
    List<ExternalNewsSource> findBySourceName(String sourceName);
    List<ExternalNewsSource> findByIdIn(List<Integer> ids);
}