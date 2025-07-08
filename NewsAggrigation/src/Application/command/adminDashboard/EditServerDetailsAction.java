package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.externalNewsApi.ExternalNewsApi;
import Application.externalNewsApi.service.ExternalNewsApiService;

import java.util.Scanner;

public class EditServerDetailsAction implements MenuAction {

    private final ExternalNewsApiService externalNewsApiService;
    private final ViewServerDetailsAction viewServerDetailsAction;
    private final Scanner scanner;

    public EditServerDetailsAction() {
        this.externalNewsApiService = new ExternalNewsApiService();
        this.viewServerDetailsAction = new ViewServerDetailsAction();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getName() {
        return "Update/Edit the external server’s details";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Update/Edit External Server Details ==");

        try {
            // Show existing server details first
            viewServerDetailsAction.execute(response);

            System.out.print("Enter the external server ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter the updated API key: ");
            String updatedApiKey = scanner.nextLine().trim();

            ExternalNewsApi externalNewsApi = externalNewsApiService.getExternalNewsApiDetailById(id);
            externalNewsApi.setApiKey(updatedApiKey);

            ExternalNewsApi updatedApi = externalNewsApiService.updateExternalNewsApiKey(externalNewsApi);
            System.out.println("✅ External server updated successfully: " + updatedApi.getSourceName());

        } catch (Exception e) {
            System.err.println("Error updating external server: " + e.getMessage());
        }
    }
}
