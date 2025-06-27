package Application.externalNewsApi;

import java.time.LocalDateTime;

public class ExternalNewsApi {
    private int id;
    private String sourceName;
    private String apiKey;
    private String baseUrl;
    private Boolean status;
    private String lastAccessed;

    public ExternalNewsApi() {}

    public ExternalNewsApi(int id, String sourceName, String apiKey, String baseUrl, Boolean status, String lastAccessed) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(String lastAccessed) {
        this.lastAccessed = lastAccessed;
    }
}
