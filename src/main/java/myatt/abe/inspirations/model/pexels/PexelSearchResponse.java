package myatt.abe.inspirations.model.pexels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PexelSearchResponse {

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("page")
    private int page;

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("photos")
    private PexelPhoto[] photos;

    @JsonProperty("next_page")
    private String nextPage;


    public int getTotalResults() {
        return totalResults;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public PexelPhoto[] getPhotos() {
        return photos;
    }

    public String getNextPage() {
        return nextPage;
    }
}
