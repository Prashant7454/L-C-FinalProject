package Application.command.dasboard;

import Application.command.MenuAction;

public class SearchAction implements MenuAction {
    @Override
    public String getName() {
        return "Search";
    }

    @Override
    public void execute() {
        System.out.println("Search Articles...");
        return; // breaks the dashboard loop
    }
}
