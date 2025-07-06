# Exception Handling System Documentation

This document describes the comprehensive exception handling system implemented for both the Spring Boot server (`NewAggrigationServer`) and the Java client application (`NewsAggrigation`).

## Overview

The exception handling system provides:
- **Centralized exception handling** for both applications
- **Custom exception classes** with specific error codes and messages
- **Consistent error responses** across all endpoints
- **Detailed logging** and error tracking
- **User-friendly error messages** with appropriate HTTP status codes

## Spring Boot Server (NewAggrigationServer)

### Exception Classes

#### 1. Base Exceptions
- **`ResourceNotFoundException`** - For resources not found (404)
- **`FoundDuplicateUserNameException`** - For duplicate user registration (409)
- **`UserLoginException`** - For authentication failures (401)

#### 2. Enhanced Exceptions
- **`ValidationException`** - For input validation errors (400)
- **`UnauthorizedException`** - For authorization failures (403)
- **`NewsServiceException`** - For news-related service errors (500)
- **`ExternalApiException`** - For external API failures (503)
- **`DatabaseException`** - For database operation errors (500)

### Global Exception Handler

The `GlobalExceptionHandler` class provides centralized exception handling with:

#### Custom Exception Handlers
```java
@ExceptionHandler(ValidationException.class)
@ExceptionHandler(UnauthorizedException.class)
@ExceptionHandler(NewsServiceException.class)
@ExceptionHandler(ExternalApiException.class)
@ExceptionHandler(DatabaseException.class)
```

#### Framework Exception Handlers
```java
@ExceptionHandler(MethodArgumentNotValidException.class)
@ExceptionHandler(MethodArgumentTypeMismatchException.class)
@ExceptionHandler(DataIntegrityViolationException.class)
```

#### Generic Exception Handlers
```java
@ExceptionHandler(RuntimeException.class)
@ExceptionHandler(Exception.class)
```

### Error Response Format

All exceptions return a consistent `ApiErrorResponse` with:
- **timestamp** - When the error occurred
- **status** - HTTP status code
- **error** - Error type description
- **message** - User-friendly error message
- **path** - Request URI
- **errorCode** - Application-specific error code
- **details** - Additional error details (optional)

### Example Error Response
```json
{
  "timestamp": "2024-01-15T10:30:45",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid email format",
  "path": "/api/auth/signup",
  "errorCode": "VALIDATION_ERROR",
  "details": "Field: email"
}
```

### Exception Constants

The `ExceptionConstants` class centralizes all error messages and codes:
- User-related messages
- News-related messages
- Category-related messages
- External API messages
- Database messages
- Validation messages

## Java Client Application (NewsAggrigation)

### Exception Classes

#### 1. Base Exception
- **`NewsAggrigationException`** - Base exception with error codes and operations

#### 2. Specific Exceptions
- **`AuthenticationException`** - For authentication-related errors
- **`NetworkException`** - For network and HTTP errors
- **`ValidationException`** - For input validation errors

### Exception Handler

The `ExceptionHandler` class provides:
- **Centralized exception logging** with timestamps
- **Specific handling** for different exception types
- **Detailed error information** including stack traces
- **Logging utilities** for errors, warnings, and info messages

### Example Exception Handling
```java
try {
    // Business logic
} catch (AuthenticationException | NetworkException | ValidationException e) {
    ExceptionHandler.handleException(e);
    throw e;
} catch (Exception e) {
    ExceptionHandler.handleGenericException(e);
    throw new AuthenticationException("Operation failed", e);
}
```

## Usage Examples

### Spring Boot Server

#### 1. Throwing Custom Exceptions
```java
// In service layer
if (user == null) {
    throw new ResourceNotFoundException(ExceptionConstants.USER_NOT_FOUND + " with ID: " + id);
}

if (userRepository.existsByEmail(email)) {
    throw new FoundDuplicateUserNameException(ExceptionConstants.USER_ALREADY_EXISTS);
}
```

#### 2. Validation in Controllers
```java
@PostMapping("/signup")
public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDTO request) {
    // Validation errors are automatically handled by @Valid
    return signupService.signup(request);
}
```

#### 3. Service Layer Exception Handling
```java
@Override
public NewsDTO createNews(NewsDTO dto) {
    try {
        News news = new News();
        mapDtoToEntity(dto, news);
        News saved = newsRepository.save(news);
        return mapEntityToDto(saved);
    } catch (Exception e) {
        throw new NewsServiceException(
            ExceptionConstants.NEWS_SAVE_ERROR,
            ExceptionConstants.NEWS_SERVICE_ERROR,
            "CREATE_NEWS"
        );
    }
}
```

### Java Client Application

#### 1. Input Validation
```java
private void validateLoginInput(String username, String password) throws ValidationException {
    if (username == null || username.trim().isEmpty()) {
        throw new ValidationException(ExceptionConstants.EMPTY_FIELD, "username");
    }
    
    if (username.length() < 3 || username.length() > 20) {
        throw new ValidationException(ExceptionConstants.INVALID_USERNAME, "username");
    }
}
```

#### 2. Network Error Handling
```java
try {
    String responseJson = HttpClientUtil.sendRequest(LOGIN_API_URL, "POST", jsonBody);
    return gson.fromJson(responseJson, LoginResponse.class);
} catch (Exception e) {
    throw new NetworkException(ExceptionConstants.CONNECTION_FAILED, LOGIN_API_URL, e);
}
```

#### 3. Centralized Exception Handling
```java
public LoginResponse login(String username, String password) throws AuthenticationException, NetworkException, ValidationException {
    try {
        validateLoginInput(username, password);
        LoginRequest loginRequest = getLoginRequest(username, password);
        LoginController loginController = new LoginController();
        return loginController.login(loginRequest);
    } catch (AuthenticationException | NetworkException | ValidationException e) {
        ExceptionHandler.handleException(e);
        throw e;
    } catch (Exception e) {
        ExceptionHandler.handleGenericException(e);
        throw new AuthenticationException(ExceptionConstants.LOGIN_FAILED, e);
    }
}
```

## Error Codes Reference

### Spring Boot Server Error Codes
- `DUPLICATE_USER` - User already exists
- `RESOURCE_NOT_FOUND` - Resource not found
- `INVALID_CREDENTIALS` - Authentication failed
- `VALIDATION_ERROR` - Input validation failed
- `UNAUTHORIZED_ACCESS` - Insufficient permissions
- `NEWS_SERVICE_ERROR` - News service error
- `EXTERNAL_API_ERROR` - External API error
- `DATABASE_ERROR` - Database operation error
- `TYPE_MISMATCH` - Parameter type mismatch
- `DATA_INTEGRITY_VIOLATION` - Data integrity violation

### Java Client Error Codes
- `AUTH_ERROR` - Authentication error
- `NETWORK_ERROR` - Network/HTTP error
- `VALIDATION_ERROR` - Validation error
- `UNKNOWN_ERROR` - Unknown error
- `SERVER_ERROR` - Server error
- `DATA_ERROR` - Data processing error

## Best Practices

### 1. Exception Hierarchy
- Use specific exceptions for specific error types
- Extend base exceptions for custom functionality
- Include error codes and operation context

### 2. Error Messages
- Use centralized constants for consistent messages
- Provide user-friendly error descriptions
- Include technical details in logs, not user messages

### 3. Logging
- Log all exceptions with appropriate detail level
- Include context information (user, operation, etc.)
- Use structured logging for better analysis

### 4. HTTP Status Codes
- 400 Bad Request - Validation errors
- 401 Unauthorized - Authentication required
- 403 Forbidden - Insufficient permissions
- 404 Not Found - Resource not found
- 409 Conflict - Resource conflict
- 500 Internal Server Error - Server errors
- 503 Service Unavailable - External service unavailable

### 5. Security
- Don't expose sensitive information in error messages
- Log security-related errors appropriately
- Use generic messages for authentication failures

## Testing Exception Handling

### Spring Boot Server
```java
@Test
public void testUserNotFound() {
    when(userRepository.findById(1L)).thenReturn(Optional.empty());
    
    assertThrows(ResourceNotFoundException.class, () -> {
        userService.getUserById(1L);
    });
}
```

### Java Client
```java
@Test
public void testInvalidCredentials() {
    assertThrows(AuthenticationException.class, () -> {
        loginService.login("invalid", "password");
    });
}
```

## Monitoring and Debugging

### 1. Log Analysis
- Monitor exception frequency and types
- Track error patterns and trends
- Identify common failure points

### 2. Error Tracking
- Use error codes for categorization
- Include operation context for debugging
- Maintain error history for analysis

### 3. Performance Impact
- Exception handling should not significantly impact performance
- Use appropriate exception types to avoid unnecessary overhead
- Consider async logging for high-volume applications

## Conclusion

This exception handling system provides:
- **Consistent error handling** across both applications
- **User-friendly error messages** with appropriate technical detail
- **Centralized logging** for monitoring and debugging
- **Extensible architecture** for adding new exception types
- **Security-conscious** error reporting

The system ensures that both applications handle errors gracefully while providing meaningful feedback to users and developers. 