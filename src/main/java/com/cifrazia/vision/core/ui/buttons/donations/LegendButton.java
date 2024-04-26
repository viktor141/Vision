package com.cifrazia.vision.core.ui.buttons.donations;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;

import java.util.List;

public class LegendButton extends DonationButton {

    public LegendButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        name = "LEGEND";

        texturePosX = 0;
        texturePosY = 138 >> 1;
        label.setY(416 >> 1);
        label.setWidth(82 >> 1);//81
    }
}
