package com.cifrazia.vision.core.ui.buttons.modal;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.StaticButton;

public class ModalGreenButton extends StaticButton {

    public ModalGreenButton(Gui parentGUI, int x, int y, String buttonText, int color) {
        super(parentGUI, x, y, 202 >> 1, 52 >> 1, buttonText, color);

        buttonTextureKit = Vision.MODAL_KIT;

        uHovered = 216 >> 1;
        v = 84 >> 1;
        borderTextureWidth = 210 >> 1;
        borderTextureHeight = 60 >> 1;
        texturePosX = 0;
        texturePosY = 88 >> 1;
    }

    @Override
    protected void borderSet() {
        borderX = x - 2;
        borderY = y - 2;
    }
}
