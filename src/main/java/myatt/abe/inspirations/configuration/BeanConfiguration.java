package myatt.abe.inspirations.configuration;

import myatt.abe.inspirations.client.PexelClient;
import myatt.abe.inspirations.client.ZenQuoteClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

@Configuration
public class BeanConfiguration {

    @Value("${pexels.url}")
    private String pexelsUrl;

    @Value("${pexels.api.key}")
    private String pexelsApiKey;

    @Value("${zen.quotes.url}")
    private String zenQuotesUrl;

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }

    @Bean
    public PexelClient pexelClient() {
        return new PexelClient(pexelsApiKey, pexelsUrl);
    }

    @Bean
    public ZenQuoteClient zenQuoteClient() {
        return new ZenQuoteClient(zenQuotesUrl);
    }

}
