package com.cifrazia.vision.core.ui.buttons.donations;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;
import com.cifrazia.vision.core.ui.util.Color;

import java.util.List;

public class ImmortalButton extends DonationButton {
    public ImmortalButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        color = new Color(223, 191, 78, 255);
        name = "IMMORTAL";

        texturePosX = 192 >> 1;
        texturePosY = 140 >> 1;
        label.setY(488 >> 1);
        label.setWidth(130 >> 1);//129
        icon.setX(102 >> 1);
    }
}
