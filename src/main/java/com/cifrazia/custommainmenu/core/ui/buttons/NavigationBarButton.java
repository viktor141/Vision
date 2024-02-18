package com.cifrazia.custommainmenu.core.ui.buttons;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;
import java.util.List;


public class NavigationBarButton extends ActiveButton {
    private final int texturePressedPosY;

    public NavigationBarButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y, String buttonText) {
        super(parentGUI, buttons, x, y, 144 >> 1, 52 >> 1, buttonText, 14737632);

        buttonTextureKit = CustomMainMenu.NAVIGATION_SHOP_KIT;
        texturePosX = 0;
        texturePosY = 362 >> 1;
        texturePressedPosY = 306 >> 1;

    }

    @Override
    protected void hoverStateRender() {
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        drawModalRectWithCustomSizedTexture(
                x, y,
                texturePosX, pressed ? texturePressedPosY : texturePosY,
                width, height,
                sizeOfTextureKit, sizeOfTextureKit);


        this.drawCenteredString(mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
    }
}
