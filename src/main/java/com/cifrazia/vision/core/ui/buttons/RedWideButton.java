package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.WideButton;

public class RedWideButton extends WideButton {
    public RedWideButton(Gui parentGUI, int x, int y, String buttonText) {
        super(parentGUI, x, y, buttonText);
        this.texturePosX = 62 >> 1;
        this.texturePosY = 212 >> 1;
    }

}
