package Application.command.adminDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.menu.ReportedNewsMenu;
import Application.news.News;
import Application.news.service.NewsService;
import Application.util.NewsUtil;

import java.util.List;
import java.util.Map;

public class ManageReportedNewsAction implements MenuAction {

    private final NewsService newsService = new NewsService();

    @Override
    public String getName() {
        return "Manage Reported News";
    }

    @Override
    public void execute(LoginResponse response) {
        System.out.println("== Manage Reported News ==");

        try {
            List<News> reportedNews = newsService.getReportedNews();
            
            if (reportedNews.isEmpty()) {
                System.out.println("No reported news found.");
                return;
            }

            System.out.println("Found " + reportedNews.size() + " reported news article(s):\n");
            
            // Process and display news using NewsUtil
            Map<Integer, News> newsMap = NewsUtil.processNewsList(reportedNews);
            NewsUtil.printNewsList(newsMap);

            // Pass the processed news map to the menu
            new ReportedNewsMenu(newsMap).showMenu(response);

        } catch (Exception e) {
            System.err.println("Error managing reported news: " + e.getMessage());
        }
    }
} 