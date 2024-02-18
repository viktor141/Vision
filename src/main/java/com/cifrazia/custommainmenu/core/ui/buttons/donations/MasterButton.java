package com.cifrazia.custommainmenu.core.ui.buttons.donations;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;
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
