package hackernews;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Performance tests.
 */
@DisplayName("Performance Tests")
class PerformanceTest {

    private static HackerNewsClient client;

    @BeforeAll
    static void setup() {
        client = new HackerNewsClient();
    }

    @Test
    @DisplayName("top stories endpoint should respond quickly")
    void topStoriesResponseTime() {
        Response response = client.getTopStoriesRaw();

        System.out.println("Response time: " + response.getTime() + "ms");
        //No official benchmark from HackerNews API, using 2 seconds as acceptable threshold
        assertThat(response.getTime())
                .as("Response time should be under 2 seconds")
                .isLessThan(2000L);
    }
}
