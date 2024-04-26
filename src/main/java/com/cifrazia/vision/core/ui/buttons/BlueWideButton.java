package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.WideButton;

public class BlueWideButton extends WideButton {

    public BlueWideButton(Gui parentGUI, int x, int y, String buttonText) {
        super(parentGUI, x, y, buttonText);
        this.texturePosX = 314 >> 1;
        this.texturePosY = 128 >> 1;
    }
}
