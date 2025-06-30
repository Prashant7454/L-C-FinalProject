package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;

import java.util.List;

public interface ExternalNewsSourceService {
    List<ExternalNewsSourceDTO> getNewsSourceBySourceName(String userName);
    void save(ExternalNewsSourceDTO api);
    List<ExternalNewsSourceDTO> getAll();
    ExternalNewsSourceDTO updateExternalNewsSource(Integer id, ExternalNewsSourceDTO dto);
    ExternalNewsSourceDTO getExternalSourceById(Integer id);
}