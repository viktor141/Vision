package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.HighButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.layer.GenLayerShore;

import javax.annotation.Nonnull;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class IconButton extends HighButton {

    public enum Icon {
        MAP(0, 0),
        QUEST(1, 0),
        ISLAND(2, 0),
        AUCTION(3, 0),
        SETTINGS(4, 0),
        SHOP(0, 1),
        CASES(1, 1),
        DONATION(2, 1),
        BANK(3, 1),
        WAREHOUSE(4, 1);

        private final int iconTextureX;
        private final int iconTextureY;
        private final int textureSize = 72 >> 1;
        public static final ResourceLocation ICON_TEXTURES = new ResourceLocation(Vision.MOD_ID, "textures/gui/button_icons.png");

        Icon(int i, int j) {
            iconTextureX = i * (76 >> 1);
            iconTextureY = j * (76 >> 1);
        }

        public void drawIcon(Minecraft mc, int x, int y) {
            mc.getTextureManager().bindTexture(ICON_TEXTURES);

            /*drawScaledCustomSizeModalRect(
                    x, y,
                    ,,
                    , iconTextureY,
                    textureSize, textureSize,
                    512, 512);*/

            GlStateManager.disableRescaleNormal();

            drawModalRectWithCustomSizedTexture(
                    x, y,
                    iconTextureX, iconTextureY,
                    textureSize, textureSize,
                    SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

            int stringX = x + (textureSize >> 1), stringY = y + textureSize + 10;

            String text = this.name().toLowerCase();
            mc.fontRenderer.drawStringWithShadow(text, (float)(stringX - mc.fontRenderer.getStringWidth(text) / 2), (float)stringY, -1);
        }

    }

    private final Icon icon;

    public IconButton(Gui parentGUI, int x, int y, Icon icon) {
        super(parentGUI, x, y, 14737632);

        this.icon = icon;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        icon.drawIcon(mc,x + (33 >> 1), y + (26 >> 1));
    }
}
