import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {
    private int id;
    private int ns;
    private String title;

    public void parse(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject query = jsonObject.getJSONObject("query");
        JSONArray random = query.getJSONArray("random");
        JSONObject page = random.getJSONObject(0);

        id = page.getInt("id");
        ns = page.getInt("ns");
        title = page.getString("title");
    }

    public int getId() {
        return id;
    }

    public int getNs() {
        return ns;
    }

    public String getTitle() {
        return title;
    }
}
