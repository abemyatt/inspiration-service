package myatt.abe.inspirations.model.pexels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PexelPhoto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("url")
    private String url;

    @JsonProperty("photographer")
    private String photographer;

    @JsonProperty("photographer_url")
    private String photographerUrl;

    @JsonProperty("photographer_id")
    private int photographerId;

    @JsonProperty("avg_color")
    private String avgColor;

    @JsonProperty("src")
    private PexelSrc src;

    @JsonProperty("liked")
    private boolean liked;

    @JsonProperty("alt")
    private String alt;

    public int getId() {
        return id;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getPhotographer() {
        return photographer;
    }


    public String getPhotographerUrl() {
        return photographerUrl;
    }


    public int getPhotographerId() {
        return photographerId;
    }


    public String getAvgColor() {
        return avgColor;
    }

    public PexelSrc getSrc() {
        return src;
    }

    public boolean isLiked() {
        return liked;
    }


    public String getAlt() {
        return alt;
    }

}
