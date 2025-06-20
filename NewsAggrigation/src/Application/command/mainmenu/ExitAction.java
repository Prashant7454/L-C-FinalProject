package Application.command.mainmenu;

import Application.command.MenuAction;

public class ExitAction implements MenuAction {

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
