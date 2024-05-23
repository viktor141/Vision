package com.cifrazia.vision.core.ui.buttons.modal;

import com.cifrazia.vision.core.abstracts.Gui;

public class ModalBlueButton extends ModalGreenButton {


    public ModalBlueButton(Gui parentGUI, int x, int y, String buttonText, int color) {
        super(parentGUI, x, y, buttonText, color);

        texturePosX = 220 >> 1;
        texturePosY = 0;
    }
}
