package Application.newsLikeOrDislikeUser.service;

import Application.newsLikeOrDislikeUser.NewsLikeDislikeUser;
import Application.newsLikeOrDislikeUser.controller.NewsLikeDislikeUserController;

public class NewsLikeDislikeUserService {
    private final NewsLikeDislikeUserController newsLikeDislikeUserController;

    public NewsLikeDislikeUserService(){
        newsLikeDislikeUserController = new NewsLikeDislikeUserController();
    }
    public NewsLikeDislikeUser getReaction(Integer newsId, Integer userId) throws Exception {
        return newsLikeDislikeUserController.getReaction(newsId,userId);
    }

    public NewsLikeDislikeUser saveOrUpdateReaction(NewsLikeDislikeUser newsLikeDislikeUser) throws Exception{
        return newsLikeDislikeUserController.saveOrUpdateReaction(newsLikeDislikeUser);
    }
}
