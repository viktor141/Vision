package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.CifraziaUser;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.ui.buttons.BlueWideButton;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class PlayerSkinPanel extends Screen {
    private final AuthorizedClient client;
    private final CifraziaUser cifraziaUser;
    private final BlueWideButton skinLink;
    private final int gapX = 30;
    private final int scale;

    public PlayerSkinPanel(Minecraft mc, Gui parentGui) {
        this.mc = mc;

        client = (AuthorizedClient) Vision.getInstance().getAuthorization();
        cifraziaUser = client.getUserData().getCifraziaUser();

        ScaledResolution scaledResolution = new ScaledResolution(mc);
        scale = (int) (scaledResolution.getScaledHeight() * 0.43f);

        skinLink = new BlueWideButton(parentGui, 0, 0, "Skin");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        Draw.drawPlayerModel(
                scale >> 1,
                (int) (screenStartY + (scale * 1.4) - 30),
                scale,
                -1, -1);

        mc.renderEngine.bindTexture(Vision.IN_GAME_KIT);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                screenStartX + gapX, screenStartY - (59 >> 1),
                276 >> 1, 30 >> 1,
                210 >> 1, 54 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();

        int nameX = screenStartX + gapX + (210 >> 2);
        int nameY = screenStartY + (54 >> 2) - (59 >> 1) - (mc.fontRenderer.FONT_HEIGHT >> 1);

        drawCenteredString(mc.fontRenderer, cifraziaUser.getUsername(), nameX, nameY, -1);

        skinLink.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public void setResolution(int width, int height) {
        screenWidth = width;
        screenHeight = height;

        screenStartX = 60 >> 1;//59
        screenStartY = screenHeight >> 1;

        skinLink.updateCords(screenStartX + 3 + gapX, screenStartY + (5 >> 1) + 3);

    }
}
