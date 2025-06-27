package Application.externalNewsApi.service;

import Application.externalNewsApi.ExternalNewsApi;
import Application.externalNewsApi.controller.ExternalNewsApiController;
import Application.news.controller.NewsController;

import java.util.List;

public class ExternalNewsApiService {

    private ExternalNewsApiController externalNewsApiController;
    public ExternalNewsApiService(){
        externalNewsApiController = new ExternalNewsApiController();
    }
    public List<ExternalNewsApi> getAllExternalNewsApiDetails() throws Exception{
        return externalNewsApiController.getAllExternalNewsApiDetails();
    }
}
