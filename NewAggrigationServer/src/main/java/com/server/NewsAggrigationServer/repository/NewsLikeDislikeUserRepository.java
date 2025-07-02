package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsLikeDislikeUserRepository extends JpaRepository<NewsLikeDislikeUser, Integer> {
    NewsLikeDislikeUser findByNewsIdAndUserId(Integer newsId, Integer userId);
}

