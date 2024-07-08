package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.CifraziaUser;
import com.cifrazia.vision.core.abstracts.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class PlayerInfoPanel extends Gui {
    private final boolean reverse;
    private final CifraziaUser cifraziaUser;
    private int x;
    private int y;

    public PlayerInfoPanel(Minecraft mc, boolean reverse) {
        this.mc = mc;
        this.reverse = reverse;

        cifraziaUser = ((AuthorizedClient) Vision.getInstance().getAuthorization()).getUserData().getCifraziaUser();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.renderEngine.bindTexture(Vision.DONATION_KIT);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x - (4 >> 1), y - (4 >> 1),
                354 >> 1, 0,
                56 >> 1, 56 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        drawModalRectWithCustomSizedTexture(
                x, y,
                436 >> 1, 0,
                48 >> 1, 48 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();

        if (cifraziaUser.getGroups().isEmpty()) return;
        CifraziaUser.Group group = cifraziaUser.getGroups().get(0);

        drawRevertibleString(group.getRole().getDisplay_name(), y + (6 >> 1));
        drawRevertibleString((group.getExpired_at() != 0) ? String.valueOf(group.getExpired_at()) : "Forever", y + (30 >> 1));

        drawSkinHead(x, y);
    }

    private void drawSkinHead(int x, int y) {
        this.mc.getTextureManager().bindTexture(mc.player.getLocationSkin());
        drawScaledCustomSizeModalRect(x + 4, y + 4, 8.0F, 8.0F, 8, 8, 16, 16, 64.0F, 64.0F);
    }

    private void drawRevertibleString(String text, int textY) {
        int textX;
        if (reverse) {
            textX = x - (6 >> 1) - mc.fontRenderer.getStringWidth(text);
        } else {
            textX = x + (64 >> 1);
        }
        drawString(mc.fontRenderer, text, textX, textY, -1);
    }

    public void setUp(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
