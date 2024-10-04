package myatt.abe.inspirations.service;

import myatt.abe.inspirations.model.zenquotes.ZenQuoteResponse;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static myatt.abe.inspirations.utility.Constants.FILE_BASE_PATH;
import static myatt.abe.inspirations.utility.Constants.FONTS;
import static myatt.abe.inspirations.utility.Constants.FONT_STYLES;
import static myatt.abe.inspirations.utility.Constants.JPEG_FILE_EXTENSION;

@Service
public class ImageModifierService {

    public BufferedImage addTextToImage(String timestamp, ZenQuoteResponse zenQuoteResponse) throws IOException {
        var quote = zenQuoteResponse.getQuote();
        var author = zenQuoteResponse.getAuthorName();

        var file = new File(FILE_BASE_PATH + timestamp + JPEG_FILE_EXTENSION);

        var bufferedImage = ImageIO.read(file);
        var height = bufferedImage.getHeight();
        var width = bufferedImage.getWidth();
        var calculatedFontSize = (int) Math.floor(width * 0.018);

        Graphics g = bufferedImage.getGraphics();
        Font font = new Font(chooseFont(), chooseStyle(), calculatedFontSize);
        g.setFont(font);
        var stringWidth = g.getFontMetrics().stringWidth(quote);
        var stringXPosition = (width / 2) - (stringWidth / 2);
        var stringYPosition = height - (height / 8);
        var authorYOffset = height / 12;
        var transparentBoxXPadding = (int) Math.floor(width * 0.02);
        var transparentBoxYPadding = (int) Math.floor (height * 0.02);
        int alpha = 180;
        Color color = new Color(0, 0, 0, alpha);
        g.setColor(color);
        g.fillRect(stringXPosition - transparentBoxXPadding, stringYPosition - transparentBoxYPadding, stringWidth + transparentBoxXPadding, stringYPosition + transparentBoxYPadding);
        g.setColor(new Color(255, 255, 255));
        g.drawString(quote, stringXPosition, stringYPosition);

        stringWidth = g.getFontMetrics().stringWidth(author);
        stringXPosition = (width / 2) - (stringWidth / 2);
        g.drawString(author, stringXPosition, stringYPosition + authorYOffset);

        g.dispose();

        return bufferedImage;
    }

    private String chooseFont() {
        var fonts = List.of(FONTS);

        return fonts.get(ThreadLocalRandom.current().nextInt(0, fonts.size()));
    }

    private int chooseStyle() {
        List<Integer> fontStyles = List.of(FONT_STYLES);

        return fontStyles.get(ThreadLocalRandom.current().nextInt(0, fontStyles.size()));
    }
}
