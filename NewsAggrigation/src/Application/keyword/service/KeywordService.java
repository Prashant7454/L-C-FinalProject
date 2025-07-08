package Application.keyword.service;

import Application.keyword.Keyword;
import Application.keyword.controller.KeywordController;

import java.util.List;

public class KeywordService {
    private final KeywordController keywordController;

    public KeywordService() {
        keywordController = new KeywordController();
    }

    public Keyword addKeyword(Keyword keyword) throws Exception {
        return keywordController.addKeyword(keyword);
    }

    public List<Keyword> getAllKeywords() throws Exception {
        return keywordController.getAllKeywords();
    }
} 