package Application.command.userDashboard.articleFilterDashboard.articleDashboard.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsLikeOrDislikeUser.NewsLikeDislikeUser;
import Application.newsLikeOrDislikeUser.service.NewsLikeDislikeUserService;

import java.util.Objects;

public class DisLikeArticleAction implements MenuAction {
    private Integer newsId;
    private final NewsLikeDislikeUserService newsLikeDislikeUserService = new NewsLikeDislikeUserService();
    private final NewsService newsService = new NewsService();

    public DisLikeArticleAction(Integer newsId){
        this.newsId = newsId;
    }
    @Override
    public String getName() {
        return "DisLike Article";
    }

    @Override
    public void execute(LoginResponse response) {
        Integer userId = response.getUserId();
        NewsLikeDislikeUser oldNewsLikeDislikeUser = new NewsLikeDislikeUser();
        try{
            oldNewsLikeDislikeUser = newsLikeDislikeUserService.getReaction(newsId, userId);
        }
        catch (Exception e){
            System.out.println("Message: " + e.getMessage());
        }
        if(oldNewsLikeDislikeUser.isDisliked() == 1){
            System.out.println("News is all ready disliked");
            return;
        }
        NewsLikeDislikeUser newNewsLikeDislikeReaction = new NewsLikeDislikeUser();
        newNewsLikeDislikeReaction.setNewsId(newsId);
        newNewsLikeDislikeReaction.setUserId(userId);
        newNewsLikeDislikeReaction.setDisliked(1);
        newNewsLikeDislikeReaction.setLiked(0);
        if(Objects.equals(oldNewsLikeDislikeUser.getNewsId(), newsId)){
            newNewsLikeDislikeReaction.setId(oldNewsLikeDislikeUser.getId());
        }

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
        news.setDisLikeCount(news.getDisLikeCount()+1);
        if(oldNewsLikeDislikeUser.isLiked() == 1){
            news.setLikeCount(news.getLikeCount()-1);
        }
        try{
            newsService.updateNewsLikeAndDisLikeCount(news);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
