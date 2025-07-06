package com.server.NewsAggrigationServer.ExternalNewsAPI.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.server.NewsAggrigationServer.ExternalNewsAPI.service.NewsAPIClient;
import com.server.NewsAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewsAggrigationServer.model.News;
import com.server.NewsAggrigationServer.service.ExternalNewsSourceService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NewsAPIClientImpl implements NewsAPIClient {

    private final RestTemplate restTemplate;
    private final ExternalNewsSourceService externalNewsSourceService;

    public NewsAPIClientImpl(RestTemplate restTemplate,ExternalNewsSourceService externalNewsSourceService) {
        this.restTemplate = restTemplate;
        this.externalNewsSourceService  = externalNewsSourceService;
    }
    @Override
    public List<News> fetchNews() {
        List<ExternalNewsSourceDTO> api = externalNewsSourceService.getNewsSourceBySourceName("News API");
        URI uri = UriComponentsBuilder
                .fromUriString(api.get(0).getBaseUrl())
                .queryParam("apiKey", api.get(0).getApiKey())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        ResponseEntity<JsonNode> resp = restTemplate.exchange(uri, HttpMethod.GET,entity,JsonNode.class);


        JsonNode arr = resp.getBody().path("articles");
        if (!arr.isArray()) {
            // Set status to false when no news is fetched
            api.get(0).setStatus(false);
            api.get(0).setLastAccessed(LocalDateTime.now());
            externalNewsSourceService.save(api.get(0));
            return Collections.emptyList();
        }

        List<News> parsedArticles = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;

        for (JsonNode node : arr) {
            News a = new News();
            a.setTitle(node.path("title").asText(""));
            a.setDescription(node.path("description").asText(""));
            a.setUrl(node.path("url").asText(""));
            a.setPublishAt(
                    LocalDateTime.parse(node.path("publishedAt").asText(), fmt)
            );
            a.setSource(node.path("name").asText());
            a.setDisLikeCount(0);
            a.setLikeCount(0);
            parsedArticles.add(a);
        }

        // If no articles were parsed, set status to false
        if (parsedArticles.isEmpty()) {
            api.get(0).setStatus(false);
        } else {
            // If articles were successfully fetched, ensure status is true
            api.get(0).setStatus(true);
        }
        
        api.get(0).setLastAccessed(LocalDateTime.now());
        externalNewsSourceService.save(api.get(0));

        return parsedArticles;
    }
}
