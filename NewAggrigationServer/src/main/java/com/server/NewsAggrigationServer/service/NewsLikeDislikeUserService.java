package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NewsLikeDislikeUserDTO;
import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;

import java.util.List;
import java.util.Optional;

public interface NewsLikeDislikeUserService {
    NewsLikeDislikeUser saveOrUpdate(NewsLikeDislikeUserDTO dto);

    NewsLikeDislikeUser getByNewsIdAndUserId(Integer newsId, Integer userId);

    List<NewsDTO> getLikedNewsByUserId(Integer userId);

    List<NewsDTO> getDislikedNewsByUserId(Integer userId);
}
