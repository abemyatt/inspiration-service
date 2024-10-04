package myatt.abe.inspirations.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static myatt.abe.inspirations.utility.Constants.ACCEPT_HEADER;
import static myatt.abe.inspirations.utility.Constants.APPLICATION_JSON;

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
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
