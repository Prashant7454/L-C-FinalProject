package Application.userNewsReport.service;

import Application.userNewsReport.UserNewsReport;
import Application.userNewsReport.controller.UserNewsReportController;

import java.util.List;

public class UserNewsReportService {

    UserNewsReportController userNewsReportController;

    public UserNewsReportService(){
        userNewsReportController = new UserNewsReportController();
    }

    public UserNewsReport reportNews(Integer userId, Integer newsId) throws Exception {
        UserNewsReport userNewsReport = new UserNewsReport();
        userNewsReport.setUserId(userId);
        userNewsReport.setNewsId(newsId);
        userNewsReport.setIsReported(1);
        return userNewsReportController.reportNews(userNewsReport);
    }

    public List<UserNewsReport> getAllReports() throws Exception {
        return userNewsReportController.getAllReports();
    }

    public UserNewsReport getReportByUserAndNews(Integer userId, Integer newsId) throws Exception {
        return userNewsReportController.getReportByUserAndNews(userId, newsId);
    }
} 