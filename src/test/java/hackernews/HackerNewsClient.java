package hackernews;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

/**
 * Simple client for interacting with the HackerNews API.
 */
public class HackerNewsClient {

    private static final String BASE_URL = "https://hacker-news.firebaseio.com/v0";

    /**
     * Calls GET https://hacker-news.firebaseio.com/v0/topstories.json
     * The API returns a JSON array like [42345, 42344, 42343, ...]
     **/
    public List<Integer> getTopStories() {
        return RestAssured
                .get(BASE_URL + "/topstories.json")
                .jsonPath()
                .getList("$", Integer.class);
    }

    // Returns raw HTTP response for testing status codes, headers, and timing
    public Response getTopStoriesRaw() {
        return RestAssured.get(BASE_URL + "/topstories.json");
    }

    // Fetches item by ID, returns null if not found or invali
    public Item getItem(int id) {
        Response response = RestAssured.get(BASE_URL + "/item/" + id + ".json");
        if (response.getStatusCode() != 200) {
            return null;
        }
        String body = response.getBody().asString();
        if (body == null || body.equals("null")) {
            return null;
        }
        return response.as(Item.class);
    }

    // Returns raw HTTP response for testing status codes, headers, and timing
    public Response getItemRaw(int id) {
        return RestAssured.get(BASE_URL + "/item/" + id + ".json");
    }

    // Returns raw HTTP response for testing non-numeric ID handling
    public Response getItemRaw(String id) {
        return RestAssured.get(BASE_URL + "/item/" + id + ".json");
    }
}
