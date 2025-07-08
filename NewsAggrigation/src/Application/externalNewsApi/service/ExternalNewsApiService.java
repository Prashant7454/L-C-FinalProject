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

    public ExternalNewsApi getExternalNewsApiDetailById(int id) throws Exception{
        return externalNewsApiController.getExternalNewsApiById(id);
    }

    public List<ExternalNewsApi> getAllExternalNewsApiDetails() throws Exception{
        return externalNewsApiController.getAllExternalNewsApiDetails();
    }

    public ExternalNewsApi updateExternalNewsApiKey(ExternalNewsApi externalNewsApi) throws Exception{
        return externalNewsApiController.updateExternalNewsApiKey(externalNewsApi);
    }
}
