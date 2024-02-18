package com.cifrazia.custommainmenu.core.ui.util.draw;

import net.minecraft.client.gui.Gui;

public class Draw extends Gui {

    public static void drawModalSquareWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float size) {
        drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, size, size);
    }
}
