package com.cifrazia.vision.core.ui.gui.page;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.WarehouseData;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItemHolder;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.abstracts.ScrollableScreen;
import com.cifrazia.vision.core.network.packets.server.ServerWarehouseRetrievePacket;
import com.cifrazia.vision.core.ui.buttons.GetItemsButton;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.ItemsDrawer;
import com.cifrazia.vision.core.ui.util.draw.ScissoredDraw;
import com.cifrazia.vision.core.ui.util.render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class Warehouse extends Screen {

    private final WarehouseItems items;
    private final AuthorizedClient client;

    public Warehouse(Minecraft mc, int screenStartX, int screenStartY) {
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        client = (AuthorizedClient) Vision.getInstance().getAuthorization();

        items = new WarehouseItems(mc, client, screenStartX, screenStartY + (100 >> 1));
    }

    public void updateItems (){
        items.update();
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
    private final WarehouseData data;
    private final Render render;
    private List<WarehouseItemHolder> itemsHolder = new ArrayList<>();
    private List<GetItemsButton> getItemsButtons;
    private ItemsDrawer itemsDrawer;

    public WarehouseItems(Minecraft mc, AuthorizedClient client, int screenStartX, int screenStartY) {
        super(20 >> 1, 6 >> 1);
        this.mc = mc;
        render = new Render(mc);
        render.setScale(48.0F);
        data = client.getWarehouseData();
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;

        itemsDrawer = new ItemsDrawer(frameSize, gapOfItems, gapOfItems);
    }

    protected void update(){

        CompletableFuture.supplyAsync(data::getItems)
                .thenAccept((data) ->{
                    itemsHolder = data;
                    itemsDrawer.setItemCount(itemsHolder.size());
                    getItemsButtons = new ArrayList<>(itemsHolder.size());
                    scrollBar.update();

                    for (WarehouseItemHolder item: itemsHolder){
                        GetItemsButton button = new GetItemsButton(this, 0, 0, item);
                        button.setEvent(()-> {
                            Vision.getInstance().getNetwork().sendToServer(new ServerWarehouseRetrievePacket(new int[]{item.getWarehouseItem().getStack_id()}));

                            this.data.forceUpdate();
                            update();
                        });
                        getItemsButtons.add(button);
                    }
                });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        itemsDrawer.draw((x, y, id) -> frame(x, y, id, mouseX, mouseY, partialTicks));

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
        GetItemsButton button = getItemsButtons.get(id);
        button.updateCords(x + (20 >> 1), y + (170 >> 1));//offset of frame
        button.drawButton(mc, mouseX, mouseY, partialTicks);
        GlStateManager.disableBlend();

        ItemStack item = itemsHolder.get(id).getItemStack();
        render.renderEffectsItem(item, x + (106 >> 1), y + (84 >> 1));
    }

    @Override
    public void setResolution(int width, int height) {
        screenEndX = width - screenStartX;
        screenEndY = height - (26 >> 1);

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;


        scrollBar.setResolution(screenHeight, screenHeight, 0);
        scrollBar.startPositions(screenEndX - scrollBar.getBarWidth(), screenStartY);

        itemsDrawer.setScrollBar(scrollBar);
        itemsDrawer.setScissoredDraw(new ScissoredDraw(mc, screenStartX, screenStartY, screenWidth, screenHeight));
        itemsDrawer.setResolution(screenStartX, screenStartY, screenWidth);
    }
}
