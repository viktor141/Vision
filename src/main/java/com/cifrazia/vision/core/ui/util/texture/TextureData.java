package com.cifrazia.vision.core.ui.util.texture;

import com.cifrazia.vision.Vision;

public class TextureData {
    private final TextureSizeData size;
    private int x;
    private int y;
    private int width;
    private int height;

    public TextureData(TextureSizeData size, int x, int y, int width, int height) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public TextureData(int x, int y, int width, int height) {
        this(new TextureSizeData(Vision.SIZE_OF_TEXTURE_KIT), x, y, width, height);
    }

    public void setScale(float scale) {
        x = (int) (x * scale);
        y = (int) (y * scale);
        width = (int) (width * scale);
        height = (int) (height * scale);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size.getSizeOfTextureKit();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
