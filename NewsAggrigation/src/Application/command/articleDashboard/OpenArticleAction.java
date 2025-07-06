package Application.command.articleDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleDetailMenu;
import Application.menu.Menu;
import Application.news.News;
import Application.news.service.NewsService;
import Application.util.NewsUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OpenArticleAction implements MenuAction {
    private Scanner scanner = new Scanner(System.in);
    private Menu previousMenu;
    private Map<Integer,News> newsMap;
    public OpenArticleAction(Menu previousMenu, Map<Integer,News> newsMap){
        this.previousMenu = previousMenu;
        this.newsMap = newsMap;
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
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        new ArticleDetailMenu(selectedNews.getId(),previousMenu).showMenu(response);
    }
}
