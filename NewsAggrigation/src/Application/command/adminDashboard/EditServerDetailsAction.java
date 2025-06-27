package Application.command.adminDashboard;

import Application.command.MenuAction;

import java.util.Scanner;

public class EditServerDetailsAction implements MenuAction {
    @Override
    public String getName() {
        return "Update/Edit the external server’s details";
    }

    @Override
    public void execute(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update/Edit the external server’s details");
        ViewServerDetailsAction viewServerDetailsAction = new ViewServerDetailsAction();
        viewServerDetailsAction.execute(userId);
        System.out.println("Enter the external server ID");
        int id = scanner.nextInt();
        System.out.println("Enter the updated API key");
        String updatedApiKey = scanner.next();

    }
}
