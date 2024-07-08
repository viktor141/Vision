package com.cifrazia.vision.core.ui.gui.page;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.ShopData;
import com.cifrazia.vision.connection.data.element.shop.ShopCategory;
import com.cifrazia.vision.connection.data.element.shop.ShopTradeOffer;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.abstracts.ScrollableScreen;
import com.cifrazia.vision.core.ui.buttons.BuyItemsButton;
import com.cifrazia.vision.core.ui.buttons.CatalogButton;
import com.cifrazia.vision.core.ui.buttons.CategoryButton;
import com.cifrazia.vision.core.ui.buttons.base.Button;
import com.cifrazia.vision.core.ui.gui.confirmation.ShopConfirmation;
import com.cifrazia.vision.core.ui.text_fields.SearchField;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import com.cifrazia.vision.core.ui.util.draw.ItemsDrawer;
import com.cifrazia.vision.core.ui.util.draw.ScissoredDraw;
import com.cifrazia.vision.core.ui.util.render.Render;
import com.cifrazia.vision.menu.ModalWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class Shop extends Screen {

    private final int gapX = 104 >> 1;
    private final int gapY = 56 >> 1;
    private final List<CatalogButton> catalogButtons = new ArrayList<>();
    private final ShopItems items;
    private final Bank bank;
    private final AuthorizedClient client;
    private final SearchField searchField;

    public Shop(Minecraft mc, Gui parentGui, int screenStartX, int screenStartY) {
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        client = (AuthorizedClient) Vision.getInstance().getAuthorization();
        searchField = new SearchField(0, mc.fontRenderer, 0, 0);

        items = new ShopItems(mc, parentGui, screenStartX + gapX, screenStartY + (94 >> 1), gapX, client, searchField);
        bank = new Bank(mc, screenStartX + gapX, screenStartY + (94 >> 1), gapX);

        addCatalogButton(new CatalogButton(this, catalogButtons, 0, 0, "Items")).setEvent(() -> {
            this.setCurrentGui(items);
            items.load();
        });
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

        searchField.x = screenEndX - gapX - searchField.width;
        searchField.y = screenStartY + gapY - searchField.height;

        for (int i = 0; i < catalogButtons.size(); i++) {
            Button button = catalogButtons.get(i);

            button.updateCords(screenStartX + gapX + (i * button.width), screenStartY + gapY - button.height);
        }

        if (currentGui != null) currentGui.setResolution(width, height);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        Draw.drawGradientRectangle(screenStartX + gapX, screenStartY + gapY, screenEndX - gapX, screenStartY + gapY + (8 >> 1), new Color(177, 229, 59, 255), new Color(25, 178, 8, 255), true);

        searchField.drawTextBox();
    }


    private CatalogButton addCatalogButton(CatalogButton catalogButton) {
        super.addButton(catalogButton);
        catalogButtons.add(catalogButton);
        return catalogButton;
    }

    public void setBankScreen() {
        catalogButtons.get(1).onClick();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (currentGui != null) currentGui.mouseClicked(mouseX, mouseY, mouseButton);

        searchField.mouseClicked(mouseX, mouseY, mouseButton);
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

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        searchField.textboxKeyTyped(typedChar, keyCode);
        if (currentGui != null) currentGui.keyTyped(typedChar, keyCode);
    }
}

class ShopItems extends ScrollableScreen {
    private final CategoryButtons categoryButtons;
    private final Gui parent;
    private final SearchField searchField;
    private List<ShopTradeOffer> items = new ArrayList<>();
    private List<BuyItemsButton> buyItemsButtons = new ArrayList<>();
    private final int gapX;
    private final int gapOfItems = 10 >> 1;
    private final int frameSize = 192 >> 1;
    private final int gapOfScreen = 238 >> 1;
    private final Render render;
    private ItemsDrawer itemsDrawer;
    private final AuthorizedClient client;
    private ItemStack tooltipItem = ItemStack.EMPTY;


    public ShopItems(Minecraft mc, Gui parent, int screenStartX, int screenStartY, int gapX, AuthorizedClient client, SearchField searchField) {
        super(16 >> 1, 4 >> 1);
        this.mc = mc;
        this.parent = parent;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        this.gapX = gapX;
        this.client = client;
        this.searchField = searchField;
        render = new Render(mc);
        itemsDrawer = new ItemsDrawer(frameSize, gapOfItems, gapOfScreen);

        categoryButtons = new CategoryButtons(mc, this, client.getShopData(), screenStartX, screenStartY, screenStartX + (220 >> 1), gapX);
    }

    public void load() {
        categoryButtons.categoryUpdate();
    }

    protected void shopListUpdate() {
        CompletableFuture.supplyAsync(() -> client.getShopData().getItems(), Vision.getInstance().getExecutorService())
                .thenAccept(data -> {
                    synchronized (client.getShopData()) {
                        items = data;
                        buyItemsButtons = new ArrayList<>(items.size());
                        itemsDrawer.setItemCount(items.size());
                        scrollBar.update();

                        for (ShopTradeOffer offer : items) {
                            BuyItemsButton button = new BuyItemsButton(this, 0, 0, offer);
                            button.setEvent(() -> {
                                mc.displayGuiScreen(new ModalWindow(new ShopConfirmation(mc, parent, offer)));
                            });
                            buyItemsButtons.add(button);
                        }
                    }
                });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        categoryButtons.drawScreen(mouseX, mouseY, partialTicks);

        synchronized (client.getShopData()) {
            itemsDrawer.draw((x, y, id) -> drawFrame(x, y, id, mouseX, mouseY, partialTicks));
        }

        drawItemTooltip(mouseX, mouseY);
    }


    protected void drawItemTooltip(int mouseX, int mouseY) {
        super.drawItemTooltip(mouseX, mouseY, tooltipItem);
        tooltipItem = ItemStack.EMPTY;
    }

    private void drawFrame(int x, int y, int id, int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(Vision.NAVIGATION_SHOP_KIT);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, frameSize, frameSize, SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        BuyItemsButton button = buyItemsButtons.get(id);
        button.updateCords(x + (14 >> 1), y + (140 >> 1));//offset of frame
        button.drawButton(mc, mouseX, mouseY, partialTicks);
        GlStateManager.disableBlend();

        ItemStack item = items.get(id).getItemStack();
        int itemX = x + (80 >> 1);
        int itemY = y + (60 >> 1);

        render.renderEffectsItem(item, itemX, itemY);

        drawDescription(x, y, 0.75125f, item.getDisplayName());

        if (render.isMouseOnItem(mouseX, mouseY, itemX, itemY, item))
            tooltipItem = item;
    }

    private void drawDescription(int x, int y, float scale, String text) {
        int gap = 2;
        int addedY = 14;

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);

        int allowedLength = frameSize - 12;
        List<String> list = mc.fontRenderer.listFormattedStringToWidth(text, (int) (allowedLength / scale));
        int fixY = ((mc.fontRenderer.FONT_HEIGHT + gap) * (list.size() - 1)) >> 1;

        if (list.size() >= 3) {
            List<String> replace = new ArrayList<>(2);
            replace.add(list.get(0));
            replace.add(list.get(1) + "...");

            list = replace;
        }

        for (int i = 0; i < list.size(); i++) {
            drawCenteredString(
                    mc.fontRenderer,
                    list.get(i),
                    (int) ((x + (frameSize >> 1)) / scale),
                    ((int) (y / scale)) + addedY - fixY + ((mc.fontRenderer.FONT_HEIGHT + gap) * i),
                    -1);
        }

        GlStateManager.popMatrix();
    }

    @Override
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
        screenEndX = width - gapX;
        screenEndY = height;

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;

        categoryButtons.setResolution(width, height);

        scrollBar.startPositions(screenEndX - scrollBar.getBarWidth(), screenStartY);

        int underGap = 20 >> 1;
        scrollBar.setResolution(screenHeight - underGap, screenHeight - underGap, 0);

        itemsDrawer.setScrollBar(scrollBar);
        itemsDrawer.setScissoredDraw(new ScissoredDraw(mc, screenStartX, screenStartY, screenWidth, screenHeight));
        itemsDrawer.setResolution(screenStartX, screenStartY, screenWidth);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        categoryButtons.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        categoryButtons.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        categoryButtons.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {//When you getWheel digit, it was set to zero and another handler didn't work
        if (Mouse.getEventX() / (mc.displayWidth / width) >= screenStartX + gapOfScreen) super.handleMouseInput();
        else categoryButtons.handleMouseInput();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (!searchField.isFocused()) return;

        if (searchField.getText().isEmpty()) {
            shopListUpdate();
        } else {
            CompletableFuture.supplyAsync(() -> client.getShopData().getItems(), Vision.getInstance().getExecutorService())
                    .thenAccept((data) -> {
                        synchronized (client.getShopData()) {
                            items = data.stream().filter(item ->
                                            item.getItemStack().getDisplayName().toLowerCase()
                                                    .contains(searchField.getText().toLowerCase()))
                                    .collect(Collectors.toList());

                            itemsDrawer.setItemCount(items.size());
                            buyItemsButtons = new ArrayList<>(items.size());
                            scrollBar.update();

                            for (ShopTradeOffer offer : items) {
                                BuyItemsButton button = new BuyItemsButton(this, 0, 0, offer);
                                button.setEvent(() -> {
                                    mc.displayGuiScreen(new ModalWindow(new ShopConfirmation(mc, parent, offer)));
                                });
                                buyItemsButtons.add(button);
                            }
                        }
                    });
        }

    }
}

class CategoryButtons extends ScrollableScreen {
    private final ShopItems shopItems;
    private final ShopData shopData;
    private final int gapX;
    private List<ShopCategory> categories;
    private List<CategoryButton> buttons = new ArrayList<>();
    private ItemsDrawer drawer;

    public CategoryButtons(Minecraft mc, ShopItems shopItems, ShopData shopData, int screenStartX, int screenStartY, int screenEndX, int gapX) {
        super(16 >> 1, 4 >> 1);
        this.mc = mc;
        this.shopItems = shopItems;
        this.shopData = shopData;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        this.screenEndX = screenEndX;
        this.gapX = gapX;
        drawer = new ItemsDrawer(182 >> 1, 40 >> 1, 6 >> 1, 0);

    }

    public void categoryUpdate() {
        CompletableFuture.supplyAsync(shopData::getCategories, Vision.getInstance().getExecutorService()).thenAccept(data -> {
            synchronized (shopData) {
                categories = data;
                buttons = new ArrayList<>(categories.size());
                drawer.setItemCount(categories.size());
                scrollBar.update();

                addCategoryButton(new CategoryButton(this, buttons, 0, 0, "All items", 0)).setEvent(() -> {
                    categorySetter(shopData.getAllCategory());
                });

                for (ShopCategory category : categories) {
                    addCategoryButton(new CategoryButton(this, buttons, 0, 0, category.getName(), category.getId())).setEvent(() -> {
                        categorySetter(category);
                    });
                }

                updateCordsOfButtons();
                emulateAllCategoriesClick();
            }
        });
    }

    private void categorySetter(ShopCategory category) {
        CompletableFuture.supplyAsync(() -> {
            shopData.setCategory(category);
            return null;
        }).thenAccept(data -> {
            shopItems.shopListUpdate();
        });

    }

    public void emulateAllCategoriesClick() {
        buttons.get(0).onClick();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        synchronized (shopData) {
            drawer.draw((x, y, id) -> drawCategoryButtons(x, y, id, mouseX, mouseY, partialTicks));
        }
    }

    private void drawCategoryButtons(int x, int y, int id, int mouseX, int mouseY, float partialTicks) {
        CategoryButton categoryButton = buttons.get(id);

        categoryButton.updateCords(x, y);
        categoryButton.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public void setResolution(int width, int height) {
        screenEndY = height;

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;

        scrollBar.startPositions(screenEndX - scrollBar.getBarWidth(), screenStartY);

        int underGap = 20 >> 1;
        scrollBar.setResolution(screenHeight - underGap, screenHeight - underGap, 0);

        drawer.setScrollBar(scrollBar);
        drawer.setScissoredDraw(new ScissoredDraw(mc, screenStartX - gapX, screenStartY, screenWidth + gapX, screenHeight));
        drawer.setResolution(screenStartX, screenStartY, screenWidth);

        updateCordsOfButtons();
    }

    private void updateCordsOfButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);

            button.updateCords(screenStartX, screenStartY + (i * button.height));
        }
    }

    private CategoryButton addCategoryButton(CategoryButton categoryButton) {
        super.addButton(categoryButton);
        buttons.add(categoryButton);
        return categoryButton;
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
        //int count = 8;
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
        mc.getTextureManager().bindTexture(Vision.NAVIGATION_SHOP_KIT);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(x, y, 270 >> 1, 0, frameSize, frameSize, SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        GlStateManager.disableBlend();

    }
}