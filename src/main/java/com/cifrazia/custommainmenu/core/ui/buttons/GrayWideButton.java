package com.cifrazia.custommainmenu.core.ui.buttons;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.WideButton;

public class GrayWideButton extends WideButton {
    public GrayWideButton(Gui parentGUI, int x, int y, String buttonText) {
        super(parentGUI, x, y, buttonText);
        this.texturePosX = 270 >> 1;
        this.texturePosY = 212 >> 1;
    }
}
