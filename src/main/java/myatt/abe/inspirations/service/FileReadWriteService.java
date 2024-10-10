package myatt.abe.inspirations.service;

import myatt.abe.inspirations.model.pexels.PexelImageData;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static myatt.abe.inspirations.utility.Constants.FILE_BASE_PATH;
import static myatt.abe.inspirations.utility.Constants.JPEG_FILE_EXTENSION;

@Service
public class FileReadWriteService {

    public void savePexelImage(PexelImageData pexelImageData) {

        var imageAsBytes = pexelImageData.getImageAsBytes();
        var timestamp = pexelImageData.getTimestamp();

        var file = createFile(timestamp);

        saveImageBytesToFile(imageAsBytes, file);
    }

    public void saveBufferedImage(String timestamp, BufferedImage bufferedImage) throws IOException {
        var modifiedFilePath = FILE_BASE_PATH + timestamp + "-modified" + JPEG_FILE_EXTENSION;
        ImageIO.write(bufferedImage, "jpeg", new File(modifiedFilePath));
    }

    public void deleteFilesInDirectory(File directory) throws IOException {
        for (File file: directory.listFiles()) {
            if (file.isDirectory())
                deleteFilesInDirectory(file);
            file.delete();
        }
    }

    private File createFile(String timestamp) {
        var fullFilePath = FILE_BASE_PATH + timestamp + ".jpeg";
        var file = new File(fullFilePath);

        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            System.out.println("uh oh couldn't create file");
            return null;
        }
    }

    private void saveImageBytesToFile(byte[] imageAsBytes, File file) {
        var byteArrayInputStream = new ByteArrayInputStream(imageAsBytes);
        try (var outputStream = new FileOutputStream(file)) {
            outputStream.write(byteArrayInputStream.readAllBytes());
        } catch (IOException e) {
            System.out.println("Can't read bytes or maybe save idk");
        }
    }
}
