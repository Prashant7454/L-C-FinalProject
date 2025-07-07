package Application.command.userDashboard.articleFilterDashboard.articleDashboard.articleDetailDashboard;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.news.News;
import Application.news.service.NewsService;
import Application.newsLikeOrDislikeUser.NewsLikeDislikeUser;
import Application.newsLikeOrDislikeUser.service.NewsLikeDislikeUserService;

public class LikeArticleAction implements MenuAction {
    private static final int LIKED_STATUS = 1;
    private static final int DISLIKED_STATUS = 0;
    private static final String ALREADY_LIKED_MESSAGE = "News is already liked";
    private static final String LIKE_SUCCESS_MESSAGE = "Article liked successfully!";
    private static final String ERROR_MESSAGE = "Error processing like action: ";
    
    private final Integer newsId;
    private final NewsLikeDislikeUserService reactionService;
    private final NewsService newsService;

    public LikeArticleAction(Integer newsId) {
        this.newsId = newsId;
        this.reactionService = new NewsLikeDislikeUserService();
        this.newsService = new NewsService();
    }

    @Override
    public String getName() {
        return "Like Article";
    }

    @Override
    public void execute(LoginResponse response) {
        try {
            Integer userId = response.getUserId();
            
            if (isAlreadyLiked(userId)) {
                displayMessage(ALREADY_LIKED_MESSAGE);
            return;
        }
            NewsLikeDislikeUser reaction = createLikeReaction(userId);
            saveReaction(reaction);
            updateNewsLikeCount(userId);
            displayMessage(LIKE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            handleError(e);
        }
    }

    private boolean isAlreadyLiked(Integer userId) throws Exception {
        NewsLikeDislikeUser existingReaction = reactionService.getReaction(newsId, userId);
        return existingReaction != null && existingReaction.isLiked() == LIKED_STATUS;
    }

    private NewsLikeDislikeUser createLikeReaction(Integer userId) throws Exception {
        NewsLikeDislikeUser existingReaction = reactionService.getReaction(newsId, userId);
        NewsLikeDislikeUser reaction = new NewsLikeDislikeUser();
        
        reaction.setNewsId(newsId);
        reaction.setUserId(userId);
        reaction.setLiked(LIKED_STATUS);
        reaction.setDisliked(DISLIKED_STATUS);
        
        if (existingReaction != null && existingReaction.getNewsId().equals(newsId)) {
            reaction.setId(existingReaction.getId());
        }
        return reaction;
    }

    private void saveReaction(NewsLikeDislikeUser reaction) throws Exception {
        reactionService.saveOrUpdateReaction(reaction);
        }

    private void updateNewsLikeCount(Integer userId) throws Exception {
        News news = newsService.getNewsById(newsId);
        if (news == null) {
            throw new RuntimeException("News not found with ID: " + newsId);
        }
        
        NewsLikeDislikeUser existingReaction = reactionService.getReaction(newsId, userId);

        Integer currentLikeCount = news.getLikeCount() != null ? news.getLikeCount() : 0;
        news.setLikeCount(currentLikeCount + 1);

        if (existingReaction != null && existingReaction.isDisliked() == LIKED_STATUS) {
            Integer currentDislikeCount = news.getDisLikeCount() != null ? news.getDisLikeCount() : 0;
            news.setDisLikeCount(currentDislikeCount - 1);
        }

        News updatedNews = newsService.updateNewsLikeAndDisLikeCount(news);
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }

    private void handleError(Exception e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());
    }
}
