package myatt.abe.inspirations.clients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ZenQuoteClient {

    private static final String RANDOM_PATH = "/random";
    private final HttpClient httpClient;

    private final String url;

    public ZenQuoteClient(String url) {
        this.httpClient = HttpClient.newBuilder().build();
        this.url = url;
    }

    public HttpResponse<String> getRandomZenQuote() throws IOException, InterruptedException, URISyntaxException {
        var request = HttpRequest.newBuilder()
                .uri(new URI(url + RANDOM_PATH))
                .header("Accept", "application/json")
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
