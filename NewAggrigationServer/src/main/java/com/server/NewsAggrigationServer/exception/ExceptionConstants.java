package com.server.NewsAggrigationServer.exception;

public class ExceptionConstants {
    
    // Error Codes
    public static final String DUPLICATE_USER = "DUPLICATE_USER";
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String UNAUTHORIZED_ACCESS = "UNAUTHORIZED_ACCESS";
    public static final String NEWS_SERVICE_ERROR = "NEWS_SERVICE_ERROR";
    public static final String EXTERNAL_API_ERROR = "EXTERNAL_API_ERROR";
    public static final String DATABASE_ERROR = "DATABASE_ERROR";
    public static final String TYPE_MISMATCH = "TYPE_MISMATCH";
    public static final String DATA_INTEGRITY_VIOLATION = "DATA_INTEGRITY_VIOLATION";
    public static final String RUNTIME_ERROR = "RUNTIME_ERROR";
    public static final String GENERIC_ERROR = "GENERIC_ERROR";
    
    // User Related Messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists with this email or username";
    public static final String INVALID_USERNAME_PASSWORD = "Invalid username or password";
    public static final String USER_ACCOUNT_DISABLED = "User account is disabled";
    public static final String INSUFFICIENT_PERMISSIONS = "Insufficient permissions to perform this action";
    public static final String LOGIN_FAILURE = "Failed To Login";

    // News Related Messages
    public static final String NEWS_NOT_FOUND = "News article not found";
    public static final String NEWS_ALREADY_EXISTS = "News article already exists";
    public static final String NEWS_FETCH_ERROR = "Error fetching news from external API";
    public static final String NEWS_SAVE_ERROR = "Error saving news article";
    public static final String NEWS_UPDATE_ERROR = "Error updating news article";
    public static final String NEWS_DELETE_ERROR = "Error deleting news article";
    
    // Category Related Messages
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
    public static final String CATEGORY_ASSIGNMENT_ERROR = "Error assigning category to news";
    
    // Keyword Related Messages
    public static final String KEYWORD_NOT_FOUND = "Keyword not found";
    public static final String KEYWORD_ALREADY_EXISTS = "Keyword already exists";
    
    // External API Messages
    public static final String API_CONNECTION_ERROR = "Unable to connect to external API";
    public static final String API_RATE_LIMIT_EXCEEDED = "API rate limit exceeded";
    public static final String API_INVALID_RESPONSE = "Invalid response from external API";
    public static final String API_AUTHENTICATION_ERROR = "External API authentication failed";
    
    // Database Messages
    public static final String DB_CONNECTION_ERROR = "Database connection error";
    public static final String DB_QUERY_ERROR = "Database query error";
    public static final String DB_TRANSACTION_ERROR = "Database transaction error";
    
    // Validation Messages
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    public static final String INVALID_PASSWORD_FORMAT = "Password must be at least 8 characters long";
    public static final String INVALID_USERNAME_FORMAT = "Username must be alphanumeric and 3-20 characters long";
    public static final String REQUIRED_FIELD_MISSING = "Required field is missing";
    public static final String INVALID_DATE_FORMAT = "Invalid date format";
    public static final String INVALID_NUMBER_FORMAT = "Invalid number format";
    
    // Notification Messages
    public static final String NOTIFICATION_NOT_FOUND = "Notification not found";
    public static final String NOTIFICATION_SEND_ERROR = "Error sending notification";
    public static final String NOTIFICATION_CONFIG_ERROR = "Error configuring notification settings";
    
    // Personalization Messages
    public static final String PERSONALIZATION_ERROR = "Error generating personalized content";
    public static final String USER_PREFERENCES_NOT_FOUND = "User preferences not found";
    
    // Report Messages
    public static final String REPORT_NOT_FOUND = "Report not found";
    public static final String REPORT_ALREADY_EXISTS = "Report already exists for this news";
    public static final String REPORT_PROCESSING_ERROR = "Error processing report";
    
    // Saved News Messages
    public static final String SAVED_NEWS_NOT_FOUND = "Saved news not found";
    public static final String SAVED_NEWS_ALREADY_EXISTS = "News is already saved";
    public static final String SAVED_NEWS_SAVE_ERROR = "Error saving news";
    public static final String SAVED_NEWS_DELETE_ERROR = "Error removing saved news";
    
    // Like/Dislike Messages
    public static final String LIKE_DISLIKE_ERROR = "Error processing like/dislike action";
    public static final String ALREADY_LIKED = "News is already liked by user";
    public static final String ALREADY_DISLIKED = "News is already disliked by user";
    
    // News Hiding Messages
    public static final String NEWS_HIDING_ERROR = "Error hiding news";
    public static final String NEWS_UNHIDING_ERROR = "Error unhiding news";
    public static final String NEWS_ALREADY_HIDDEN = "News is already hidden";
    public static final String NEWS_NOT_HIDDEN = "News is not hidden";
} 