package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.userDashboard.articleFilterDashboard.articleDashboard.articleDetailDashboard.*;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class ArticleDetailMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();
    private Integer newsId;
    public ArticleDetailMenu(Integer newsId, Menu previousMenu) {
        actions.add(new SaveArticleAction(newsId));
        actions.add(new LikeArticleAction(newsId));
        actions.add(new DisLikeArticleAction(newsId));
        actions.add(new ReportAction(newsId));
        actions.add(new BackAction(previousMenu,null));
        actions.add(new LogoutAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}
