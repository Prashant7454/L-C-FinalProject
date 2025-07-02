package Application.command.articleDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ArticleDetailMenu;
import Application.menu.Menu;
import Application.news.News;
import Application.news.service.NewsService;
import Application.util.NewsUtil;

import java.util.Scanner;

public class OpenArticleAction implements MenuAction {
    Scanner scanner = new Scanner(System.in);
    Menu previousMenu;
    public OpenArticleAction(Menu previousMenu){
        this.previousMenu = previousMenu;
    }

    @Override
    public String getName() {
        return "Open Article";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.print("Enter News Id: ");
        Integer newsId = scanner.nextInt();
        try {
            NewsUtil.showArticleDetails(newsId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        new ArticleDetailMenu(newsId,previousMenu).showMenu(response);
    }
}
