package myatt.abe.inspirations.controller;

import myatt.abe.inspirations.service.FileReadWriteService;
import myatt.abe.inspirations.service.ImageModifierService;
import myatt.abe.inspirations.service.PexelsImageRetrievalService;
import myatt.abe.inspirations.service.ZenQuoteRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@Profile("test")
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private ZenQuoteRetrievalService zenQuoteRetrievalService;

    @Autowired
    private PexelsImageRetrievalService pexelsImageRetrievalService;

    @Autowired
    private FileReadWriteService fileReadWriteService;

    @Autowired
    private ImageModifierService imageModifierService;

    @GetMapping("/zen-quote")
    public ResponseEntity<String> getZenQuote() throws URISyntaxException, IOException, InterruptedException {
        var zenQuote = zenQuoteRetrievalService.getRandomQuote();

        return new ResponseEntity<>(zenQuote.toString(), HttpStatus.OK);
    }

    @GetMapping("/pexels-curated")
    public ResponseEntity<String> getPexelsCuratedImage() throws URISyntaxException, IOException, InterruptedException {
        var pexelImage = pexelsImageRetrievalService.retrieveCuratedPhoto();

        var imageBytes = pexelsImageRetrievalService.downloadImage(pexelImage);

        fileReadWriteService.savePexelImage(imageBytes);

        return new ResponseEntity<>(pexelImage.toString(), HttpStatus.OK);
    }

    @GetMapping("/pexels-search")
    public ResponseEntity<String> getPexelsSearchImage(@RequestParam String query) throws URISyntaxException, IOException, InterruptedException {
        var pexelImage = pexelsImageRetrievalService.retrieveSearchPhotos(query);

        var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);

        fileReadWriteService.savePexelImage(pexelImageData);

        return new ResponseEntity<>(pexelImage.toString(), HttpStatus.OK);
    }

    @GetMapping("/add-text-to-image-curated")
    public ResponseEntity<String> addTextToImageCurated() throws IOException, URISyntaxException, InterruptedException {
        var zenQuote = zenQuoteRetrievalService.getRandomQuote();

        var pexelImage = pexelsImageRetrievalService.retrieveCuratedPhoto();

        var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);

        fileReadWriteService.savePexelImage(pexelImageData);

        var timestamp = pexelImageData.getTimestamp();

        var bufferedImage = imageModifierService.addTextToImage(timestamp, zenQuote);

        fileReadWriteService.saveBufferedImage(timestamp, bufferedImage);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/add-text-to-image-search")
    public ResponseEntity<String> addTextToImageSearch(@RequestParam(name = "query") String query) throws IOException, URISyntaxException, InterruptedException {
        var zenQuote = zenQuoteRetrievalService.getRandomQuote();

        var pexelImage = pexelsImageRetrievalService.retrieveSearchPhotos(query);

        var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);

        fileReadWriteService.savePexelImage(pexelImageData);

        var timestamp = pexelImageData.getTimestamp();

        var bufferedImage = imageModifierService.addTextToImage(timestamp, zenQuote);

        fileReadWriteService.saveBufferedImage(timestamp, bufferedImage);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
