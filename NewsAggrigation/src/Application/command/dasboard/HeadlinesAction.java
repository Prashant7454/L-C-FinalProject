package Application.command.dasboard;

import Application.command.MenuAction;

public class HeadlinesAction implements MenuAction {
    @Override
    public String getName() {
        return "Headlines";
    }

    @Override
    public void execute() {
        System.out.println("Fetching top headlines...");
        // Add logic here
    }
}
