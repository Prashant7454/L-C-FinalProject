package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.externalNewsApi.ExternalNewsApi;
import Application.externalNewsApi.service.ExternalNewsApiService;

import java.util.List;

public class ViewServerDetailsAction implements MenuAction {

    private final ExternalNewsApiService externalNewsApiService;

    public ViewServerDetailsAction() {
        this.externalNewsApiService = new ExternalNewsApiService();
    }

    @Override
    public String getName() {
        return "View the External Server’s Details";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== List of External Servers ==");

        try {
            List<ExternalNewsApi> externalNewsApisList = externalNewsApiService.getAllExternalNewsApiDetails();
            printExternalServers(externalNewsApisList);
        } catch (Exception e) {
            System.err.println("Failed to retrieve server details: " + e.getMessage());
        }
    }

    private void printExternalServers(List<ExternalNewsApi> servers) {
        if (servers == null || servers.isEmpty()) {
            System.out.println("(No external servers configured)");
            return;
        }

        for (ExternalNewsApi server : servers) {
            System.out.printf("%d. %s  -  %s%n", server.getId(), server.getSourceName(), server.getApiKey());
        }
    }
}
