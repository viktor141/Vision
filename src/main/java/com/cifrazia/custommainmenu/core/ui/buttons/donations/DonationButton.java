package com.cifrazia.custommainmenu.core.ui.buttons.donations;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;
import com.cifrazia.custommainmenu.core.ui.buttons.base.FadingButton;
import com.cifrazia.custommainmenu.core.ui.util.draw.Draw;
import com.cifrazia.custommainmenu.core.ui.util.texture.TextureData;
import com.cifrazia.custommainmenu.core.ui.util.texture.TextureSizeData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class DonationButton extends FadingButton {
    protected TextureSizeData sizeData;
    protected TextureData background;
    protected TextureData label;
    protected int backgroundShift;
    protected int labelGapX;
    protected int labelGapY;
    protected String name;

    public DonationButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y, 198 >> 1, 44 >> 1, "");

        buttonTextureKit = CustomMainMenu.DONATION_KIT;

        sizeData = new TextureSizeData(sizeOfTextureKit);

        background = new TextureData(sizeData, 0, 294 >> 1, 206 >> 1, 52 >> 1);
        backgroundShift = 4 >> 1;

        label = new TextureData(sizeData, 0, 0, 0, 20 >> 1);
        labelGapX = 38 >> 1;
        labelGapY = 12 >> 1;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        GlStateManager.enableBlend();
        Draw.drawModalSquareWithCustomSizedTexture(
                x - backgroundShift, y - backgroundShift,
                background.getX(), background.getY(),
                background.getWidth(), background.getHeight(),
                background.getSize());
        GlStateManager.disableBlend();

        super.drawButton(mc, mouseX, mouseY, partialTicks);

        GlStateManager.enableBlend();
        Draw.drawModalSquareWithCustomSizedTexture(
                x + labelGapX, y + labelGapY,
                label.getX(), label.getY(),
                label.getWidth(), label.getHeight(),
                label.getSize());
        GlStateManager.disableBlend();
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);

        sizeData.setScale(scale);

        background.setScale(scale);
        backgroundShift = (int) (backgroundShift * scale);

        label.setScale(scale);
        labelGapX = (int) (labelGapX * scale);
        labelGapY = (int) (labelGapY * scale);
    }

    @Override
    protected void hoverStateRender() {

    }
}
