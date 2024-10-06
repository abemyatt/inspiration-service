package myatt.abe.inspirations.service;

import myatt.abe.inspirations.model.ModifiedImageProperties;
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

        var modifiedImageProperties = new ModifiedImageProperties(width, height, stringWidth);

        int alpha = 180;
        Color color = new Color(0, 0, 0, alpha);
        g.setColor(color);
        g.fillRect(modifiedImageProperties.getTransparentBoxXPosition(), modifiedImageProperties.getTransparentBoxYPosition(),
                modifiedImageProperties.getTransparentBoxWidth(), modifiedImageProperties.getTransparentBoxHeight());
        g.setColor(new Color(255, 255, 255));
        g.drawString(quote, modifiedImageProperties.getStringXPosition(), modifiedImageProperties.getStringYPosition());

        stringWidth = g.getFontMetrics().stringWidth(author);
        modifiedImageProperties = new ModifiedImageProperties(width, height, stringWidth);

        var authorYOffset = height / 12;
        g.drawString(author, modifiedImageProperties.getStringXPosition(),
                modifiedImageProperties.getStringYPosition() + authorYOffset);

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
