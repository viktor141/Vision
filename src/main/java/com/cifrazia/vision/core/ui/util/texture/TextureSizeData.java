package com.cifrazia.vision.core.ui.util.texture;

public class TextureSizeData {
    private int sizeOfTextureKit;

    public TextureSizeData(int sizeOfTextureKit) {
        this.sizeOfTextureKit = sizeOfTextureKit;
    }

    public void setScale(float scale) {
        sizeOfTextureKit = (int) (sizeOfTextureKit * scale);
    }

    public int getSizeOfTextureKit() {
        return sizeOfTextureKit;
    }
}
