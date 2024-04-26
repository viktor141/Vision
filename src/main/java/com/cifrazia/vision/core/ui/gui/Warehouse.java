package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.abstracts.ScrollableScreen;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.ItemsDrawer;
import com.cifrazia.vision.core.ui.util.draw.ScissoredDraw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class Warehouse extends Screen {

    private final WarehouseItems items;

    public Warehouse(Minecraft mc, int screenStartX, int screenStartY) {
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;

        items = new WarehouseItems(mc, screenStartX, screenStartY + (100 >> 1));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        items.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void setResolution(int width, int height) {
        items.setResolution(width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        items.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        items.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        items.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        items.handleMouseInput();
    }
}

class WarehouseItems extends ScrollableScreen {

    private final int gapOfItems = 10 >> 1;
    private final int frameSize = 242 >> 1;
    private int itemCount = 156;
    private ScissoredDraw scissoredDraw;
    private ItemsDrawer itemsDrawer;

    public WarehouseItems(Minecraft mc, int screenStartX, int screenStartY) {
        super(20 >> 1, 6 >> 1);
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        itemsDrawer.draw((x, y, id) -> frame(x, y, id, mouseX, mouseY, partialTicks));

        /*int itemsScreenWidth = screenWidth - (scrollBar.getBarWidth() + (gap << 1) + gapOfItems);

        int columns = itemsScreenWidth / (frameSize + gap);
        int rows = (itemCount / columns) + ((itemCount % columns) > 0 ? 1 : 0);

        scrollBar.setContentHeight((rows * (frameSize + gap)) - (gap >> 1));

        scissoredDraw.draw(() -> {
            for (int i = 0; i < count; i++) {
                frame(
                        screenStartX + gapOfItems + (j * (frameSize + gap)),
                        screenStartY + (i * (frameSize + gap)) + scrollBar.getScrollOffset(),
                        0,
                        mouseX, mouseY, partialTicks);
            }
        });*/

    }

    private void frame(int x, int y, int id, int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(Vision.NAVIGATION_SHOP_KIT);
        GlStateManager.color(Color.WHITE_100.getRf(), Color.WHITE_100.getGf(), Color.WHITE_100.getBf(), Color.WHITE_100.getAf());
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x, y,
                270 >> 1, 0,
                242 >> 1, 242 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();
    }

    @Override
    public void setResolution(int width, int height) {
        screenEndX = width - screenStartX;
        screenEndY = height - (26 >> 1);

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;


        scrollBar.setResolution(screenHeight, screenHeight, 0);
        scrollBar.startPositions(screenEndX - scrollBar.getBarWidth(), screenStartY);
        scissoredDraw = new ScissoredDraw(mc, screenStartX, screenStartY, screenWidth, screenHeight);
        itemsDrawer = new ItemsDrawer(scrollBar, scissoredDraw, frameSize, gapOfItems, gapOfItems, itemCount);


        itemsDrawer.setResolution(screenStartX, screenStartY, screenWidth);
    }
}
