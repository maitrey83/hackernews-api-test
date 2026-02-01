package hackernews;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for retrieving the top story item.
 * Requirement 2: Using the Top Stories API to retrieve the current top story from the Items API
 */
@DisplayName("Top Story Item Retrieval")
class TopStoryItemTest {

    private static HackerNewsClient client;
    private Item topStory;
    private int topStoryId;

    @BeforeAll
    static void setup() {
        client = new HackerNewsClient();
    }

    @BeforeEach
    void fetchTopStory() {
        List<Integer> topStories = client.getTopStories();
        topStoryId = topStories.get(0);
        topStory = client.getItem(topStoryId);
    }

    @Test
    @DisplayName("should retrieve top story from Items API")
    void canRetrieveTopStory() {
        assertThat(topStory).isNotNull();
    }

    @Test
    @DisplayName("should have matching ID")
    void idMatchesRequested() {
        assertThat(topStory.getId()).isEqualTo(topStoryId);
    }

    @Test
    @DisplayName("should be a story or job type")
    void typeIsStoryOrJob() {
        // Top stories endpoint can include jobs
        assertThat(topStory.getType()).isIn("story", "job");
    }

    @Test
    @DisplayName("should have a title")
    void hasTitle() {
        assertThat(topStory.getTitle()).isNotBlank();
    }

    @Test
    @DisplayName("should have an author")
    void hasAuthor() {
        assertThat(topStory.getBy()).isNotBlank();
    }
}
