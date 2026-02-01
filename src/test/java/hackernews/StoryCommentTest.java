package hackernews;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for retrieving comments from a top story.
 * Requirement 3: Using the Top Stories API to retrieve a top story, 
 *                retrieve its first comment using the Items API
 */
@DisplayName("Story Comment Retrieval")
class StoryCommentTest {

    private static HackerNewsClient client;
    private Item storyWithComments;
    private Item firstComment;

    @BeforeAll
    static void setup() {
        client = new HackerNewsClient();
    }

    @BeforeEach
    void fetchStoryAndComment() {
        List<Integer> topStories = client.getTopStories();

        // Find a story that has comments
        for (int storyId : topStories) {
            Item story = client.getItem(storyId);
            if (story != null && story.hasComments()) {
                storyWithComments = story;
                int firstCommentId = story.getKids().get(0);
                firstComment = client.getItem(firstCommentId);
                break;
            }
        }
    }

    @Test
    @DisplayName("should retrieve the first comment")
    void canRetrieveFirstComment() {
        assertThat(firstComment).isNotNull();
    }

    @Test
    @DisplayName("comment parent should match the story ID")
    void parentMatchesStory() {
        assertThat(firstComment.getParent()).isEqualTo(storyWithComments.getId());
    }

    @Test
    @DisplayName("comment should have an author")
    void commentHasAuthor() {
        if (firstComment.getDeleted() == null || !firstComment.getDeleted()) {
            assertThat(firstComment.getBy()).isNotBlank();
        }
    }

    @Test
    @DisplayName("comment should have a timestamp")
    void commentHasTimestamp() {
        assertThat(firstComment.getTime()).isNotNull();
        assertThat(firstComment.getTime()).isPositive();
    }
}
