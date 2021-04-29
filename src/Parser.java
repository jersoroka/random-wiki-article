import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {
    private int id;
    private String title;
    private String url;

    public void parseRandomPage(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject query = jsonObject.getJSONObject("query");
        JSONArray random = query.getJSONArray("random");
        JSONObject page = random.getJSONObject(0);

        id = page.getInt("id");
        title = page.getString("title");
    }

    public void parsePage(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject query = jsonObject.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        JSONObject page = pages.getJSONObject(String.valueOf(id));

        url = page.getString("fullurl");

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() { return url; }
}
