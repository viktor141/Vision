package com.cifrazia.vision.core.ui.buttons.donations;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;
import com.cifrazia.vision.core.ui.util.Color;

import java.util.List;

public class MasterButton extends DonationButton {

    public MasterButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        color = new Color(85, 153, 255, 255);
        name = "MASTER";

        texturePosX = 0;
        texturePosY = 88 >> 1;
        label.setY(398 >> 1);
        label.setWidth(98 >> 1);//97
        label.setX(0);
    }
}
