package Application.command.dasboard;

import Application.command.MenuAction;

public class SavedArticlesAction implements MenuAction {
    @Override
    public String getName() {
        return "SavedArticle";
    }

    @Override
    public void execute() {
        System.out.println("Fetching top saved Articles...");
        return; // breaks the dashboard loop
    }
}
