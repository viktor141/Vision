package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;
import com.cifrazia.vision.core.ui.buttons.base.ActiveTransparentButton;

import java.util.List;

public class CatalogButton extends ActiveTransparentButton {

    public CatalogButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y, String buttonText) {
        super(parentGUI, buttons, x, y, 164 >> 1, 36 >> 1, buttonText);

        buttonTextureKit = Vision.NAVIGATION_SHOP_KIT;

        texturePosX = 0;
        texturePosY = 418 >> 1;
    }

}
