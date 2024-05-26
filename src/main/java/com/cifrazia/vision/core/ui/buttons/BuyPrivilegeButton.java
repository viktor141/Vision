package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.StaticButton;

public class BuyPrivilegeButton extends StaticButton {


    public BuyPrivilegeButton(Gui parentGUI, int x, int y, String buttonText, int color) {
        super(parentGUI, x, y, 190 >> 1, 64 >> 1, buttonText, color);
    }
}
