package com.cifrazia.custommainmenu.core.ui.buttons.links;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.SquareButton;

public class YouTubeLink extends SquareButton {

    public YouTubeLink(Gui parentGUI, int x, int y) {
        super(parentGUI, x, y, 0);

        texturePosX = 254 >> 1;
    }
}
