package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.util.HttpClientUtil;

public class AssignCategoriesAction implements MenuAction {

    private static final String CATEGORY_ASSIGNMENT_API_URL = "http://localhost:8081/api/category-assignment";

    @Override
    public String getName() {
        return "Assign Categories to News";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Assign Categories to News ==");

        try {
            // First, get the count of uncategorized news
            String countApi = CATEGORY_ASSIGNMENT_API_URL + "/uncategorized-count";
            String countResponse = HttpClientUtil.sendRequest(countApi, "GET", null);
            int uncategorizedCount = Integer.parseInt(countResponse);

            if (uncategorizedCount == 0) {
                System.out.println("No uncategorized news found. All news articles already have categories assigned.");
                return;
            }

            System.out.println("Found " + uncategorizedCount + " uncategorized news articles.");
            System.out.print("Do you want to assign categories to these news articles? (y/n): ");
            
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("y") && !confirmation.equals("yes")) {
                System.out.println("Operation cancelled.");
                return;
            }

            System.out.println("Assigning categories to " + uncategorizedCount + " news articles...");
            
            // Trigger category assignment
            String assignApi = CATEGORY_ASSIGNMENT_API_URL + "/assign-to-uncategorized";
            String result = HttpClientUtil.sendRequest(assignApi, "POST", null);
            
            System.out.println("Result: " + result);
            System.out.println("Category assignment completed successfully!");

        } catch (Exception e) {
            System.err.println("Error assigning categories: " + e.getMessage());
        }
    }
} 