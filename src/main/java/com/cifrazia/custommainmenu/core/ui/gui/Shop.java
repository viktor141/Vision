package com.cifrazia.custommainmenu.core.ui.gui;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Screen;
import com.cifrazia.custommainmenu.core.abstracts.ScrollableScreen;
import com.cifrazia.custommainmenu.core.ui.buttons.BuyItemsButton;
import com.cifrazia.custommainmenu.core.ui.buttons.CatalogButton;
import com.cifrazia.custommainmenu.core.ui.buttons.CategoryButton;
import com.cifrazia.custommainmenu.core.ui.buttons.base.Button;
import com.cifrazia.custommainmenu.core.ui.util.draw.ScissoredDraw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.cifrazia.custommainmenu.CustomMainMenu.SIZE_OF_TEXTURE_KIT;

public class Shop extends Screen {

    private final int gapX = 104 >> 1;
    private final int gapY = 56 >> 1;
    private final List<CatalogButton> catalogButtons = new ArrayList<>();

    private final ShopItems items;
    private final Bank bank;

    public Shop(Minecraft mc, int screenStartX, int screenStartY) {
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        items = new ShopItems(mc, screenStartX + gapX, screenStartY + (94 >> 1), gapX);
        bank = new Bank(mc, screenStartX + gapX, screenStartY + (94 >> 1), gapX);

        addCatalogButton(new CatalogButton(this, catalogButtons, 0, 0, "Items")).setEvent(() -> this.setCurrentGui(items));
        addCatalogButton(new CatalogButton(this, catalogButtons, 0, 0, "Bank")).setEvent(() -> this.setCurrentGui(bank));

        catalogButtons.get(0).onClick();
    }

    @Override
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;

        screenWidth = width - screenStartX;
        screenHeight = height - screenStartY;

        screenEndX = width;
        screenEndY = height;

        for (int i = 0; i < catalogButtons.size(); i++) {
            Button button = catalogButtons.get(i);

            button.updateCords(screenStartX + gapX + (i * button.width), screenStartY + gapY - button.height);
        }

        if (currentGui != null) currentGui.setResolution(width, height);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawGradientRectHorizontal(
                screenStartX + gapX, screenStartY + gapY,
                screenEndX - gapX, screenStartY + gapY + (8 >> 1),
                -5118661, -15093240);
    }


    private CatalogButton addCatalogButton(CatalogButton catalogButton) {
        super.addButton(catalogButton);
        catalogButtons.add(catalogButton);
        return catalogButton;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (currentGui != null) currentGui.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        if (currentGui != null) currentGui.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        if (currentGui != null) currentGui.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        if (currentGui != null) currentGui.handleMouseInput();
    }
}

class ShopItems extends ScrollableScreen {


    private final List<CategoryButton> categoryButtons = new ArrayList<>();
    private final int items = 80;
    private final List<BuyItemsButton> buyItemsButtons = new ArrayList<>(items);
    private final int gapX;
    private final int gap = 10 >> 1;
    private final int frameSize = 192 >> 1;
    private final int gapOfItems = 238 >> 1;
    private ScissoredDraw scissoredDraw;


    public ShopItems(Minecraft mc, int screenStartX, int screenStartY, int gapX) {
        super(16 >> 1, 4 >> 1);
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        this.gapX = gapX;

        for (int i = 0; i < items; i++) {
            BuyItemsButton button = new BuyItemsButton(this, 0, 0, "", -1);
            button.setEvent(()-> {
                mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            });
            buyItemsButtons.add(button);
        }

        addCategoryButton(new CategoryButton(this, categoryButtons, 0, 0, "All items"));
        addCategoryButton(new CategoryButton(this, categoryButtons, 0, 0, "Armour"));

        categoryButtons.get(0).onClick();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (CategoryButton categoryButton : this.categoryButtons) {
            categoryButton.drawButton(this.mc, mouseX, mouseY, partialTicks);
        }

        int itemsScreenWidth = screenWidth - (scrollBar.getBarWidth() + (gap << 1) + gapOfItems);

        int columns = itemsScreenWidth / (frameSize + gap);
        int rows = (items / columns) + ((items % columns) > 0 ? 1 : 0);

        scrollBar.setContentHeight((rows * (frameSize + gap)) - (gap >> 1));

        scissoredDraw.draw(() -> {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < Math.min(columns, items - (columns * i)); j++) {
                    drawFrame(
                            screenStartX + gapOfItems + (j * (frameSize + gap)),
                            screenStartY + (i * (frameSize + gap)) + scrollBar.getScrollOffset(),
                            columns * i + j,
                            mouseX, mouseY, partialTicks);
                }
            }
        });
    }


    private void drawFrame(int x, int y, int id, int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(CustomMainMenu.NAVIGATION_SHOP_KIT);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x, y,
                0, 0,
                frameSize, frameSize,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        BuyItemsButton button = buyItemsButtons.get(id);
        button.updateCords(x + (14 >> 1), y + (140>>1));
        button.drawButton(mc, mouseX, mouseY, partialTicks);


        GlStateManager.disableBlend();

    }

    @Override
    public void setResolution(int width, int height) {
        screenEndX = width - gapX;
        screenEndY = height;

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;

        scrollBar.startPositions(screenEndX - scrollBar.getBarWidth(), screenStartY);

        int underGap = 20 >> 1;
        scrollBar.setResolution(screenHeight - underGap, screenHeight - underGap, 0);

        scissoredDraw = new ScissoredDraw(mc, screenStartX, screenStartY, screenWidth, screenHeight);


        for (int i = 0; i < categoryButtons.size(); i++) {
            Button button = categoryButtons.get(i);

            button.updateCords(screenStartX, screenStartY + (i * button.height));
        }
    }

    private void addCategoryButton(CategoryButton categoryButton) {
        super.addButton(categoryButton);
        categoryButtons.add(categoryButton);
    }
}

class Bank extends Screen {

    private final int frameSize = 242 >> 1;
    private final int gapX;
    private final int gap = 10 >> 1;

    public Bank(Minecraft mc, int screenStartX, int screenStartY, int gapX) {
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        this.gapX = gapX;
    }

    @Override
    public void setResolution(int width, int height) {
        screenEndX = width - gapX;
        screenEndY = height;

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int count = 8;
        int startX = gapX + (screenWidth >> 1) - ((frameSize << 1) + gap + (gap >> 1));

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                int x = startX + (j * (frameSize + gap));
                int y = screenStartY + (i * (frameSize + gap));
                drawFrame(x, y);
            }
        }
    }

    private void drawFrame(int x, int y) {
        mc.getTextureManager().bindTexture(CustomMainMenu.NAVIGATION_SHOP_KIT);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x, y,
                270 >> 1, 0,
                frameSize, frameSize,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        GlStateManager.disableBlend();

    }
}
