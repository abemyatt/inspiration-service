package myatt.abe.inspirations.model.pexels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PexelSrc {

    @JsonProperty("original")
    private String original;

    @JsonProperty("large2x")
    private String large2x;

    @JsonProperty("large")
    private String large;

    @JsonProperty("medium")
    private String medium;

    @JsonProperty("small")
    private String small;

    @JsonProperty("potrait")
    private String potrait;

    @JsonProperty("landscape")
    private String landscape;

    @JsonProperty("tiny")
    private String tiny;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getLarge2x() {
        return large2x;
    }

    public void setLarge2x(String large2x) {
        this.large2x = large2x;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getPotrait() {
        return potrait;
    }

    public void setPotrait(String potrait) {
        this.potrait = potrait;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public String getTiny() {
        return tiny;
    }

    public void setTiny(String tiny) {
        this.tiny = tiny;
    }
}
