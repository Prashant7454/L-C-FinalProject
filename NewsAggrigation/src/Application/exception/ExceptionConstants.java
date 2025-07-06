package Application.exception;

public class ExceptionConstants {
    
    // Error Codes
    public static final String AUTH_ERROR = "AUTH_ERROR";
    public static final String NETWORK_ERROR = "NETWORK_ERROR";
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    public static final String SERVER_ERROR = "SERVER_ERROR";
    public static final String DATA_ERROR = "DATA_ERROR";
    
    // Authentication Messages
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String LOGIN_FAILED = "Login failed. Please check your credentials.";
    public static final String SIGNUP_FAILED = "Signup failed. Please try again.";
    public static final String SESSION_EXPIRED = "Session expired. Please login again.";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access. Please login first.";
    
    // Network Messages
    public static final String CONNECTION_FAILED = "Failed to connect to server";
    public static final String SERVER_UNREACHABLE = "Server is unreachable. Please check your connection.";
    public static final String TIMEOUT_ERROR = "Request timed out. Please try again.";
    public static final String API_ERROR = "External API error occurred";
    public static final String HTTP_ERROR = "HTTP error occurred";
    
    // Validation Messages
    public static final String INVALID_USERNAME = "Username must be 3-20 characters long";
    public static final String INVALID_PASSWORD = "Password must be at least 8 characters long";
    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String EMPTY_FIELD = "This field cannot be empty";
    public static final String INVALID_INPUT = "Invalid input provided";
    public static final String INVALID_DATE = "Invalid date format";
    public static final String INVALID_NUMBER = "Invalid number format";
    
    // News Related Messages
    public static final String NEWS_FETCH_ERROR = "Error fetching news articles";
    public static final String NEWS_NOT_FOUND = "News article not found";
    public static final String NEWS_SAVE_ERROR = "Error saving news article";
    public static final String NEWS_UPDATE_ERROR = "Error updating news article";
    public static final String NEWS_DELETE_ERROR = "Error deleting news article";
    
    // Category Related Messages
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String CATEGORY_FETCH_ERROR = "Error fetching categories";
    public static final String CATEGORY_SAVE_ERROR = "Error saving category";
    
    // User Related Messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_FETCH_ERROR = "Error fetching user data";
    public static final String USER_SAVE_ERROR = "Error saving user data";
    public static final String USER_UPDATE_ERROR = "Error updating user data";
    
    // Menu Related Messages
    public static final String MENU_ERROR = "Error displaying menu";
    public static final String INVALID_MENU_CHOICE = "Invalid menu choice";
    public static final String MENU_NAVIGATION_ERROR = "Error navigating menu";
    
    // File Related Messages
    public static final String FILE_READ_ERROR = "Error reading file";
    public static final String FILE_WRITE_ERROR = "Error writing file";
    public static final String FILE_NOT_FOUND = "File not found";
    
    // Data Processing Messages
    public static final String DATA_PARSING_ERROR = "Error parsing data";
    public static final String DATA_CONVERSION_ERROR = "Error converting data";
    public static final String DATA_VALIDATION_ERROR = "Data validation failed";
    
    // System Messages
    public static final String SYSTEM_ERROR = "System error occurred";
    public static final String MEMORY_ERROR = "Insufficient memory";
    public static final String CONFIGURATION_ERROR = "Configuration error";
    
    // Operation Messages
    public static final String OPERATION_FAILED = "Operation failed";
    public static final String OPERATION_CANCELLED = "Operation cancelled";
    public static final String OPERATION_TIMEOUT = "Operation timed out";
} 