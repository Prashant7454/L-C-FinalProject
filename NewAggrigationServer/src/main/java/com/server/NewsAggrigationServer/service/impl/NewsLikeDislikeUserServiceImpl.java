package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.NewsDTO;
import com.server.NewsAggrigationServer.dto.NewsLikeDislikeUserDTO;
import com.server.NewsAggrigationServer.model.NewsLikeDislikeUser;
import com.server.NewsAggrigationServer.repository.NewsLikeDislikeUserRepository;
import com.server.NewsAggrigationServer.service.NewsLikeDislikeUserService;
import com.server.NewsAggrigationServer.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsLikeDislikeUserServiceImpl implements NewsLikeDislikeUserService {

    private final NewsLikeDislikeUserRepository newsLikeDislikeUserRepository;
    private final NewsService newsService;

    @Autowired
    public NewsLikeDislikeUserServiceImpl(NewsLikeDislikeUserRepository repository, NewsService newsService) {
        this.newsLikeDislikeUserRepository = repository;
        this.newsService = newsService;
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

    @Override
    public List<NewsDTO> getLikedNewsByUserId(Integer userId) {
        List<NewsLikeDislikeUser> likedReactions = newsLikeDislikeUserRepository.findByUserIdAndLiked(userId, 1);
        List<Integer> newsIds = likedReactions.stream()
                .map(NewsLikeDislikeUser::getNewsId)
                .collect(Collectors.toList());
        
        if (newsIds.isEmpty()) {
            return List.of();
        }
        
        return newsService.getNewsByIds(newsIds);
    }

    @Override
    public List<NewsDTO> getDislikedNewsByUserId(Integer userId) {
        List<NewsLikeDislikeUser> dislikedReactions = newsLikeDislikeUserRepository.findByUserIdAndDisliked(userId, 1);
        List<Integer> newsIds = dislikedReactions.stream()
                .map(NewsLikeDislikeUser::getNewsId)
                .collect(Collectors.toList());
        
        if (newsIds.isEmpty()) {
            return List.of();
        }
        
        return newsService.getNewsByIds(newsIds);
    }
}
