package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;

public class LikeArticleAction implements MenuAction {
    @Override
    public String getName() {
        return "Like Article";
    }

    @Override
    public void execute(LoginResponse response) {

    }
}
