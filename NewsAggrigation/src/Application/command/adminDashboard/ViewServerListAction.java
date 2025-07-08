package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.externalNewsApi.ExternalNewsApi;
import Application.externalNewsApi.service.ExternalNewsApiService;

import java.util.List;

public class ViewServerListAction implements MenuAction {

    private final ExternalNewsApiService externalNewsApiService;

    public ViewServerListAction() {
        this.externalNewsApiService = new ExternalNewsApiService();
    }

    @Override
    public String getName() {
        return "View the List of External Servers and Status";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== External Server Details ==");

        try {
            List<ExternalNewsApi> externalNewsApis = externalNewsApiService.getAllExternalNewsApiDetails();
            printServerList(externalNewsApis);
        } catch (Exception e) {
            System.err.println("Error fetching server list: " + e.getMessage());
        }
    }

    private void printServerList(List<ExternalNewsApi> servers) {
        if (servers == null || servers.isEmpty()) {
            System.out.println("(No external servers found)");
            return;
        }

        int index = 1;
        for (ExternalNewsApi server : servers) {
            System.out.printf("%d. %s  |  Status: %s  |  Last Accessed: %s%n",
                    index++, server.getSourceName(), server.getStatus(), server.getLastAccessed());
        }
    }
}
