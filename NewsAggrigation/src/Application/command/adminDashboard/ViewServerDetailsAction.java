package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.externalNewsApi.ExternalNewsApi;
import Application.externalNewsApi.service.ExternalNewsApiService;

import java.util.ArrayList;
import java.util.List;

public class ViewServerDetailsAction implements MenuAction {
    @Override
    public String getName() {
        return "View the external server’s details";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("List of external servers: ");
        ExternalNewsApiService externalNewsApiService = new ExternalNewsApiService();
        List<ExternalNewsApi> externalNewsApisList = new ArrayList<>();
        try {
            externalNewsApisList = externalNewsApiService.getAllExternalNewsApiDetails();
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return;
        }
        showAllExternalAPI(externalNewsApisList);
    }

    private void showAllExternalAPI(List<ExternalNewsApi> externalNewsApisList){
        for(ExternalNewsApi externalNewsApi: externalNewsApisList){
            System.out.print(externalNewsApi.getId()+".");
            System.out.print(externalNewsApi.getSourceName()+ "  -  ");
            System.out.println(externalNewsApi.getApiKey());
        }
    }
}
