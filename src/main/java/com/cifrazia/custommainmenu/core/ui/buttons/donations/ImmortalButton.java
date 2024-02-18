package com.cifrazia.custommainmenu.core.ui.buttons.donations;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;

import java.util.List;

public class ImmortalButton extends DonationButton {
    public ImmortalButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y);

        name = "IMMORTAL";

        texturePosX = 204 >> 1;
        texturePosY = 138 >> 1;
        label.setY(392 >> 1);
        label.setWidth(108 >> 1);//107
    }
}
