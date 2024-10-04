package myatt.abe.inspirations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import myatt.abe.inspirations.client.ZenQuoteClient;
import myatt.abe.inspirations.model.zenquotes.ZenQuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class ZenQuoteRetrievalService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ZenQuoteClient zenQuoteClient;

    @Value("${zen.quotes.url}")
    private String zenQuotesUrl;

    public ZenQuoteResponse getRandomQuote() throws URISyntaxException, IOException, InterruptedException {

        var response = zenQuoteClient.getRandomZenQuote();

        var zenQuoteResponse = objectMapper.readValue(response.body(), ZenQuoteResponse[].class);

        return zenQuoteResponse[0]; // only retrieving one object, so index 0 is fine
    }
}
