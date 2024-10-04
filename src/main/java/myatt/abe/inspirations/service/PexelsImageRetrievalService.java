package myatt.abe.inspirations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import myatt.abe.inspirations.clients.PexelClient;
import myatt.abe.inspirations.model.pexels.PexelCuratedResponse;
import myatt.abe.inspirations.model.pexels.PexelImageData;
import myatt.abe.inspirations.model.pexels.PexelPhoto;
import myatt.abe.inspirations.model.pexels.PexelSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public PexelPhoto retrieveCuratedPhotos() throws IOException, InterruptedException, URISyntaxException {
        var responseBody = pexelClient.getCuratedImage().body();

        var photos = objectMapper.readValue(responseBody, PexelCuratedResponse.class);

        return photos.getPhotos()[0]; // return the first photo
    }

    public PexelPhoto retrieveSearchPhotos() throws IOException, InterruptedException, URISyntaxException {

        var responseBody = pexelClient.getSearchImage().body();

        var photos = objectMapper.readValue(responseBody, PexelSearchResponse.class);

        return photos.getPhotos()[0]; // return the first photo
    }

    public PexelImageData downloadImage(PexelPhoto pexelPhoto) throws IOException, URISyntaxException, InterruptedException {

        var url = pexelPhoto.getSrc().getOriginal();

        var imageData = pexelClient.getPhoto(url).body();

        return new PexelImageData(imageData);
    }
}
