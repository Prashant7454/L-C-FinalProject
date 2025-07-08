# News Aggregation Personalization System

## Overview

The personalization system provides intelligent news recommendations to users based on their individual interests, reading behavior, and preferences. It analyzes multiple data points to create a personalized experience that increases user engagement and satisfaction.

## How It Works

### 1. Data Collection
The system collects user behavior data from multiple sources:
- **Notification Preferences**: Categories users have enabled for notifications
- **Reading History**: Articles users have viewed (automatically recorded)
- **Like/Dislike Behavior**: Articles users have liked or disliked
- **Saved Articles**: Articles users have saved for later reading
- **Keywords**: Keywords users have added to categories

### 2. Interest Score Calculation
For each news article, the system calculates a personalized interest score using weighted factors:

- **Notification Preferences (40%)**: Highest weight for explicit user preferences
- **Reading History (25%)**: Recent reading behavior in similar categories
- **Like/Dislike Behavior (20%)**: User reactions to similar content
- **Saved Articles (15%)**: Content users found valuable enough to save

### 3. Recommendation Algorithm
1. Get all visible news articles
2. Calculate personalized interest score for each article
3. Sort articles by interest score (highest first)
4. Return top recommendations with pagination support

## System Components

### 1. Server-Side Components

#### Models
- **UserReadingHistory**: Tracks which articles users have read
- **NewsLikeDislikeUser**: Existing model for user reactions
- **SavedNews**: Existing model for saved articles
- **NotificationConfiguration**: Existing model for user preferences

#### Services
- **NewsPersonalizationService**: Main personalization logic
- **NewsLikeDislikeUserService**: Enhanced with methods to get liked/disliked articles
- **UserReadingHistoryRepository**: Database operations for reading history

#### Controllers
- **NewsPersonalizationController**: REST endpoints for personalization features

### 2. Client-Side Components

#### Controllers
- **NewsPersonalizationController**: Client-side API communication

#### Services
- **NewsPersonalizationService**: Client-side service layer

#### Actions
- **PersonalizedNewsAction**: User dashboard action for viewing recommendations

## Database Schema

### New Table: user_reading_history
```sql
CREATE TABLE user_reading_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    news_id INT NOT NULL,
    read_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (news_id) REFERENCES news(id)
);
```

### Enhanced Repository Methods
- `findByUserIdOrderByReadAtDesc()`: Get user's reading history
- `existsByUserIdAndNewsId()`: Check if user has read an article
- `findRecentReadingHistory()`: Get recent reading history (last 30 days)

## API Endpoints

### Personalization Endpoints
- `GET /api/personalization/user/{userId}` - Get personalized news recommendations
- `GET /api/personalization/user/{userId}/page` - Get paginated recommendations
- `GET /api/personalization/user/{userId}/news/{newsId}/score` - Get interest score for specific article
- `GET /api/personalization/user/{userId}/top-categories` - Get user's top interest categories
- `POST /api/personalization/user/{userId}/news/{newsId}/read` - Record article read

### Enhanced Like/Dislike Endpoints
- `GET /api/news-reactions/user/{userId}/liked` - Get user's liked articles
- `GET /api/news-reactions/user/{userId}/disliked` - Get user's disliked articles

## User Experience

### 1. Personalized News Dashboard
Users can access personalized recommendations through:
- **User Dashboard** → **Personalized News Recommendations**
- Shows top 20 personalized articles
- Additional options for more recommendations and interest categories

### 2. Automatic Data Collection
- **Reading History**: Automatically recorded when users open articles
- **Like/Dislike**: Existing functionality enhanced for personalization
- **Saved Articles**: Existing functionality used for personalization
- **Notification Preferences**: Existing settings influence recommendations

### 3. Interest Category Insights
Users can view their top interest categories based on:
- Categories they've enabled for notifications
- Categories of articles they've read recently
- Categories of articles they've liked
- Categories of articles they've saved

## Personalization Algorithm Details

### Interest Score Calculation
```java
double totalScore = 0.0;

// Notification preferences (40% weight)
double notificationScore = calculateNotificationPreferenceScore(userId, newsId);
totalScore += notificationScore * 0.4;

// Reading history (25% weight)
double readingHistoryScore = calculateReadingHistoryScore(userId, newsId);
totalScore += readingHistoryScore * 0.25;

// Like/dislike behavior (20% weight)
double likeDislikeScore = calculateLikeDislikeScore(userId, newsId);
totalScore += likeDislikeScore * 0.2;

// Saved articles (15% weight)
double savedArticlesScore = calculateSavedArticlesScore(userId, newsId);
totalScore += savedArticlesScore * 0.15;

return Math.min(totalScore, 1.0); // Cap at 1.0
```

### Category Matching
For each factor, the system:
1. Gets the categories of the target news article
2. Finds user behavior in similar categories
3. Calculates a score based on the overlap
4. Normalizes the score by the number of categories

### Time Decay
- Recent reading history (last 30 days) gets higher weight
- Older behavior has reduced influence on recommendations

## Privacy and Data Protection

### Data Collection
- Only collects data necessary for personalization
- Reading history is automatically recorded but can be cleared
- All data is stored securely in the database

### User Control
- Users can view their top interest categories
- Reading history can be cleared (future enhancement)
- Personalization can be disabled (future enhancement)

## Performance Considerations

### Optimization Strategies
1. **Caching**: Interest scores can be cached for frequently accessed articles
2. **Batch Processing**: Calculate scores for multiple articles in batches
3. **Indexing**: Database indexes on user_id and news_id for fast queries
4. **Pagination**: Recommendations are paginated to avoid loading all articles

### Scalability
- Algorithm is designed to work with large datasets
- Database queries are optimized for performance
- Personalization calculations are done on-demand

## Future Enhancements

### 1. Advanced Algorithms
- **Collaborative Filtering**: Recommend articles based on similar users
- **Content-Based Filtering**: Analyze article content for better matching
- **Hybrid Approaches**: Combine multiple recommendation strategies

### 2. User Experience
- **Personalization Settings**: Allow users to control personalization preferences
- **Interest Categories**: Show category names instead of IDs
- **Recommendation Explanations**: Explain why articles are recommended

### 3. Analytics
- **Recommendation Performance**: Track click-through rates on recommendations
- **User Engagement**: Measure how personalization affects user engagement
- **A/B Testing**: Test different personalization algorithms

### 4. Machine Learning
- **Dynamic Weights**: Automatically adjust factor weights based on user behavior
- **Content Analysis**: Use NLP to analyze article content for better matching
- **Predictive Modeling**: Predict user interests based on historical data

## Testing

### Manual Testing
1. Create a user account
2. Enable notifications for specific categories
3. Read articles in different categories
4. Like and save some articles
5. Check personalized recommendations
6. Verify that recommendations match user interests

### Automated Testing
- Unit tests for interest score calculation
- Integration tests for API endpoints
- Performance tests for recommendation generation

## Troubleshooting

### Common Issues
1. **No Recommendations**: User needs to interact with content first
2. **Poor Recommendations**: Check if user has diverse reading history
3. **Performance Issues**: Verify database indexes are in place

### Debugging
- Check server logs for personalization errors
- Verify reading history is being recorded
- Test individual API endpoints

## Security Considerations

1. **Data Privacy**: Ensure user data is handled according to privacy regulations
2. **Access Control**: Verify user can only access their own personalization data
3. **Data Retention**: Implement policies for data retention and deletion
4. **Audit Logging**: Log personalization-related activities for security monitoring

The personalization system provides a foundation for intelligent content recommendations that will improve user engagement and satisfaction with the news aggregation platform. 