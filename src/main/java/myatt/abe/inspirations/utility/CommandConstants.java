package myatt.abe.inspirations.utility;

public final class CommandConstants {

    public static final String RANDOM_QUOTE_COMMAND = "random-quote";
    public static final String RANDOM_QUOTE_DESCRIPTION = "Retrieves a random quote";

    public static final String RANDOM_IMAGE_COMMAND = "random-image";
    public static final String RANDOM_IMAGE_DESCRIPTION = "Retrieves a random image";

    public static final String CURATED_IMAGE_COMMAND = "curated-image";
    public static final String CURATED_IMAGE_DESCRIPTION = "Gets the current curated image";

    public static final String IMAGE_SEARCH_COMMAND = "image-search";
    public static final String IMAGE_SEARCH_DESCRIPTION = "Retrieves an image based on the search query";
    public static final String IMAGE_SEARCH_QUERY_DESCRIPTION = "The search query used to retrieve an image";

    public static final String RANDOM_INSPIRATION_COMMAND = "random-inspiration";
    public static final String RANDOM_INSPIRATION_DESCRIPTION = "Adds a random quote to a random image - Are you inspired yet?";

    public static final String CURATED_INSPIRATION_COMMAND = "curated-inspiration";
    public static final String CURATED_INSPIRATION_DESCRIPTION = "Adds a random quote to a curated image, new image daily - truly inspiring";

    public static final String INSPIRATIONAL_IMAGE_SEARCH_COMMAND = "inspirational-image-search";
    public static final String INSPIRATIONAL_IMAGE_SEARCH_DESCRIPTION = "Adds a random quote to an image retrieved based on the search query";

    public static final String SEARCH_QUERY_OPTION = "search-query";


    private CommandConstants(){

    };
}
