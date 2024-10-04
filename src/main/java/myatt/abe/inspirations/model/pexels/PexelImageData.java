package myatt.abe.inspirations.model.pexels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PexelImageData {
    private final byte[] imageAsBytes;

    private final String timestamp;

    public PexelImageData(byte[] imageAsBytes) {
        this.imageAsBytes = imageAsBytes;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
    }

    public byte[] getImageAsBytes() {
        return imageAsBytes;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
