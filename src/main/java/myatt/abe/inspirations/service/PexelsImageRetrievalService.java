package myatt.abe.inspirations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import myatt.abe.inspirations.client.PexelClient;
import myatt.abe.inspirations.model.pexels.PexelCuratedResponse;
import myatt.abe.inspirations.model.pexels.PexelImageData;
import myatt.abe.inspirations.model.pexels.PexelPhoto;
import myatt.abe.inspirations.model.pexels.PexelSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PexelsImageRetrievalService {

    @Autowired
    private PexelClient pexelClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${pexels.url}")
    private String pexelsUrl;

    @Value("${pexels.api.key}")
    private String pexelsApiKey;

    public PexelPhoto retrieveCuratedPhoto() throws IOException, InterruptedException, URISyntaxException {
        var responseBody = pexelClient.getCuratedImage().body();

        var photos = objectMapper.readValue(responseBody, PexelCuratedResponse.class);

        return photos.getPhotos()[0]; // return the first photo
    }

    public PexelPhoto retrieveRandomPhoto() throws IOException, InterruptedException, URISyntaxException {
        var responseBody = pexelClient.getRandomImage().body();

        var photos = objectMapper.readValue(responseBody, PexelCuratedResponse.class);

        var randomPhotoIndex = ThreadLocalRandom.current().nextInt(photos.getPhotos().length - 1);

        return photos.getPhotos()[randomPhotoIndex]; // return the first photo
    }

    public PexelPhoto retrieveSearchPhotos(String query) throws IOException, InterruptedException, URISyntaxException {

        var responseBody = pexelClient.getSearchImage(query).body();

        var photos = objectMapper.readValue(responseBody, PexelSearchResponse.class);

        var randomPhotoIndex = ThreadLocalRandom.current().nextInt(photos.getPhotos().length - 1);

        return photos.getPhotos()[randomPhotoIndex]; // return the first photo
    }

    public PexelImageData downloadImage(PexelPhoto pexelPhoto) throws IOException, URISyntaxException, InterruptedException {

        var url = pexelPhoto.getSrc().getOriginal();

        var imageData = pexelClient.getPhoto(url).body();

        return new PexelImageData(imageData);
    }
}
