import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class HttpRequest {
    private static final String REQUEST_RANDOM_URL = "https://en.wikipedia.org/w/api.php?action=query&list=random&format=json&rnnamespace=0&rnlimit=1";

    public static void requestRandomPage(Parser parser) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(REQUEST_RANDOM_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        parser.parseRandomPage(response.body());
    }

    public static void requestPage(Parser parser) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://en.wikipedia.org/w/api.php?action=query&prop=info&pageids=" + parser.getId()
                        + "&inprop=url&format=json"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        parser.parsePage(response.body());
    }

}
