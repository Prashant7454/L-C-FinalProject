# ExternalNewsSource Test Suite

This document describes the comprehensive test suite for the ExternalNewsSource functionality, including the model, DTO, service, and controller layers.

## Test Structure

### 1. Service Layer Tests (`ExternalNewsSourceServiceImplTest.java`)

**Location**: `src/test/java/com/server/NewsAggrigationServer/service/ExternalNewsSourceServiceImplTest.java`

**Coverage**:
- ✅ `getNewsSourceBySourceName()` - Multiple scenarios
- ✅ `save()` - Normal and edge cases
- ✅ `getAll()` - With and without data
- ✅ `updateExternalNewsSource()` - Success and failure cases
- ✅ `getExternalSourceById()` - Success and failure cases

**Test Scenarios**:
- **Happy Path Tests**:
  - Retrieving sources by name
  - Saving new sources
  - Getting all sources
  - Updating existing sources
  - Getting source by ID

- **Edge Cases**:
  - Empty result sets
  - Null values handling
  - Multiple sources with same name
  - Partial updates

- **Error Scenarios**:
  - Resource not found exceptions
  - Null ID handling
  - Invalid data scenarios

### 2. Controller Layer Tests (`ExternalNewsSourceControllerTest.java`)

**Location**: `src/test/java/com/server/NewsAggrigationServer/controller/ExternalNewsSourceControllerTest.java`

**Coverage**:
- ✅ `GET /api/external/{id}` - Success and 404 scenarios
- ✅ `GET /api/external/source` - All sources retrieval
- ✅ `PUT /api/external/updatesource` - Update operations

**Test Scenarios**:
- **HTTP Response Tests**:
  - 200 OK responses
  - 404 Not Found responses
  - 400 Bad Request responses
  - 500 Internal Server Error responses

- **Request Validation**:
  - Valid JSON payloads
  - Invalid JSON handling
  - Null request bodies
  - Empty request bodies
  - Unsupported media types

- **Response Validation**:
  - JSON structure verification
  - Field value assertions
  - Array responses
  - Empty responses

### 3. Integration Tests (`ExternalNewsSourceIntegrationTest.java`)

**Location**: `src/test/java/com/server/NewsAggrigationServer/integration/ExternalNewsSourceIntegrationTest.java`

**Coverage**:
- ✅ End-to-end API testing
- ✅ Database persistence verification
- ✅ Transaction management
- ✅ Complete request-response cycles

**Test Scenarios**:
- **Full Stack Testing**:
  - Controller → Service → Repository flow
  - Database persistence verification
  - Transaction rollback testing

- **Real Database Testing**:
  - H2 in-memory database
  - Data persistence validation
  - Query result verification

## Test Configuration

### Test Properties (`application-test.properties`)

**Location**: `src/test/resources/application-test.properties`

**Configuration**:
```properties
# H2 In-Memory Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Security Disabled for Testing
spring.security.enabled=false

# Logging Configuration
logging.level.com.server.NewsAggrigationServer=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

## Running the Tests

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Spring Boot 3.x

### Command Line Execution

1. **Run All Tests**:
   ```bash
   mvn test
   ```

2. **Run Specific Test Class**:
   ```bash
   mvn test -Dtest=ExternalNewsSourceServiceImplTest
   mvn test -Dtest=ExternalNewsSourceControllerTest
   mvn test -Dtest=ExternalNewsSourceIntegrationTest
   ```

3. **Run Specific Test Method**:
   ```bash
   mvn test -Dtest=ExternalNewsSourceServiceImplTest#getNewsSourceBySourceName_ShouldReturnMatchingSources
   ```

4. **Run Tests with Coverage**:
   ```bash
   mvn test jacoco:report
   ```

### IDE Execution

1. **IntelliJ IDEA**:
   - Right-click on test class → "Run"
   - Right-click on test method → "Run"
   - Use Ctrl+Shift+F10 to run current test

2. **Eclipse**:
   - Right-click on test class → "Run As" → "JUnit Test"
   - Right-click on test method → "Run As" → "JUnit Test"

## Test Data

### Sample ExternalNewsSource Data
```java
ExternalNewsSource source = new ExternalNewsSource();
source.setId(1);
source.setSourceName("Test News API");
source.setBaseUrl("https://api.testnews.com");
source.setApiKey("test-api-key-123");
source.setStatus(true);
source.setLastAccessed(LocalDateTime.now());
```

### Sample ExternalNewsSourceDTO Data
```java
ExternalNewsSourceDTO dto = new ExternalNewsSourceDTO();
dto.setId(1);
dto.setSourceName("Test News API");
dto.setBaseUrl("https://api.testnews.com");
dto.setApiKey("test-api-key-123");
dto.setStatus(true);
dto.setLastAccessed(LocalDateTime.now());
```

## API Endpoints Tested

### 1. GET `/api/external/{id}`
- **Purpose**: Retrieve external news source by ID
- **Test Cases**:
  - ✅ Valid ID returns 200 with source data
  - ✅ Invalid ID returns 404
  - ✅ Null ID returns 400

### 2. GET `/api/external/source`
- **Purpose**: Retrieve all external news sources
- **Test Cases**:
  - ✅ Returns 200 with array of sources
  - ✅ Empty database returns empty array
  - ✅ Multiple sources returned correctly

### 3. PUT `/api/external/updatesource`
- **Purpose**: Update external news source
- **Test Cases**:
  - ✅ Valid update returns 200 with updated data
  - ✅ Invalid ID returns 404
  - ✅ Invalid JSON returns 400
  - ✅ Null request body returns 400

## Mocking Strategy

### Service Layer Mocking
- **Repository Layer**: Mocked using `@Mock` and `@InjectMocks`
- **Dependencies**: External dependencies mocked
- **Verification**: Repository method calls verified

### Controller Layer Mocking
- **Service Layer**: Mocked using `@Mock`
- **HTTP Layer**: MockMvc for HTTP request simulation
- **JSON Handling**: ObjectMapper for request/response serialization

### Integration Testing
- **Database**: Real H2 in-memory database
- **Full Stack**: Complete Spring context loaded
- **Transactions**: Real transaction management

## Assertions and Verifications

### Service Layer Assertions
- **Return Values**: Verify correct DTO mapping
- **Exception Handling**: Verify proper exception throwing
- **Repository Calls**: Verify correct method invocations

### Controller Layer Assertions
- **HTTP Status Codes**: Verify correct response codes
- **Response Content**: Verify JSON structure and values
- **Content Types**: Verify proper media type headers

### Integration Layer Assertions
- **Database State**: Verify data persistence
- **Transaction Behavior**: Verify rollback functionality
- **End-to-End Flow**: Verify complete request processing

## Coverage Metrics

### Expected Coverage
- **Service Layer**: 95%+ method coverage
- **Controller Layer**: 90%+ method coverage
- **Integration Layer**: 85%+ scenario coverage

### Coverage Areas
- ✅ Happy path scenarios
- ✅ Error handling scenarios
- ✅ Edge cases
- ✅ Null value handling
- ✅ Invalid input handling
- ✅ Database operations
- ✅ HTTP request/response handling

## Best Practices Implemented

1. **Test Isolation**: Each test is independent
2. **Descriptive Names**: Test methods clearly describe scenarios
3. **Arrange-Act-Assert**: Clear test structure
4. **Mock Verification**: Verify mock interactions
5. **Exception Testing**: Test both success and failure paths
6. **Integration Testing**: End-to-end validation
7. **Transaction Management**: Proper test cleanup

## Troubleshooting

### Common Issues

1. **Test Database Connection**:
   - Ensure H2 dependency is in pom.xml
   - Check application-test.properties configuration

2. **Mock Setup Issues**:
   - Verify @Mock and @InjectMocks annotations
   - Check Mockito version compatibility

3. **JSON Serialization Issues**:
   - Ensure ObjectMapper is properly configured
   - Check DTO field annotations

4. **Transaction Rollback Issues**:
   - Verify @Transactional annotation on test class
   - Check database configuration

### Debug Mode
Enable debug logging by adding to application-test.properties:
```properties
logging.level.com.server.NewsAggrigationServer=DEBUG
logging.level.org.springframework.test=DEBUG
```

## Future Enhancements

1. **Performance Testing**: Add load testing scenarios
2. **Security Testing**: Add authentication/authorization tests
3. **API Documentation**: Generate OpenAPI documentation from tests
4. **Contract Testing**: Add consumer-driven contract tests
5. **Mutation Testing**: Add mutation testing for better coverage validation 