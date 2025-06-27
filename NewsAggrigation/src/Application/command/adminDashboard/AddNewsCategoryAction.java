package Application.command.adminDashboard;

import Application.command.MenuAction;

public class AddNewsCategoryAction implements MenuAction {
    @Override
    public String getName() {
        return "Add new News Category";
    }

    @Override
    public void execute(int userId) {
        System.out.println("Add new category action...");
        return;
    }
}
