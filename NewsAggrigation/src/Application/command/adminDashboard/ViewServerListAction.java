package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.externalNewsApi.ExternalNewsApi;
import Application.externalNewsApi.service.ExternalNewsApiService;
import Application.news.News;

import java.util.ArrayList;
import java.util.List;

public class ViewServerListAction implements MenuAction {
    @Override
    public String getName() {
        return "View the list of external servers and status";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("List of external servers details: ");
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
        int count = 1;
        for(ExternalNewsApi externalNewsApi: externalNewsApisList){
            System.out.print(count+".");
            System.out.print(externalNewsApi.getSourceName()+ "  -  ");
            System.out.print(externalNewsApi.getStatus()+ "  -  ");
            System.out.println(externalNewsApi.getLastAccessed());
            count++;
        }
    }
}
