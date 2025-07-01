package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class DisLikeArticleAction implements MenuAction {
    @Override
    public String getName() {
        return "DisLike Article";
    }

    @Override
    public void execute(LoginResponse response) {

    }
}
