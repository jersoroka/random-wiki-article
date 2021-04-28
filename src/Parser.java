import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {
    private static int id;
    private static int ns;
    private static String title;

    public static void parse(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject query = jsonObject.getJSONObject("query");
        JSONArray random = query.getJSONArray("random");
        JSONObject page = random.getJSONObject(0);

        id = page.getInt("id");
        ns = page.getInt("ns");
        title = page.getString("title");
    }

}
