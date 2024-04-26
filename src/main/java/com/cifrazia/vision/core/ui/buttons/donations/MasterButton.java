package com.cifrazia.vision.core.ui.buttons.donations;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;

import java.util.List;

public class MasterButton extends DonationButton {

    public MasterButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        name = "MASTER";

        texturePosX = 0;
        texturePosY = 90 >> 1;
        label.setY(460 >> 1);
        label.setWidth(82 >> 1);//81
    }
}
