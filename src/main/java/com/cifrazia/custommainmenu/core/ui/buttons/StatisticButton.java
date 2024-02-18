package com.cifrazia.custommainmenu.core.ui.buttons;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.SquareButton;

public class StatisticButton extends SquareButton {


    public StatisticButton(Gui parentGUI, int x, int y) {
        super(parentGUI, x, y, 0);

        texturePosX = 62 >> 1;
    }
}
