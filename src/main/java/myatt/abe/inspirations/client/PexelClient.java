package myatt.abe.inspirations.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static myatt.abe.inspirations.utility.Constants.ACCEPT_HEADER;
import static myatt.abe.inspirations.utility.Constants.APPLICATION_JSON;
import static myatt.abe.inspirations.utility.Constants.AUTHORIZATION_HEADER;
import static myatt.abe.inspirations.utility.Constants.CONTENT_TYPE_HEADER;
import static myatt.abe.inspirations.utility.Constants.IMAGE_JPEG;

public class PexelClient {

    private static final String CURATED_PATH = "/curated";

    private static final String SEARCH_PATH = "/search";

    private static final String QUERY_PARAM = "?query=";

    private static final String PER_PAGE_PARAM = "?per_page=";

    private final HttpClient httpClient;
    private final String apiKey;

    private final String url;

    public PexelClient(String apiKey, String url) {
        httpClient = HttpClient.newBuilder().build();
        this.apiKey = apiKey;
        this.url = url;
    }

    public HttpResponse<String> getCuratedImage() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(new URI(url + CURATED_PATH + PER_PAGE_PARAM + "1"))
                .header(AUTHORIZATION_HEADER, apiKey)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getRandomImage() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(new URI(url + CURATED_PATH + PER_PAGE_PARAM + "80"))
                .header(AUTHORIZATION_HEADER, apiKey)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getSearchImage(String query) throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(new URI(url + SEARCH_PATH + QUERY_PARAM + query))
                .header(AUTHORIZATION_HEADER, apiKey)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<byte[]> getPhoto(String photoUrl) throws IOException, InterruptedException, URISyntaxException {
        var request = HttpRequest.newBuilder()
                .uri(new URI(photoUrl))
                .header(AUTHORIZATION_HEADER, apiKey)
                .header(CONTENT_TYPE_HEADER, IMAGE_JPEG)
                .header(ACCEPT_HEADER, IMAGE_JPEG)
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
    }
}
