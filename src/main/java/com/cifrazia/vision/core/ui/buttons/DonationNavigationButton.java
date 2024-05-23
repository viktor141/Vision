package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;

import java.util.Collection;

public class DonationNavigationButton extends NavigationBarButton {


    public DonationNavigationButton(Gui parentGUI, Collection<? extends ActiveButton> buttons, String buttonText) {
        super(parentGUI, buttons, buttonText);

        texturePressedPosY = 416 >> 1;
    }
}
