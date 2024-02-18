package com.cifrazia.custommainmenu.core.abstracts;

import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;
import com.cifrazia.custommainmenu.core.ui.buttons.base.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.io.IOException;

public abstract class Gui extends GuiScreen {

    protected Button lastHoveredButton;
    protected Screen currentGui = null;


    public void setLastHoveredButton(Button lastHoveredButton) {
        this.lastHoveredButton = lastHoveredButton;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (lastHoveredButton != null) {
            if (lastHoveredButton.isMouseOver()) {
                if (lastHoveredButton instanceof ActiveButton) {
                    if (!((ActiveButton) lastHoveredButton).isPressed()) lastHoveredButton.onClick();
                } else {
                    lastHoveredButton.onClick();
                }
                //lastHoveredButton.playPressSound(mc.getSoundHandler());
            }
        }

        if (currentGui != null) currentGui.mouseClicked(mouseX, mouseY, mouseButton);
    }


    protected <T extends Button> T addButton(T buttonIn) {
        buttonIn.id = buttonList.size();
        buttonList.add(buttonIn);
        return buttonIn;
    }

    public void setCurrentGui(Screen currentGui) {
        this.currentGui = currentGui;
        currentGui.setResolution(width, height);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (currentGui != null) currentGui.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        if (currentGui != null) currentGui.setResolution(width, height);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        if (currentGui != null) currentGui.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        if (currentGui != null) currentGui.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        if (currentGui != null) currentGui.handleMouseInput();
    }

    protected void drawGradientRectHorizontal(int left, int top, int right, int bottom, int startColor, int endColor) {
        float sa = (float) (startColor >> 24 & 255) / 255.0F;
        float sr = (float) (startColor >> 16 & 255) / 255.0F;
        float sg = (float) (startColor >> 8 & 255) / 255.0F;
        float sb = (float) (startColor & 255) / 255.0F;

        float ea = (float) (endColor >> 24 & 255) / 255.0F;
        float er = (float) (endColor >> 16 & 255) / 255.0F;
        float eg = (float) (endColor >> 8 & 255) / 255.0F;
        float eb = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);

        bufferbuilder.pos(left, top, this.zLevel).color(sr, sg, sb, sa).endVertex();
        bufferbuilder.pos(left, bottom, this.zLevel).color(sr, sg, sb, sa).endVertex();


        bufferbuilder.pos(right, bottom, this.zLevel).color(er, eg, eb, ea).endVertex();
        bufferbuilder.pos(right, top, this.zLevel).color(er, eg, eb, ea).endVertex();

        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
}
