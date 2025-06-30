package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.externalNewsApi.ExternalNewsApi;
import Application.externalNewsApi.service.ExternalNewsApiService;

import java.util.Scanner;

public class EditServerDetailsAction implements MenuAction {
    @Override
    public String getName() {
        return "Update/Edit the external server’s details";
    }

    @Override
    public void execute(LoginResponse response) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update/Edit the external server’s details");
        ViewServerDetailsAction viewServerDetailsAction = new ViewServerDetailsAction();
        viewServerDetailsAction.execute(response);
        System.out.println("Enter the external server ID");
        int id = scanner.nextInt();
        System.out.println("Enter the updated API key");
        String updatedApiKey = scanner.next();
        ExternalNewsApiService externalNewsApiService = new ExternalNewsApiService();
        ExternalNewsApi externalNewsApi = null;
        try{
            externalNewsApi = externalNewsApiService.getExternalNewsApiDetailById(id);
            externalNewsApi.setApiKey(updatedApiKey);
            externalNewsApi = externalNewsApiService.updateExternalNewsApiKey(externalNewsApi);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
