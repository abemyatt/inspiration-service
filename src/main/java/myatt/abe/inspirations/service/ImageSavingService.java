package myatt.abe.inspirations.service;

import myatt.abe.inspirations.model.pexels.PexelImageData;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageSavingService {

    public void saveImage(PexelImageData pexelImageData) {

        var imageAsBytes = pexelImageData.getImageAsBytes();
        var timestamp = pexelImageData.getTimestamp();

        var file = createFile(timestamp);

        saveImageToFile(imageAsBytes, file);
    }

    private File createFile(String timestamp) {
        var currentWorkingDirectory = System.getProperty("user.dir");
        var fileDestination = currentWorkingDirectory + File.separator + "images" + File.separator + timestamp + ".jpeg";
        var file = new File(fileDestination);

        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            System.out.println("uh oh couldn't create file");
            return null;
        }
    }

    private void saveImageToFile(byte[] imageAsBytes, File file) {
        var byteArrayInputStream = new ByteArrayInputStream(imageAsBytes);
        try (var outputStream = new FileOutputStream(file)) {
            outputStream.write(byteArrayInputStream.readAllBytes());
        } catch (IOException e) {
            System.out.println("Can't read bytes or maybe save idk");
        }
    }
}
