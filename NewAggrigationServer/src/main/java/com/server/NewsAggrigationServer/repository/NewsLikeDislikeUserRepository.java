package com.server.NewsAggrigationServer.repository;

import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsLikeDislikeUserRepository extends JpaRepository<NewsLikeDislikeUser, Integer> {
    NewsLikeDislikeUser findByNewsIdAndUserId(Integer newsId, Integer userId);
    
    // New methods for personalization
    List<NewsLikeDislikeUser> findByUserIdAndLiked(Integer userId, Integer liked);
    List<NewsLikeDislikeUser> findByUserIdAndDisliked(Integer userId, Integer disliked);
}

