package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.externalNewsApi.ExternalNewsApi;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsLikeOrDislikeUser.NewsLikeDislikeUser;
import Application.newsLikeOrDislikeUser.service.NewsLikeDislikeUserService;

import java.util.Objects;

public class LikeArticleAction implements MenuAction {
    private Integer newsId;
    private final NewsLikeDislikeUserService newsLikeDislikeUserService = new NewsLikeDislikeUserService();
    private final NewsService newsService = new NewsService();

    public LikeArticleAction(Integer newsId){
        this.newsId = newsId;
    }

    @Override
    public String getName() {
        return "Like Article";
    }

    @Override
    public void execute(LoginResponse response) {
        Integer userId = response.getUserId();
        NewsLikeDislikeUser oldNewsLikeDislikeUser = null;
        try{
            oldNewsLikeDislikeUser = newsLikeDislikeUserService.getReaction(newsId, userId);
        }
        catch (Exception e){
            System.out.println("Message: " + e.getMessage());
        }
        if(oldNewsLikeDislikeUser == null){
            oldNewsLikeDislikeUser = new NewsLikeDislikeUser();
        }
        if(oldNewsLikeDislikeUser.isLiked() == 1){
            System.out.println("News is all ready liked");
            return;
        }
        NewsLikeDislikeUser newNewsLikeDislikeReaction = new NewsLikeDislikeUser();
        newNewsLikeDislikeReaction.setNewsId(newsId);
        newNewsLikeDislikeReaction.setUserId(userId);
        newNewsLikeDislikeReaction.setLiked(1);
        newNewsLikeDislikeReaction.setDisliked(0);
        if(Objects.equals(oldNewsLikeDislikeUser.getNewsId(), newsId)){
            newNewsLikeDislikeReaction.setId(oldNewsLikeDislikeUser.getId());
        }


        System.out.println("newNewsLikeDislikeReaction: " + newNewsLikeDislikeReaction);

        try{
            newNewsLikeDislikeReaction = newsLikeDislikeUserService.saveOrUpdateReaction(newNewsLikeDislikeReaction);
        }
        catch (Exception e){
            System.out.println("Message: " + e.getMessage());
        }
        News news = new News();
        try{
            news =  newsService.getNewsById(newsId);
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        news.setLikeCount(news.getLikeCount()+1);
        if(oldNewsLikeDislikeUser.isDisliked() == 1){
            news.setDisLikeCount(news.getDisLikeCount()-1);
        }
        try{
            newsService.updateNewsLikeAndDisLikeCount(news);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
