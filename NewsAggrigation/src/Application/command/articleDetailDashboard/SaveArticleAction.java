package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.savedNews.service.SavedNewsService;

public class SaveArticleAction implements MenuAction {

    private final SavedNewsService savedNewsService = new SavedNewsService();
    private final Integer newsId;

    public SaveArticleAction(Integer newsId){
        this.newsId = newsId;
    }
    @Override
    public String getName() {
        return "Save Article";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            savedNewsService.saveNews(response.getUserId(), newsId);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
