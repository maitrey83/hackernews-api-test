# HackerNews API Test Suite

Automated acceptance tests for the [HackerNews Public API](https://github.com/HackerNews/API).

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Quick Start

```bash
# Clone the repository
git clone https://github.com/maitrey83/hackernews-api-test.git
cd hackernews-api-test

# Run all tests
mvn test
```

## Running Specific Tests

```bash
# Run a single test class
mvn test -Dtest=TopStoriesApiTest

# Run a specific test method
mvn test -Dtest=TopStoriesApiTest#canRetrieveFirstComment
```

## API Observations

During testing, the following API behavior was observed:

### Non-numeric Item ID Handling

**Request:**
```
GET https://hacker-news.firebaseio.com/v0/item/abc.json
```

**Response:**
```
HTTP 200 OK
Body: null
```

**Observation:** The API returns HTTP 200 with a `null` body for non-numeric IDs. A stricter REST API would typically return HTTP 400 Bad Request for invalid input. This is not necessarily a bug, but a lenient design choice - the API treats any non-existent item the same way, regardless of why it doesn't exist.

## Project Structure

```
src/test/java/hackernews/
├── HackerNewsClient.java    # API client
├── Item.java                # Data model
├── TopStoriesApiTest.java   # Requirement 1
├── TopStoryItemTest.java    # Requirement 2
├── StoryCommentTest.java    # Requirement 3
├── EdgeCaseTest.java        # Edge cases
└── PerformanceTest.java     # Performance
```

## Tech Stack

- Java 17
- JUnit 5
- REST Assured
- AssertJ
- Jackson
