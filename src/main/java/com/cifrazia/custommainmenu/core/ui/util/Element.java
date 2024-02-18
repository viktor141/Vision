package com.cifrazia.custommainmenu.core.ui.util;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.ui.util.draw.Draw;
import com.cifrazia.custommainmenu.core.ui.util.texture.TextureData;
import com.cifrazia.custommainmenu.core.ui.util.texture.TextureSizeData;
import net.minecraft.client.Minecraft;

public class Element {
    private final Minecraft mc;
    private String text;
    private final TextureSizeData size = new TextureSizeData(CustomMainMenu.SIZE_OF_TEXTURE_KIT);
    private final TextureData box;
    private int x;
    private int y;

    public Element(Minecraft mc, String text) {
        this.mc = mc;
        this.text = text;

        box = new TextureData(size, 210 >> 1, 0, 48 >> 1, 48 >> 1);
    }

    public void drawElement(int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(CustomMainMenu.DONATION_KIT);
        Draw.drawModalSquareWithCustomSizedTexture(
                x, y,
                box.getX(), box.getY(),
                box.getWidth(), box.getHeight(),
                box.getSize());

        mc.fontRenderer.drawString(text, x + (58 >> 1), y + (8 >> 1), -1);
    }

    public void setUp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSize (){
        return box.getWidth();
    }
}
