package com.server.NewAggrigationServer.repository;

import com.server.NewAggrigationServer.model.ExternalNewsSource;
import com.server.NewAggrigationServer.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalNewsSourceRepository extends JpaRepository<ExternalNewsSource,Integer> {
    List<ExternalNewsSource> findBySourceName(String sourceName);
    List<ExternalNewsSource> findByIdIn(List<Integer> ids);
}