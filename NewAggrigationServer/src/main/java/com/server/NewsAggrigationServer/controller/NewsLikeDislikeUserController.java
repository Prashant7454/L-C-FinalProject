package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.NewsLikeDislikeUserDTO;
import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import com.server.NewsAggrigationServer.service.NewsLikeDislikeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news-reactions")
public class NewsLikeDislikeUserController {

    @Autowired
    private NewsLikeDislikeUserService newsLikeDislikeUserService;

    @PostMapping
    public NewsLikeDislikeUser likeOrDislike(@RequestBody NewsLikeDislikeUserDTO dto) {
        return newsLikeDislikeUserService.saveOrUpdate(dto);
    }

    @GetMapping("/{newsId}/{userId}")
    public NewsLikeDislikeUser getReaction(@PathVariable Integer newsId, @PathVariable Integer userId) {
        return newsLikeDislikeUserService.getByNewsIdAndUserId(newsId, userId);
    }
}
