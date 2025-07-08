# News Aggregation Notification System

## Overview

The notification system automatically sends email notifications to users when new news articles are fetched from external APIs. Users can configure which categories they want to receive notifications for through the notification configuration feature.

## How It Works

### 1. News Fetching Process
- The system fetches news from external APIs (NewsAPI and TheNewsAPI) every 3 hours via a scheduled task
- When new news articles are fetched, they are:
  1. Saved to the database
  2. Assigned categories based on keywords in the title and content
  3. **NEW**: Notifications are sent to users who have enabled notifications for those categories

### 2. Notification Configuration
Users can:
- View their current notification settings for each category
- Toggle notifications on/off for specific categories
- Add keywords to categories for better news categorization

### 3. Email Notifications
When a news article is fetched and categorized:
- The system finds all users who have enabled notifications for the article's categories
- For each user, it:
  - Sends an email notification with the news title, description, and category
  - Stores a notification record in the database for the user to view later

## Setup Instructions

### 1. Email Configuration
Update the email settings in `application.properties`:

```properties
# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

**Important**: For Gmail, you need to:
1. Enable 2-factor authentication
2. Generate an "App Password" (not your regular password)
3. Use the app password in the configuration

### 2. Dependencies
The system uses Spring Boot Mail starter, which is already added to `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

## System Components

### 1. EmailService
- **Interface**: `EmailService`
- **Implementation**: `EmailServiceImpl`
- **Purpose**: Sends email notifications to users

### 2. NewsNotificationService
- **Interface**: `NewsNotificationService`
- **Implementation**: `NewsNotificationServiceImpl`
- **Purpose**: Orchestrates the notification process for news articles

### 3. Integration Points
- **NewsSyncServiceImpl**: Calls notification service after fetching and categorizing news
- **NotificationConfigurationRepository**: Finds users with enabled notifications for specific categories
- **UserRepository**: Gets user details (email, username) for sending notifications

## Database Tables

### 1. notification_configuration
Stores user preferences for which categories they want notifications:
- `id`: Primary key
- `user_id`: User ID
- `category_id`: Category ID
- `enabled`: Boolean flag for notification preference

### 2. notification
Stores notification records for users:
- `id`: Primary key
- `news_id`: News article ID
- `user_id`: User ID
- `timestamp`: When the notification was created

### 3. user
Contains user information including email addresses:
- `id`: Primary key
- `username`: Username
- `email`: Email address for notifications
- `password`: Encrypted password
- `role`: User role

## API Endpoints

### Notification Configuration
- `GET /api/notification-config/user/{userId}` - Get user's notification settings
- `PUT /api/notification-config` - Update notification settings
- `DELETE /api/notification-config/user/{userId}/category/{categoryId}` - Delete notification setting

### Notifications
- `GET /api/notifications/user/{userId}` - Get user's notifications
- `DELETE /api/notifications/clear/{userId}` - Clear all notifications for user
- `POST /api/notifications/test/{newsId}` - Test notification for specific news (admin only)

## User Workflow

### 1. Setting Up Notifications
1. User logs into the application
2. Goes to "Notification Configuration" from the user dashboard
3. Views current notification settings for all categories
4. Toggles notifications on/off for desired categories
5. Settings are saved automatically

### 2. Receiving Notifications
1. When news is fetched from external APIs
2. System automatically categorizes the news
3. Users with enabled notifications for those categories receive emails
4. Notifications are also stored in the database for viewing in the app

### 3. Viewing Notifications
1. Users can view their notifications through the "Notifications" menu option
2. Notifications show the news articles they've been notified about
3. Users can clear notifications when no longer needed

## Admin Features

### 1. Category Management
- Hide/unhide categories (hidden categories won't send notifications)
- Manage category keywords for better news categorization

### 2. News Management
- View and manage reported news
- Hide news by keywords
- Assign categories to news manually

## Error Handling

The notification system includes comprehensive error handling:
- Email sending failures are logged but don't stop the news fetching process
- Database errors are caught and logged
- Missing user or category data is handled gracefully

## Logging

The system logs:
- When notifications are sent successfully
- Email sending failures
- Database operation errors
- Category assignment and notification processing

## Testing

To test the notification system:
1. Set up email configuration with valid credentials
2. Create a user account
3. Enable notifications for a category
4. Wait for the scheduled news fetch or manually trigger it
5. Check email inbox for notifications

## Security Considerations

1. **Email Credentials**: Store email credentials securely, preferably using environment variables
2. **User Privacy**: Only send notifications to users who have explicitly enabled them
3. **Rate Limiting**: Consider implementing rate limiting for email sending to prevent abuse
4. **Data Protection**: Ensure user email addresses are handled according to privacy regulations

## Future Enhancements

Potential improvements:
1. **Push Notifications**: Add mobile push notifications
2. **Notification Templates**: Allow customization of email templates
3. **Notification Preferences**: Add more granular control (frequency, time of day, etc.)
4. **Digest Emails**: Send daily/weekly summaries instead of individual notifications
5. **Unsubscribe Links**: Add unsubscribe functionality to emails 