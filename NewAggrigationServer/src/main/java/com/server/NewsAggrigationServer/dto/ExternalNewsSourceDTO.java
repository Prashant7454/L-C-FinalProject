package com.server.NewsAggrigationServer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ExternalNewsSourceDTO {

    private int id;
    private String sourceName;
    private String apiKey;
    private String baseUrl;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime lastAccessed;

    public ExternalNewsSourceDTO() {}

    public ExternalNewsSourceDTO(int id, String sourceName, String apiKey, String baseUrl, Integer status, LocalDateTime lastAccessed) {
        this.id = id;
        this.sourceName = sourceName;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.status = status;
        this.lastAccessed = lastAccessed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    @Override
    public String toString() {
        return "ExternalNewsSourceDTO{" +
                "id=" + id +
                ", sourceName='" + sourceName + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", status=" + status +
                ", lastAccessed=" + lastAccessed +
                '}';
    }
}