package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.StaticButton;
import com.cifrazia.vision.core.ui.util.texture.TextureData;

public class BuyPrivilegeButton extends StaticButton {

    public static final TextureData yellow = new TextureData(212 >> 1, 196 >> 1, 190 >> 1, 64 >> 1);
    public static final TextureData green = new TextureData(212 >> 1, 268 >> 1, 190 >> 1, 64 >> 1);
    public static final TextureData gray = new TextureData(212 >> 1, 340 >> 1, 190 >> 1, 64 >> 1);


    public BuyPrivilegeButton(Gui parentGUI, int x, int y, String buttonText) {
        super(parentGUI, x, y, 190 >> 1, 64 >> 1, buttonText, -1);

        this.buttonTextureKit = Vision.DONATION_KIT;

        uHovered = 0;
        v = 218 >> 1;

        borderTextureWidth = 196 >> 1;
        borderTextureHeight = 76 >> 1;
        setColor(gray);
    }


    public void setColor(TextureData data) {
        texturePosX = data.getX();
        texturePosY = data.getY();
    }
}
