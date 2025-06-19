package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewAggrigationServer.model.ExternalNewsSource;
import com.server.NewAggrigationServer.repository.ExternalNewsSourceRepository;
import com.server.NewAggrigationServer.service.ExternalNewsSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalNewsSourceServiceImpl implements ExternalNewsSourceService {

    @Autowired
    private ExternalNewsSourceRepository externalNewsSourceRepository;

    @Override
    public List<ExternalNewsSourceDTO> getNewsSourceBySourceName(String SourceName) {
        return externalNewsSourceRepository.findBySourceName(SourceName).stream()
                .map(c -> {
                    ExternalNewsSourceDTO dto = new ExternalNewsSourceDTO();
                    dto.setId(c.getId());
                    dto.setSourceName(c.getSourceName());
                    dto.setApiKey(c.getApiKey());
                    dto.setBaseUrl(c.getBaseUrl());
                    dto.setStatus(c.getStatus());
                    dto.setLastAccessed(c.getLastAccessed());
                    System.out.println("API key: " + c.getApiKey());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void save(ExternalNewsSourceDTO api) {
        ExternalNewsSource externalNewsSource = new ExternalNewsSource();
        externalNewsSource.setId(api.getId());
        externalNewsSource.setSourceName(api.getSourceName());
        externalNewsSource.setSourceName(api.getSourceName());
        externalNewsSource.setStatus(api.getStatus());
        externalNewsSource.setLastAccessed(api.getLastAccessed());
        externalNewsSource.setBaseUrl(api.getBaseUrl());
        externalNewsSource.setApiKey(api.getApiKey());
        externalNewsSourceRepository.save(externalNewsSource);
    }
}