package Application.command.userDashboard.articleFilterDashboard.articleDashboard.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.savedNews.service.SavedNewsService;

public class UnsaveArticleAction implements MenuAction {

    private final SavedNewsService savedNewsService = new SavedNewsService();
    private final Integer newsId;

    public UnsaveArticleAction(Integer newsId){
        this.newsId = newsId;
    }
    @Override
    public String getName() {
        return "Unsave Article";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            boolean success = savedNewsService.unsaveNews(response.getUserId(), newsId);
            if (success) {
                System.out.println("Article unsaved successfully!");
            } else {
                System.out.println("Failed to unsave article.");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}