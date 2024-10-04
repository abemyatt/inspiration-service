package myatt.abe.inspirations.utility;

import java.io.File;

import static java.awt.Font.BOLD;
import static java.awt.Font.DIALOG;
import static java.awt.Font.DIALOG_INPUT;
import static java.awt.Font.ITALIC;
import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import static java.awt.Font.SANS_SERIF;
import static java.awt.Font.SERIF;

public final class Constants {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String ACCEPT_HEADER = "Accept";
    public static final String APPLICATION_JSON = "application/json";
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String JPEG_FILE_EXTENSION = ".jpeg";

    public static final String FILE_BASE_PATH = System.getProperty("user.dir") + File.separator + "images" + File.separator;

    public static final String[] FONTS = new String[] {
            MONOSPACED, SANS_SERIF, SERIF, DIALOG, DIALOG_INPUT
    };

    public static final Integer[] FONT_STYLES = new Integer[] {
            PLAIN, BOLD, ITALIC
    };

    private Constants(){
        // private constructor
    }
}
