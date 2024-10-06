package myatt.abe.inspirations.model;

public class ModifiedImageProperties {

    private final int imageWidth;
    private final int imageHeight;
    private final int stringWidth;
    private final int stringXPosition;
    private final int stringYPosition;
    private final int authorYOffset;
    private final int transparentBoxXPadding;
    private final int transparentBoxYPadding;
    private final int transparentBoxXPosition;
    private final int transparentBoxYPosition;
    private final int transparentBoxWidth;
    private final int transparentBoxHeight;

    public ModifiedImageProperties(int imageWidth, int imageHeight, int stringWidth) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.stringWidth = stringWidth;
        this.stringXPosition = (imageWidth / 2) - (stringWidth / 2);
        this.stringYPosition = (int) (imageHeight - (imageHeight * 0.5));
        this.authorYOffset = (int) (stringYPosition - (stringYPosition * 0.90));
        this.transparentBoxXPadding = (int) Math.floor(imageWidth * 0.02);
        this.transparentBoxYPadding = (int) Math.floor (imageHeight * 0.05);
        this.transparentBoxXPosition = this.stringXPosition - this.transparentBoxXPadding;
        this.transparentBoxYPosition = stringYPosition - authorYOffset;
        this.transparentBoxWidth = stringWidth + (transparentBoxXPadding * 2);
        this.transparentBoxHeight = authorYOffset + (transparentBoxYPadding * 2);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getStringWidth() {
        return stringWidth;
    }

    public int getStringXPosition() {
        return stringXPosition;
    }

    public int getStringYPosition() {
        return stringYPosition;
    }

    public int getAuthorYOffset() {
        return authorYOffset;
    }

    public int getTransparentBoxXPadding() {
        return transparentBoxXPadding;
    }

    public int getTransparentBoxYPadding() {
        return transparentBoxYPadding;
    }

    public int getTransparentBoxXPosition() {
        return transparentBoxXPosition;
    }

    public int getTransparentBoxYPosition() {
        return transparentBoxYPosition;
    }

    public int getTransparentBoxWidth() {
        return transparentBoxWidth;
    }

    public int getTransparentBoxHeight() {
        return transparentBoxHeight;
    }
}
