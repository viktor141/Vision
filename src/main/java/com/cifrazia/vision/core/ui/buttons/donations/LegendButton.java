package com.cifrazia.vision.core.ui.buttons.donations;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;
import com.cifrazia.vision.core.ui.util.Color;

import java.util.List;

public class LegendButton extends DonationButton {

    public LegendButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        color = new Color(244, 61, 61, 255);
        name = "LEGEND";

        texturePosX = 0;
        texturePosY = 140 >> 1;
        label.setY(458 >> 1);
        label.setWidth(98 >> 1);//97
        icon.setX(68 >> 1);
    }
}
