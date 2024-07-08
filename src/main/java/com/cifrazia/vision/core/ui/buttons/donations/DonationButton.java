package com.cifrazia.vision.core.ui.buttons.donations;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;
import com.cifrazia.vision.core.ui.buttons.base.FadingButton;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import com.cifrazia.vision.core.ui.util.texture.TextureData;
import com.cifrazia.vision.core.ui.util.texture.TextureSizeData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class DonationButton extends FadingButton {
    protected Color color;
    protected TextureSizeData sizeData;
    protected TextureData background;
    protected TextureData label;
    protected TextureData icon;
    protected int backgroundShift;
    protected int labelGapX;
    protected int labelGapY;
    protected int iconGapX;
    protected int iconGapY;
    protected String name;

    public DonationButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y) {
        super(parentGUI, buttons, x, y, 188 >> 1, 48 >> 1, "");

        buttonTextureKit = Vision.DONATION_KIT;

        sizeData = new TextureSizeData(sizeOfTextureKit);

        background = new TextureData(sizeData, 0, 300 >> 1, 196 >> 1, 56 >> 1);
        backgroundShift = 4 >> 1;

        label = new TextureData(sizeData, 0, 0, 0, 20 >> 1);
        labelGapX = 42 >> 1;
        labelGapY = 14 >> 1;

        icon = new TextureData(sizeData, 0, 364 >> 1, 24 >> 1, 24 >> 1);
        iconGapX = labelGapX - (4 >> 1) - icon.getWidth();
        iconGapY = labelGapY - 1;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(buttonTextureKit);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0f, 1.0f,1.0f,1.0f);
        Draw.drawModalSquareWithCustomSizedTexture(
                x - backgroundShift, y - backgroundShift,
                background.getX(), background.getY(),
                background.getWidth(), background.getHeight(),
                background.getSize());
        GlStateManager.disableBlend();

        super.drawButton(mc, mouseX, mouseY, partialTicks);

        GlStateManager.enableBlend();
        Draw.drawModalSquareWithCustomSizedTexture(
                x + iconGapX, y + iconGapY,
                icon.getX(), icon.getY(),
                icon.getWidth(), icon.getHeight(),
                icon.getSize());

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

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
