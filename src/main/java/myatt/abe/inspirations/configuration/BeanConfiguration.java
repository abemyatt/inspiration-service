package myatt.abe.inspirations.configuration;

import myatt.abe.inspirations.client.PexelClient;
import myatt.abe.inspirations.client.ZenQuoteClient;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

import java.util.EnumSet;

@Configuration
public class BeanConfiguration {

    @Value("${pexels.url}")
    private String pexelsUrl;

    @Value("${pexels.api.key}")
    private String pexelsApiKey;

    @Value("${zen.quotes.url}")
    private String zenQuotesUrl;

    @Value("${inspiration.for.the.boys.discord.token}")
    private String discordToken;

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

    @Bean
    public JDA jda() {
        return JDABuilder.createLight(discordToken, EnumSet.noneOf(GatewayIntent.class))
                .build();
    }

}
