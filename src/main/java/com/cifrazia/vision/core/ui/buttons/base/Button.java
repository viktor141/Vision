package com.cifrazia.vision.core.ui.buttons.base;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public abstract class Button extends GuiButton {

    protected int sizeOfTextureKit = Vision.SIZE_OF_TEXTURE_KIT;
    private final Gui parentGUI;
    protected int texturePosX;
    protected int texturePosY;
    protected int color;
    protected float scale;
    protected Color renderColor;
    protected Runnable runnable;
    protected ResourceLocation buttonTextureKit;


    public Button(Gui parentGUI, int x, int y, int widthIn, int heightIn, String buttonText, int color) {
        super(0, x, y, widthIn, heightIn, buttonText);
        this.parentGUI = parentGUI;
        this.color = color;

        buttonTextureKit = Vision.SERVER_KIT;
        renderColor = Color.WHITE_100;
    }


    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (!this.visible) return;
        GlStateManager.color(renderColor.getRf(), renderColor.getGf(), renderColor.getBf(), renderColor.getAf());
        GlStateManager.enableBlend();//Need for fix black frame around first rendered button(I think it's a bug)

        mc.getTextureManager().bindTexture(buttonTextureKit);

        setHover(mouseX, mouseY);

        hoverStateRender();

        this.mouseDragged(mc, mouseX, mouseY);
        GlStateManager.disableBlend();
    }

    public void setHover(int mouseX, int mouseY) {
        hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

        if (hovered) parentGUI.setLastHoveredButton(this);
    }

    protected abstract void hoverStateRender();


    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return super.mousePressed(mc, mouseX, mouseY);
    }

    @Override
    public void playPressSound(SoundHandler soundHandlerIn) {
        super.playPressSound(soundHandlerIn);
    }

    @Override
    public boolean isMouseOver() {
        return this.hovered && this.enabled;
    }

    public void onClick() {
        if (runnable == null) return;

        runnable.run();
    }

    public void updateCords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Button setEvent(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public void setScale(float scale) {
        this.scale = scale;

        width = (int) (width * scale);
        height = (int) (height * scale);
        texturePosX = (int) (texturePosX * scale);
        texturePosY = (int) (texturePosY * scale);
        sizeOfTextureKit = (int) (sizeOfTextureKit * scale);
    }
}
