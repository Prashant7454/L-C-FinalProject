package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.ExternalNewsSource;
import com.server.NewsAggrigationServer.repository.ExternalNewsSourceRepository;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalNewsSourceServiceImpl implements ExternalNewsSourceService {

    private static final Logger log = LoggerFactory.getLogger(ExternalNewsSourceServiceImpl.class);

    @Autowired
    private ExternalNewsSourceRepository externalNewsSourceRepository;

    @Override
    public List<ExternalNewsSourceDTO> getNewsSourceBySourceName(String SourceName) {
        log.info("Fetching news sources by sourceName: {}", SourceName);
        List<ExternalNewsSource> sources = externalNewsSourceRepository.findBySourceName(SourceName);
        log.info("Fetched {} news sources for sourceName {}", sources.size(), SourceName);
        return sources.stream()
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
        log.info("Saving external news source: {}", api);
        try {
            ExternalNewsSource externalNewsSource = new ExternalNewsSource();
            externalNewsSource.setId(api.getId());
            externalNewsSource.setSourceName(api.getSourceName());
            externalNewsSource.setStatus(api.getStatus());
            externalNewsSource.setLastAccessed(api.getLastAccessed());
            externalNewsSource.setBaseUrl(api.getBaseUrl());
            externalNewsSource.setApiKey(api.getApiKey());
            externalNewsSourceRepository.save(externalNewsSource);
            log.info("External news source saved successfully: {}", api.getSourceName());
        } catch (Exception ex) {
            log.error("Error saving external news source: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<ExternalNewsSourceDTO> getAll() {
        log.info("Fetching all external news sources");
        List<ExternalNewsSource> sources = externalNewsSourceRepository.findAll();
        log.info("Fetched {} external news sources", sources.size());
        return sources.stream()
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
        log.info("Updating external news source with id: {} and dto: {}", id, dto);
        try {
            ExternalNewsSource externalNewsSource = externalNewsSourceRepository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("External news source not found with ID: {}", id);
                        return new ResourceNotFoundException("News not found with ID: " + id);
                    });
            externalNewsSource.setApiKey(dto.getApiKey());
            ExternalNewsSource updated = externalNewsSourceRepository.save(externalNewsSource);
            dto.setLastAccessed(updated.getLastAccessed());
            dto.setStatus(updated.getStatus());
            dto.setId(updated.getId());
            dto.setSourceName(updated.getSourceName());
            dto.setApiKey(updated.getApiKey());
            dto.setBaseUrl(updated.getBaseUrl());
            log.info("External news source updated: {}", dto);
            return dto;
        } catch (Exception ex) {
            log.error("Error updating external news source: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public ExternalNewsSourceDTO getExternalSourceById(Integer id) {
        log.info("Fetching external news source by id: {}", id);
        ExternalNewsSource externalNewsSource = externalNewsSourceRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("External news source not found with ID: {}", id);
                    return new ResourceNotFoundException("News not found with ID: " + id);
                });
        ExternalNewsSourceDTO dto = new ExternalNewsSourceDTO();
        dto.setId(externalNewsSource.getId());
        dto.setSourceName(externalNewsSource.getSourceName());
        dto.setStatus(externalNewsSource.getStatus());
        dto.setLastAccessed(externalNewsSource.getLastAccessed());
        dto.setApiKey(externalNewsSource.getApiKey());
        dto.setBaseUrl(externalNewsSource.getBaseUrl());
        log.info("External news source found: {}", dto);
        return dto;
    }
}