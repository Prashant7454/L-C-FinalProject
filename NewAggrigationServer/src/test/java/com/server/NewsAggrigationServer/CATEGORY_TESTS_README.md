# Category Test Suite Documentation

## Overview
This test suite provides comprehensive testing for the Category functionality in the News Aggrigation Server application. The tests cover the complete flow from REST API endpoints through the service layer to the data access layer.

## Test Structure

### 1. CategoryControllerTest
**Location**: `controller/CategoryControllerTest.java`
**Type**: Unit Tests
**Purpose**: Tests the REST API endpoints for Category operations

**Test Coverage**:
- ✅ `POST /api/categories` - Create category
- ✅ `GET /api/categories` - Get all categories
- ✅ `GET /api/categories/{id}` - Get category by ID
- ✅ `GET /api/categories/news/{newsId}` - Get categories by news ID
- ✅ `POST /api/categories/by-ids` - Get categories by IDs
- ✅ `GET /api/categories/visible` - Get visible categories only
- ✅ `POST /api/categories/visible/by-ids` - Get visible categories by IDs
- ✅ `PUT /api/categories/{id}/hide` - Hide category
- ✅ `PUT /api/categories/{id}/unhide` - Unhide category

**Test Scenarios**:
- Success cases for all endpoints
- Error handling for not found scenarios
- Exception handling for duplicate categories
- Validation of response structure and content

### 2. CategoryServiceImplTest
**Location**: `service/CategoryServiceImplTest.java`
**Type**: Unit Tests
**Purpose**: Tests the business logic in the CategoryService implementation

**Test Coverage**:
- ✅ `createCategory()` - Create new category
- ✅ `getCategoryById()` - Retrieve category by ID
- ✅ `getAllCategories()` - Get all categories
- ✅ `getAllCategoriesByIds()` - Get categories by IDs
- ✅ `getAllVisibleCategories()` - Get visible categories only
- ✅ `getVisibleCategoriesByIds()` - Get visible categories by IDs
- ✅ `hideCategory()` - Hide a category
- ✅ `unhideCategory()` - Unhide a category

**Test Scenarios**:
- Success cases for all methods
- Error handling for not found scenarios
- Duplicate category creation prevention
- Null and empty input validation
- Repository interaction verification

### 3. CategoryIntegrationTest
**Location**: `integration/CategoryIntegrationTest.java`
**Type**: Integration Tests
**Purpose**: Tests the complete flow from controller to database

**Test Coverage**:
- ✅ End-to-end category creation and retrieval
- ✅ Duplicate category prevention
- ✅ Category hiding and unhiding workflow
- ✅ Bulk operations (get all, get by IDs)
- ✅ Error scenarios with real database interactions

**Test Scenarios**:
- Complete CRUD operations
- Business rule validation
- Database state verification
- Transaction handling

## Running the Tests

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- H2 database (included in test dependencies)

### Running All Tests
```bash
# Run all Category tests
mvn test -Dtest=CategoryTestRunner

# Run specific test class
mvn test -Dtest=CategoryControllerTest
mvn test -Dtest=CategoryServiceImplTest
mvn test -Dtest=CategoryIntegrationTest

# Run all tests in the project
mvn test
```

### Running from IDE
1. Right-click on `CategoryTestRunner.java` and select "Run CategoryTestRunner"
2. Or run individual test classes as needed
3. Or run specific test methods within each class

## Test Configuration

### Test Properties
The tests use `application-test.properties` with the following configuration:
- H2 in-memory database for testing
- Automatic schema creation and cleanup
- SQL logging enabled for debugging
- Security auto-configuration disabled

### Dependencies
- Spring Boot Test Starter
- H2 Database (test scope)
- Mockito for mocking
- JUnit 5 for test framework

## Test Data

### Sample Categories Used in Tests
- Technology (visible)
- Sports (visible)
- Politics (hidden)
- Science (visible)

### Test Scenarios
1. **Happy Path**: Normal operations with valid data
2. **Error Handling**: Invalid IDs, missing data, duplicates
3. **Edge Cases**: Empty lists, null values, boundary conditions
4. **Business Rules**: Category visibility, duplicate prevention

## Assertions and Verifications

### Controller Tests
- HTTP status codes
- Response body structure
- JSON path validations
- Service method invocations

### Service Tests
- Return value validation
- Exception handling
- Repository method calls
- Business logic verification

### Integration Tests
- Database state verification
- End-to-end workflow validation
- Transaction integrity
- Real data persistence

## Best Practices Implemented

1. **Test Isolation**: Each test is independent and doesn't affect others
2. **Mocking**: External dependencies are properly mocked
3. **Naming Convention**: Clear, descriptive test method names
4. **Setup/Teardown**: Proper test data initialization and cleanup
5. **Assertions**: Comprehensive validation of expected outcomes
6. **Documentation**: Clear comments explaining test scenarios

## Coverage Metrics

The test suite provides:
- **Controller Layer**: 100% endpoint coverage
- **Service Layer**: 100% method coverage
- **Integration**: Complete workflow coverage
- **Error Scenarios**: Comprehensive exception handling

## Troubleshooting

### Common Issues
1. **Database Connection**: Ensure H2 dependency is included
2. **Test Context**: Verify `@SpringBootTest` annotations
3. **Mock Setup**: Check Mockito configurations
4. **Transaction Rollback**: Ensure `@Transactional` is used

### Debug Mode
Enable debug logging by setting:
```properties
logging.level.com.server.NewsAggrigationServer=DEBUG
logging.level.org.springframework.web=DEBUG
```

## Future Enhancements

1. **Performance Tests**: Add load testing for bulk operations
2. **Security Tests**: Add authentication/authorization tests
3. **API Documentation Tests**: Validate OpenAPI specifications
4. **Contract Tests**: Add consumer-driven contract testing 