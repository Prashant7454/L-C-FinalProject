package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NewsLikeDislikeUserDTO;
import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import com.server.NewsAggrigationServer.repository.NewsLikeDislikeUserRepository;
import com.server.NewsAggrigationServer.service.NewsLikeDislikeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsLikeDislikeUserServiceImpl implements NewsLikeDislikeUserService {

    private final NewsLikeDislikeUserRepository newsLikeDislikeUserRepository;

    @Autowired
    public NewsLikeDislikeUserServiceImpl(NewsLikeDislikeUserRepository repository) {
        this.newsLikeDislikeUserRepository = repository;
    }

    @Override
    public NewsLikeDislikeUser saveOrUpdate(NewsLikeDislikeUserDTO dto) {
        NewsLikeDislikeUser newsLikeDislikeUser = new NewsLikeDislikeUser();
        newsLikeDislikeUser.setId(dto.getId());
        newsLikeDislikeUser.setNewsId(dto.getNewsId());
        newsLikeDislikeUser.setUserId(dto.getUserId());
        newsLikeDislikeUser.setLiked(dto.getLiked());
        newsLikeDislikeUser.setDisliked(dto.getDisliked());

        return newsLikeDislikeUserRepository.save(newsLikeDislikeUser);
    }

    @Override
    public NewsLikeDislikeUser getByNewsIdAndUserId(Integer newsId, Integer userId) {
        return newsLikeDislikeUserRepository.findByNewsIdAndUserId(newsId, userId);
    }
}
