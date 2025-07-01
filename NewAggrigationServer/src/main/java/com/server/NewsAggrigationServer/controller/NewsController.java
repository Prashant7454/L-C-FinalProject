package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.service.NewsService;
import com.server.NewsAggrigationServer.service.SavedNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    private SavedNewsService savedNewsService;

    public NewsController(SavedNewsService savedNewsService){
        this.savedNewsService = savedNewsService;
    }


    @PostMapping
    public NewsDTO createNews(@RequestBody NewsDTO dto) {
        return newsService.createNews(dto);
    }

    @PutMapping("/{id}")
    public NewsDTO updateNews(@PathVariable Integer id, @RequestBody NewsDTO dto) {
        return newsService.updateNews(id, dto);
    }

    @GetMapping("/{id}")
    public NewsDTO getNewsById(@PathVariable Integer id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/list")
    public List<NewsDTO> getNewsByIdList(@RequestBody List<Integer> ids) {
        return newsService.getNewsByIds(ids);
    }

    @GetMapping
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/search")
    public List<NewsDTO> searchNews(@RequestParam String searchString) {
        return newsService.getNews(searchString);
    }

    @PostMapping("/add-multiple")
    public ResponseEntity<String> addMultipleNews(@RequestBody List<NewsDTO> newsList) {
        newsService.addMultipleNews(newsList);
        return ResponseEntity.ok("News added successfully");
    }

    @GetMapping("/save/{userId}")
    public List<NewsDTO> getSavedNews(@PathVariable Integer userId) {
        return savedNewsService.getSavedNewsByUserId(userId);
    }

    @PostMapping("/today")
    public List<NewsDTO> getTodayNewsByIds(@RequestBody List<Integer> ids) {
        return newsService.getTodayNewsByIds(ids);
    }

    @PostMapping("/date-range")
    public List<NewsDTO> getNewsByIdsAndDateRange( @RequestBody DateRangeNewsRequest dateRangeNewsRequest) {
        LocalDateTime startDate = LocalDateTime.parse(dateRangeNewsRequest.getStart());
        LocalDateTime endDate = LocalDateTime.parse(dateRangeNewsRequest.getEnd());
        return newsService.getNewsByIdsAndDateRange(dateRangeNewsRequest.getIds(), startDate, endDate);
    }

    private static class DateRangeNewsRequest {
        private List<Integer> ids;
        private String start;
        private String end;

        public List<Integer> getIds() {
            return ids;
        }

        public void setIds(List<Integer> ids) {
            this.ids = ids;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }
    }
}
