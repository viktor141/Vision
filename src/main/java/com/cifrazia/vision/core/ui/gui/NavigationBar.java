package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.ui.buttons.DonationNavigationButton;
import com.cifrazia.vision.core.ui.buttons.NavigationBarButton;
import com.cifrazia.vision.core.ui.buttons.base.Button;
import com.cifrazia.vision.core.ui.gui.page.Donation;
import com.cifrazia.vision.core.ui.gui.page.Warehouse;
import com.cifrazia.vision.menu.GeneralBusinessMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.util.HashMap;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class NavigationBar extends Screen {

    private final HashMap<String, NavigationBarButton> navigationBarButtons = new HashMap<>();


    public NavigationBar(Minecraft mc, GeneralBusinessMenu menu, Screen shop, Donation donation, Warehouse warehouse) {
        this.mc = mc;

        addButton(new NavigationBarButton(this, navigationBarButtons.values(), "Shop")).setEvent(() -> {
            menu.setCurrentGui(shop);
        });
        addButton(new NavigationBarButton(this, navigationBarButtons.values(), "Case")).setEvent(() -> {
            navigationBarButtons.get("Shop").onClick();
        });
        addButton(new DonationNavigationButton(this, navigationBarButtons.values(), "Donation")).setEvent(() -> {
            menu.setCurrentGui(donation);
        });
        addButton(new NavigationBarButton(this, navigationBarButtons.values(), "Kit")).setEvent(() -> {
            navigationBarButtons.get("Shop").onClick();
        });
        addButton(new NavigationBarButton(this, navigationBarButtons.values(), "Warehouse")).setEvent(() -> {
            warehouse.updateItems();
            menu.setCurrentGui(warehouse);
        });
        addButton(new NavigationBarButton(this, navigationBarButtons.values(), "Pass")).setEvent(() -> {
            navigationBarButtons.get("Shop").onClick();
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        int gap = 8 >> 1;
        int width = 8 >> 1;

        drawRect(screenStartX + gap, screenStartY, screenEndX - gap, screenEndY, -16777216);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();

        mc.getTextureManager().bindTexture(Vision.NAVIGATION_SHOP_KIT);
        drawModalRectWithCustomSizedTexture(
                screenStartX, screenStartY,
                0, 198 >> 1,
                width, screenHeight,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        drawModalRectWithCustomSizedTexture(
                screenEndX - gap, screenStartY,
                8 >> 1, 198 >> 1,
                width, screenHeight,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        GlStateManager.disableBlend();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void setResolution(int width, int height) {
        screenWidth = 892 >> 1;
        screenHeight = 60 >> 1;

        screenStartX = 20 >> 1;
        screenStartY = 20 >> 1;

        screenEndX = screenStartX + screenWidth;
        screenEndY = screenStartY + screenHeight;

        for (int i = 0; i < buttonList.size(); i++) {
            Button button = (Button) buttonList.get(i);

            button.updateCords(screenStartX + (4 >> 1) + (i * (button.width + (4 >> 1))), screenStartY + (4 >> 1));//firstButton on x = 24 y = 24
        }
    }

    public NavigationBarButton getButton(String name){
        return navigationBarButtons.get(name);
    }


    private NavigationBarButton addButton(NavigationBarButton statusButton) {
        super.addButton(statusButton);
        navigationBarButtons.put(statusButton.displayString,statusButton);
        return statusButton;
    }
}
