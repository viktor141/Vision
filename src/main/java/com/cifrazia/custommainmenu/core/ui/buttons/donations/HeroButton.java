package com.cifrazia.custommainmenu.core.ui.buttons.donations;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;

import java.util.List;

public class HeroButton extends DonationButton {
    public HeroButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        name = "HERO";

        texturePosX = 202 >> 1;
        texturePosY = 90 >> 1;
        label.setY(440 >> 1);
        label.setWidth(54 >> 1);
    }
}
