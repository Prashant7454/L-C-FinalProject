package Application.savedNews.service;

import Application.savedNews.SavedNews;
import Application.savedNews.controller.SavedNewsController;

public class SavedNewsService {

    SavedNewsController savedNewsController;

    public SavedNewsService(){
        savedNewsController = new SavedNewsController();
    }

    public SavedNews saveNews(Integer userId, Integer newsId) throws Exception{
        SavedNews savedNews = new SavedNews();
        savedNews.setNewsId(newsId);
        savedNews.setUserId(userId);
        return savedNewsController.saveNews(savedNews);
    }

    public boolean unsaveNews(Integer userId, Integer newsId) throws Exception {
        return savedNewsController.unsaveNews(userId, newsId);
    }
}
