package myatt.abe.inspirations.model.zenquotes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ZenQuoteResponse {

    @JsonProperty("q")
    private String quote;

    @JsonProperty("a")
    private String authorName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("c")
    private int characterCount;

    @JsonProperty("h")
    private String preFormattedHTMLQuote;

    public String getQuote() {
        return quote;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public String getPreFormattedHTMLQuote() {
        return preFormattedHTMLQuote;
    }

    @Override
    public String toString() {
        return "ZenQuote{" +
                "quote='" + quote + '\'' +
                ", authorName='" + authorName + '\'' +
                ", characterCount=" + characterCount +
                ", preFormattedHTMLQuote='" + preFormattedHTMLQuote + '\'' +
                '}';
    }
}
