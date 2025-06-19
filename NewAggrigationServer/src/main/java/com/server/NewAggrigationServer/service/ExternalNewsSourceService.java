package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.ExternalNewsSourceDTO;

import java.util.List;

public interface ExternalNewsSourceService {
    List<ExternalNewsSourceDTO> getNewsSourceBySourceName(String userName);
    void save(ExternalNewsSourceDTO api);
}