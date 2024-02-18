package com.cifrazia.custommainmenu.core.ui.buttons.base;

import com.cifrazia.custommainmenu.core.abstracts.Gui;

public abstract class StaticButton extends RectButton{

    public StaticButton(Gui parentGUI, int x, int y, int widthIn, int heightIn, String buttonText, int color) {
        super(parentGUI, x, y, widthIn, heightIn, buttonText, color);
    }

    @Override
    protected void hoverStateRender() {
        drawModalRectWithCustomSizedTexture(
                borderX, borderY,
                uHovered, v,
                borderTextureWidth, borderTextureHeight,
                sizeOfTextureKit, sizeOfTextureKit);
    }
}
