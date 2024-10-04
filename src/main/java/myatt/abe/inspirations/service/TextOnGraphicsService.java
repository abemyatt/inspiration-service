package myatt.abe.inspirations.service;

import myatt.abe.inspirations.model.zenquotes.ZenQuoteResponse;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@Service
public class TextOnGraphicsService {
    private static final String FILE_EXTENSION = ".jpeg";

    public void addTextToImage(String pathToFile, ZenQuoteResponse zenQuoteResponse) throws IOException {
        var quote = zenQuoteResponse.getQuote();
        var author = zenQuoteResponse.getAuthorName();

        var currentWorkingDirectory = System.getProperty("user.dir");
        var fullPath = currentWorkingDirectory + File.separator + "images" + File.separator + pathToFile;
        var file = new File(fullPath + FILE_EXTENSION);

        var bufferedImage = ImageIO.read(file);
        var height = bufferedImage.getHeight();
        var width = bufferedImage.getWidth();

        Graphics g = bufferedImage.getGraphics();
        g.setFont(g.getFont().deriveFont(100f));
        var stringWidth = g.getFontMetrics().stringWidth(quote);

        g.drawString(quote, (width / 2) - (stringWidth / 2), height - (height / 8));
        stringWidth = g.getFontMetrics().stringWidth(author);
        g.drawString(" - " + author, (width / 2) - (stringWidth / 2), height - (height / 14));
        g.dispose();

        var modifiedFilePath = fullPath + "-modified" + FILE_EXTENSION;
        ImageIO.write(bufferedImage, "jpeg", new File(modifiedFilePath));

        System.out.println("modified image written");
    }

}
