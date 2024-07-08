package com.cifrazia.vision.core.ui.util.panel;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.privilege.KitHolder;
import com.cifrazia.vision.core.ui.gui.page.Donation;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import com.cifrazia.vision.core.ui.util.render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

import java.util.List;

public class KitPanel implements Panel {

    private final Minecraft mc;
    private final List<ItemStack> items;
    private final Render render;
    private final Donation donation;
    private final KitHolder kitHolder;
    private final PanelFrame panelFrame;
    private final String description;
    private int x;
    private int y;

    public KitPanel(Minecraft mc, KitHolder kitHolder, Donation donation) {
        this.mc = mc;
        this.items = kitHolder.getKitItems();
        this.kitHolder = kitHolder;
        this.donation = donation;

        render = new Render(mc);
        //render.setScale(24.0f);
        panelFrame = new PanelFrame(Color.SIX_PRESENT, Color.WHITE_20);
        int unit;
        String unitName;
        String unitsName;


        if ((unit = (int) (kitHolder.getKit().getCool_down() / 60 / 60 / 24)) != 0) {
            unitsName = "days";
            unitName = "day";
        } else if ((unit = (int) (kitHolder.getKit().getCool_down() / 60 / 60)) != 0) {
            unitsName = "hours";
            unitName = "hour";
        } else {
            unit = (int) (kitHolder.getKit().getCool_down() / 60);
            unitsName = "minutes";
            unitName = "minute";
        }

        description = "Available every " + (unit == 1 ? unitName : unit + " " + unitsName);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        panelFrame.draw(x, y);

        int count = (int) (panelFrame.getWidth() / (render.getScale() + (10 >> 1)));
        int gap = (int) (panelFrame.getWidth() - (((render.getScale() + (10 >> 1)) * count) - (10 >> 1)));
        int gapY = (panelFrame.getHeight() >> 1) + (panelFrame.getHeight() >> 3);

        for (int i = 0; i < count - 1; i++) {
            int itemX = x + render.getRenderGap() + (gap >> 1) + (i * ((10 >> 1) + (int) render.getScale()));
            int itemY = y + gapY;
            ItemStack item = items.get(i);
            render.renderEffectsItem(item, itemX, itemY);

            if (render.isMouseOnItem(mouseX, mouseY, itemX, itemY, item))
                donation.setItemForTooltip(item);

        }

        mc.getTextureManager().bindTexture(Vision.DONATION_KIT);

        int counterX = (int) (x + (gap >> 1) + ((render.getScale() + (10 >> 1)) * (count - 1)));
        int counterY = y + gapY - render.getRenderGap();

        float scale = 1.4f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.scale(scale, scale, scale);
        Draw.drawModalRectWithCustomSizedTexture(
                (int) (counterX / scale), (int) (counterY / scale),
                0, 0,
                48 >> 1, 48 >> 1,
                Vision.SIZE_OF_TEXTURE_KIT, Vision.SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        float numScale = 1.5f;
        GlStateManager.pushMatrix();
        GlStateManager.scale(numScale, numScale, numScale);
        Draw.drawCenteredStringWidthShadow(
                mc.fontRenderer, "+" + items.size(),
                (int) ((counterX + ((48 >> 2) * scale)) / numScale),
                (int) (((counterY + ((48 >> 2) * scale)) - (mc.fontRenderer.FONT_HEIGHT >> 1)) / numScale),
                -1);
        GlStateManager.popMatrix();

        Draw.drawCenteredStringWidthShadow(
                mc.fontRenderer, kitHolder.getKit().getName(),
                x + (panelFrame.getWidth() >> 1), y + (panelFrame.getHeight() >> 3), -1);
        Draw.drawCenteredStringWidthShadow(
                mc.fontRenderer, description,
                x + (panelFrame.getWidth() >> 1), y + (panelFrame.getHeight() >> 2) + (panelFrame.getHeight() >> 5), Color.GRAY_COMMAND_DESCRIPTION.getFullColor());
    }

    @Override
    public void setUp(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        panelFrame.setUp(x, y, width);
        panelFrame.setHeight(height);
    }

    public boolean isMouseBounding(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + panelFrame.getWidth() && mouseY >= y && mouseY <= y + getHeight();
    }


    public KitHolder getKitHolder() {
        return kitHolder;
    }

    public int getHeight() {
        return panelFrame.getHeight();
    }

    public String getDescription() {
        return description;
    }
}
