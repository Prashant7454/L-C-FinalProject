package com.server.NewAggrigationServer.ExternalNewsAPI.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.server.NewAggrigationServer.ExternalNewsAPI.service.TheNewsAPIClient;
import com.server.NewAggrigationServer.dto.ExternalNewsSourceDTO;
import com.server.NewAggrigationServer.model.Category;
import com.server.NewAggrigationServer.model.News;
import com.server.NewAggrigationServer.model.NewsCategory;
import com.server.NewAggrigationServer.repository.CategoryRepository;
import com.server.NewAggrigationServer.repository.NewsCategoryRepository;
import com.server.NewAggrigationServer.repository.NewsRepository;
import com.server.NewAggrigationServer.service.ExternalNewsSourceService;
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
public class TheNewsAPIClientImpl implements TheNewsAPIClient {

    private final RestTemplate restTemplate;
    private final ExternalNewsSourceService externalNewsSourceService;
    private final CategoryRepository categoryRepository;
    private final NewsRepository articleRepository;
    private final NewsCategoryRepository newsCategoryRepository;

    public TheNewsAPIClientImpl(RestTemplate restTemplate,ExternalNewsSourceService externalNewsSourceService, CategoryRepository categoryRepository, NewsRepository articleRepository, NewsCategoryRepository newsCategoryRepository) {
        this.restTemplate = restTemplate;
        this.externalNewsSourceService  = externalNewsSourceService;
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
        this.newsCategoryRepository = newsCategoryRepository;

    }
    @Override
    public List<News> fetchNews() {
        List<ExternalNewsSourceDTO> api = externalNewsSourceService.getNewsSourceBySourceName("The News API");
        URI uri = UriComponentsBuilder
                .fromUriString(api.get(0).getBaseUrl())
                .queryParam("api_token", api.get(0).getApiKey())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        ResponseEntity<JsonNode> resp = restTemplate.exchange(uri, HttpMethod.GET,entity,JsonNode.class);


        JsonNode arr = resp.getBody().path("data");
        if (!arr.isArray()) return Collections.emptyList();

        List<News> parsedArticles = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;

        for (JsonNode node : arr) {
            News a = new News();
            a.setTitle(node.path("title").asText(""));
            a.setDescription(node.path("description").asText(""));
            a.setUrl(node.path("url").asText(""));
            a.setPublishAt(
                    LocalDateTime.parse(node.path("published_at").asText(), fmt)
            );
            a.setSource(node.path("source").asText());
            parsedArticles.add(a);
            JsonNode categoryArray = node.path("categories");
            News news = articleRepository.save(a);
            if (categoryArray.isArray()) {
                List<String> categoryNames = new ArrayList<>();
                for (JsonNode cat : categoryArray) {
                    categoryNames.add(cat.asText());
                }

                for (String catName : categoryNames) {
                    System.out.println("Name: " + catName);
                    Category category = categoryRepository.findByName(catName);
                    if(category == null){
                        category = categoryRepository.save(new Category(catName));
                    }
                    NewsCategory newsCategory = new NewsCategory();
                    newsCategory.setNewsId(news.getId());
                    newsCategory.setCategoryId(category.getId());
                    newsCategoryRepository.save(newsCategory);
                }
            }

        }

        api.get(0).setLastAccessed(LocalDateTime.now());
        externalNewsSourceService.save(api.get(0));

        return parsedArticles;
    }
}
