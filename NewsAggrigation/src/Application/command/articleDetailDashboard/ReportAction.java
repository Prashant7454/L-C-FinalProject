package Application.command.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;
import Application.userNewsReport.UserNewsReport;
import Application.userNewsReport.service.UserNewsReportService;

public class ReportAction implements MenuAction {
    private Integer newsId;
    private final UserNewsReportService userNewsReportService = new UserNewsReportService();
    private final NewsService newsService = new NewsService();

    public ReportAction(Integer newsId){
        this.newsId = newsId;
    }

    @Override
    public String getName() {
        return "Report Article";
    }

    @Override
    public void execute(LoginResponse response) {
        Integer userId = response.getUserId();
        
        try {
            // Check if user has already reported this news
            UserNewsReport existingReport = userNewsReportService.getReportByUserAndNews(userId, newsId);
            
            if (existingReport != null && existingReport.getIsReported() == 1) {
                System.out.println("You have already reported this article.");
                return;
            }
            
            // Report the news
            UserNewsReport newReport = userNewsReportService.reportNews(userId, newsId);
            System.out.println("Article reported successfully!");
            
            // Get current news to update report count
            News news = newsService.getNewsById(newsId);
            if (news != null) {
                // Increment report count
                Integer currentReportCount = news.getReportCount() != null ? news.getReportCount() : 0;
                news.setReportCount(currentReportCount + 1);
                
                // Check if report count exceeds 10, then hide the news
                if (news.getReportCount() >= 10) {
                    news.setIsHide(1);
                    System.out.println("This article has been hidden due to multiple reports.");
                }
                
                // Update the news
                newsService.updateNews(news);
            }
            
        } catch (Exception e) {
            System.out.println("Error reporting article: " + e.getMessage());
        }
    }
}
