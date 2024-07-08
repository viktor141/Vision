package com.cifrazia.vision.core.ui.gui.information;

import com.cifrazia.vision.connection.data.element.privilege.KitHolder;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.ScrollablePanelScreen;
import com.cifrazia.vision.core.ui.buttons.modal.ModalGrayButton;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.ItemsDrawer;
import com.cifrazia.vision.core.ui.util.draw.ScissoredDraw;
import com.cifrazia.vision.core.ui.util.panel.ConfirmationPanel;
import com.cifrazia.vision.core.ui.util.render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

import java.util.List;

public class KitItems extends ScrollablePanelScreen {

    private final ModalGrayButton cancel;
    private final List<ItemStack> items;
    private final Render render;
    private final int itemGap = 20 >> 1;
    private final int gapOfScreenY = 120 >> 1;
    private final String description;
    private final KitHolder kitHolder;
    private final ItemsDrawer itemsDrawer;
    private final int gapOfScreenX = 48 >> 1;
    private int rows;
    private int columns;
    private ItemStack tooltipItem = ItemStack.EMPTY;


    public KitItems(Minecraft mc, Gui parentGui, KitHolder kitHolder, String description) {
        super(new ConfirmationPanel(mc), 6, 2);
        this.mc = mc;
        this.kitHolder = kitHolder;
        this.items = kitHolder.getKitItems();
        this.parentGui = parentGui;
        this.description = description;

        render = new Render(mc);
        itemsDrawer = new ItemsDrawer((int) render.getScale(), itemGap, gapOfScreenX);

        cancel = addButton(new ModalGrayButton(this, 0, 0, "Cancel", -1));
        cancel.setEvent(() -> mc.displayGuiScreen(parentGui));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawCenteredString(
                mc.fontRenderer, kitHolder.getKit().getName(),
                screenStartX + (screenWidth >> 1), screenStartY + (34 >> 1),
                -1);

        drawCenteredString(
                mc.fontRenderer, description,
                screenStartX + (screenWidth >> 1), screenStartY + (60 >> 1),
                Color.GRAY_COMMAND_DESCRIPTION.getFullColor());

        GlStateManager.pushMatrix();
        GlStateManager.translate(1.0f, 1.0f, 100f);//fix that other items rendered on parent screen was collision with this items
        itemsDrawer.draw((x, y, id) -> drawItemStack(x, y + render.getRenderGap(), id, mouseX, mouseY));
        GlStateManager.popMatrix();


        /*for (int i = 0; i < rows; i++) {
            for (int j = 0; j < Math.min(columns, items.size() - (columns * i)); j++) {
                int x = screenStartX + gapOfScreenX + render.getRenderGap() + (itemGap >> 1) + (j * (itemGap + render.getRenderGap() + (int) render.getScale()));
                int y = screenStartY + gapOfScreenY + render.getRenderGap() + (itemGap >> 1) + (i * (itemGap + render.getRenderGap() + (int) render.getScale()));

                drawItemStack(x, y + scrollBar.getScrollOffset(), columns * i + j, mouseX, mouseY);
            }
        }*/

        cancel.updateCords(cancel.x, screenEndY - ((cancel.height << 1) / 3));

        drawItemTooltip(mouseX, mouseY);
    }

    private void drawItemStack(int x, int y, int id, int mouseX, int mouseY) {
        ItemStack item = items.get(id);
        render.renderEffectsItem(item, x, y);

        if (render.isMouseOnItem(mouseX, mouseY, x, y, item))
            tooltipItem = item;
    }

    protected void drawItemTooltip(int mouseX, int mouseY) {
        super.drawItemTooltip(mouseX, mouseY, tooltipItem);
        tooltipItem = ItemStack.EMPTY;
    }

    @Override
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;

        screenWidth = (int) (width * 0.359375f);
        int screenMaxHeight = (int) (height * 0.84356f);
        int screenCalculatedHeight;

        columns = (int) (screenWidth / (render.getScale() + itemGap)) - 1;

        //gapOfScreenX = (screenWidth - (columns * ((int) render.getScale() + render.getRenderGap() + itemGap))) >> 1;

        rows = items.size() / columns + Math.min(1, items.size() % columns);

        screenCalculatedHeight = (int) ((render.getScale() + itemGap) * rows) + gapOfScreenY;

        screenHeight = Math.min(screenMaxHeight, screenCalculatedHeight) + (56 >> 1);

        super.setResolution(width, height);

        int contentStartY = screenStartY + gapOfScreenY;
        int activeHeightOfScreen = screenHeight - gapOfScreenY - (56 >> 1);

        scrollBar.startPositions(screenEndX - (32 >> 1), contentStartY);
        scrollBar.setResolution(activeHeightOfScreen, activeHeightOfScreen, screenHeight);

        itemsDrawer.setItemCount(items.size());
        itemsDrawer.setResolution(screenStartX, contentStartY, screenWidth);
        itemsDrawer.setScissoredDraw(new ScissoredDraw(mc, screenStartX, contentStartY, screenWidth, activeHeightOfScreen));
        itemsDrawer.setScrollBar(scrollBar);

        cancel.updateCords(screenStartX + (screenWidth >> 1) - (cancel.width >> 1), screenEndY - ((cancel.height << 1) / 3));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (mouseX < screenStartX || mouseX > screenEndX + (16 >> 1) || mouseY < screenStartY || mouseY > screenEndY) {
            mc.displayGuiScreen(parentGui);
        }
    }

}
