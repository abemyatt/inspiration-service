package myatt.abe.inspirations.model.pexels;

public class PexelImageData {

    private int width;

    private int height;

    private byte[] imageAsBytes;

    public PexelImageData(int width, int height, byte[] imageAsBytes) {
        this.width = width;
        this.height = height;
        this.imageAsBytes = imageAsBytes;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public byte[] getImageAsBytes() {
        return imageAsBytes;
    }

    public void setImageAsBytes(byte[] imageAsBytes) {
        this.imageAsBytes = imageAsBytes;
    }
}
