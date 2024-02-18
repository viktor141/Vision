package com.cifrazia.custommainmenu.core.ui.gui;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Screen;
import com.cifrazia.custommainmenu.core.ui.buttons.NavigationBarButton;
import com.cifrazia.custommainmenu.core.ui.buttons.base.Button;
import com.cifrazia.custommainmenu.menu.GeneralBusinessMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;
import java.util.List;

import static com.cifrazia.custommainmenu.CustomMainMenu.SIZE_OF_TEXTURE_KIT;

public class NavigationBar extends Screen {

    private final List<NavigationBarButton> navigationBarButtons = new ArrayList<>();


    public NavigationBar(Minecraft mc, GeneralBusinessMenu menu, Screen shop, Donation donation) {
        this.mc = mc;

        addButton(new NavigationBarButton(this, navigationBarButtons, 0, 0, "Shop")).setEvent(() -> {
            menu.setCurrentGui(shop);
        });
        addButton(new NavigationBarButton(this, navigationBarButtons, 0, 0, "Case")).setEvent(() -> {
            navigationBarButtons.get(0).onClick();
        });
        addButton(new NavigationBarButton(this, navigationBarButtons, 0, 0, "Donation")).setEvent(() -> {
            menu.setCurrentGui(donation);
        });
        addButton(new NavigationBarButton(this, navigationBarButtons, 0, 0, "Kit")).setEvent(() -> {
            navigationBarButtons.get(0).onClick();
        });
        addButton(new NavigationBarButton(this, navigationBarButtons, 0, 0, "Warehouse")).setEvent(() -> {

        });
        addButton(new NavigationBarButton(this, navigationBarButtons, 0, 0, "Pass")).setEvent(() -> {
            navigationBarButtons.get(0).onClick();
        });

        navigationBarButtons.get(0).onClick();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        int gap = 8 >> 1;
        int width = 8 >> 1;

        drawRect(screenStartX + gap, screenStartY, screenEndX - gap, screenEndY, -16777216);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();

        mc.getTextureManager().bindTexture(CustomMainMenu.NAVIGATION_SHOP_KIT);
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


    private NavigationBarButton addButton(NavigationBarButton statusButton) {
        super.addButton(statusButton);
        navigationBarButtons.add(statusButton);
        return statusButton;
    }
}
