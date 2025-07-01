package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.articleDetailDashboard.*;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class ArticleDetailMenu {
    private final List<MenuAction> actions = new ArrayList<>();
    private Integer newsId;
    public ArticleDetailMenu(Integer newsId) {
        actions.add(new BackAction());
        actions.add(new LogoutAction());
        actions.add(new SaveArticleAction(newsId));
        actions.add(new LikeArticleAction());
        actions.add(new DisLikeArticleAction());
        actions.add(new ReportAction());
    }

    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}
