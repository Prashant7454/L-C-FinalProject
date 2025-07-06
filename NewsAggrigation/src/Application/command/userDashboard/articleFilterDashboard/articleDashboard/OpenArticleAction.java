package Application.command.userDashboard.articleFilterDashboard.articleDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleDetailMenu;
import Application.menu.Menu;
import Application.news.News;
import Application.personalization.service.NewsPersonalizationService;
import Application.util.NewsUtil;

import java.util.Map;
import java.util.Scanner;

public class OpenArticleAction implements MenuAction {
    private final Scanner scanner = new Scanner(System.in);
    private Menu previousMenu;
    private Map<Integer,News> newsMap;
    private final NewsPersonalizationService personalizationService;

    public OpenArticleAction(Menu previousMenu, Map<Integer,News> newsMap){
        this.previousMenu = previousMenu;
        this.newsMap = newsMap;
        this.personalizationService = new NewsPersonalizationService();
    }

    @Override
    public String getName() {
        return "Open Article";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.print("Select News : ");
        Integer selectedNewsNumber = scanner.nextInt();

        News selectedNews = newsMap.get(selectedNewsNumber);
        if(selectedNews == null){
            System.out.println("Invalid choose!");
            return;
        }
        try {
            NewsUtil.showArticleDetails(selectedNews);
            
            // Record that the user has read this article for personalization
            try {
                personalizationService.recordArticleRead(response.getUserId(), selectedNews.getId());
            } catch (Exception e) {
                // Silently fail - personalization recording shouldn't break the main functionality
                System.err.println("Warning: Could not record article read for personalization: " + e.getMessage());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        new ArticleDetailMenu(selectedNews.getId(),previousMenu).showMenu(response);
    }
}
