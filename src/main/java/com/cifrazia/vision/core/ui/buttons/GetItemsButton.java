package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItemHolder;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.StaticButton;

public class GetItemsButton extends StaticButton {
    private final WarehouseItemHolder itemHolder;

    public GetItemsButton(Gui parentGUI, int x, int y, WarehouseItemHolder itemHolder) {
        super(parentGUI, x, y, 202 >> 1, 52 >> 1, "Get Item", -1);
        this.itemHolder = itemHolder;

        buttonTextureKit = Vision.STOCK_AND_BUNK_KIT;

        texturePosX = 0;
        texturePosY = 366 >> 1;

        uHovered = 0;
        v = 430 >> 1;
        borderTextureWidth = 210 >> 1;
        borderTextureHeight = 60 >> 1;
    }

    @Override
    protected void borderSet() {
        borderX = x - 1;
        borderY = y - 1;
    }
}
