package hackernews;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the Top Stories endpoint.
 * Requirement 1: Retrieving top stories with the Top Stories API
 **/
@DisplayName("Top Stories API")
class TopStoriesApiTest {

    private static HackerNewsClient client;

    @BeforeAll
    static void setup() {
        client = new HackerNewsClient();
    }

    @Test
    @DisplayName("should return a non-empty list of story IDs")
    void returnsNonEmptyArray() {
        List<Integer> topStories = client.getTopStories();

        assertThat(topStories).isNotNull();
        assertThat(topStories).isNotEmpty();
    }

    @Test
    @DisplayName("should return at most 500 stories")
    void returnsAtMost500Items() {
        List<Integer> topStories = client.getTopStories();

        assertThat(topStories.size()).isLessThanOrEqualTo(500);
    }

    @Test
    @DisplayName("should return only positive integer IDs")
    void allIdsArePositive() {
        List<Integer> topStories = client.getTopStories();

        assertThat(topStories).allMatch(id -> id > 0, "All IDs should be positive");
    }
}
