package com.cifrazia.vision.core.ui.buttons.donations;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;
import com.cifrazia.vision.core.ui.util.Color;

import java.util.List;

public class HeroButton extends DonationButton {
    public HeroButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        color = new Color(221, 85, 255, 255);
        name = "HERO";

        texturePosX = 192 >> 1;
        texturePosY = 88 >> 1;
        label.setY(428 >> 1);
        label.setWidth(66 >> 1);//65
        icon.setX(34 >> 1);
    }
}
