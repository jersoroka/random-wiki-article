import org.json.JSONArray;
import org.json.JSONObject;

// represents a class that parses strings from wikipedia requests
public class Parser {
    private int id;
    private String title;
    private String url;

    // MODIFIES: this
    // EFFECTS: parses the page id and title from the random page response body
    public void parseRandomPage(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject query = jsonObject.getJSONObject("query");
        JSONArray random = query.getJSONArray("random");
        JSONObject page = random.getJSONObject(0);

        id = page.getInt("id");
        title = page.getString("title");
    }

    // MODIFIES: this
    // EFFECTS: parses the url from the random page response body
    public void parsePage(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject query = jsonObject.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        JSONObject page = pages.getJSONObject(String.valueOf(id));

        url = page.getString("fullurl");
    }

    // getters
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() { return url; }
}
