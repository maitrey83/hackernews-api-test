package hackernews;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Edge case and negative scenario tests.
 */
@DisplayName("Edge Cases and Negative Scenarios")
class EdgeCaseTest {

    private static HackerNewsClient client;

    @BeforeAll
    static void setup() {
        client = new HackerNewsClient();
    }

    @Test
    @DisplayName("invalid item ID should return null")
    void invalidIdReturnsNull() {
        Item item = client.getItem(999999999);

        assertThat(item).isNull();
    }

    @Test
    @DisplayName("non-numeric ID should be handled gracefully")
    void nonNumericIdHandled() {
        Response response = client.getItemRaw("abc");

        // API should not crash, returns 200 with null body
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("should handle story without comments")
    void storyWithoutComments() {
        List<Integer> topStories = client.getTopStories();

        // Look for a story or job without kids
        for (int id : topStories) {
            Item item = client.getItem(id);
            if (item != null && !item.hasComments()) {
                // Found one - kids should be null or empty
                assertThat(item.getKids() == null || item.getKids().isEmpty()).isTrue();
                return;
            }
        }
        // If all stories have comments, that's fine too
    }

    @Test
    @DisplayName("last item in top stories should be valid")
    void lastTopStoryIsValid() {
        List<Integer> topStories = client.getTopStories();
        int lastIndex = topStories.size() - 1;
        Item last = client.getItem(topStories.get(lastIndex));

        assertThat(last).isNotNull();
        assertThat(last.getId()).isEqualTo(topStories.get(lastIndex));
    }
}
