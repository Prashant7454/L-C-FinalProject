package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.ExternalNewsSource;
import com.server.NewsAggrigationServer.repository.ExternalNewsSourceRepository;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
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

    @Override
    public List<ExternalNewsSourceDTO> getAll() {
        return externalNewsSourceRepository.findAll().stream()
                .map(c -> {
                    ExternalNewsSourceDTO dto = new ExternalNewsSourceDTO();
                    dto.setId(c.getId());
                    dto.setSourceName(c.getSourceName());
                    dto.setApiKey(c.getApiKey());
                    dto.setBaseUrl(c.getBaseUrl());
                    dto.setStatus(c.getStatus());
                    dto.setLastAccessed(c.getLastAccessed());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public ExternalNewsSourceDTO updateExternalNewsSource(Integer id, ExternalNewsSourceDTO dto) {
        ExternalNewsSource externalNewsSource = externalNewsSourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + id));
        externalNewsSource.setApiKey(dto.getApiKey());
        ExternalNewsSource updated = externalNewsSourceRepository.save(externalNewsSource);
        ExternalNewsSourceDTO externalNewsSourceDTO = new ExternalNewsSourceDTO();
        dto.setLastAccessed(updated.getLastAccessed());
        dto.setStatus(updated.getStatus());
        dto.setId(updated.getId());
        dto.setSourceName(updated.getSourceName());
        dto.setApiKey(updated.getApiKey());
        dto.setBaseUrl(updated.getBaseUrl());
        System.out.println(dto);
        return dto;
    }

    @Override
    public ExternalNewsSourceDTO getExternalSourceById(Integer id) {
        ExternalNewsSource externalNewsSource = externalNewsSourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + id));
        ExternalNewsSourceDTO dto = new ExternalNewsSourceDTO();
        dto.setId(externalNewsSource.getId());
        dto.setSourceName(externalNewsSource.getSourceName());
        dto.setStatus(externalNewsSource.getStatus());
        dto.setLastAccessed(externalNewsSource.getLastAccessed());
        dto.setApiKey(externalNewsSource.getApiKey());
        dto.setBaseUrl(externalNewsSource.getBaseUrl());
        return dto;
    }
}