package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.PlayerCifraziaData;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.links.DonationLink;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class BankPanel extends Gui {
    private final DonationLink donationLink;
    private final boolean reverse;
    private final PlayerCifraziaData data;
    private int x;
    private int y;

    public BankPanel(Minecraft mc, boolean reverse) {
        this.reverse = reverse;
        this.mc = mc;
        width = 262 >> 1;
        height = 48 >> 1;
        donationLink = addButton(new DonationLink(this, 0, 0));
        data = ((AuthorizedClient) Vision.getInstance().getAuthorization()).getPlayerCifraziaData();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(Vision.IN_GAME_KIT);
        drawRevertible();
        donationLink.drawButton(mc, mouseX, mouseY, partialTicks);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x + (10 >> 1), y + (12 >> 1),
                0, 0,
                24 >> 1, 24 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        drawModalRectWithCustomSizedTexture(
                x + (152 >> 1), y + (12 >> 1),
                34 >> 1, 0,
                24 >> 1, 24 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();

        drawNumber(data.getRuby(), x + (36 >> 1), 9);
        drawNumber(data.getGold(), x + (178 >> 1), 6);
    }

    private void drawRevertible() {
        int backgroundX, backgroundY;
        if (reverse) {
            backgroundX = -(x + width);
            backgroundY = -(y + height);
        } else {
            backgroundX = x;
            backgroundY = y;
        }
        GlStateManager.enableBlend();
        GlStateManager.pushMatrix();
        if (reverse) GlStateManager.rotate(180, 0, 0, 1);
        drawModalRectWithCustomSizedTexture(
                backgroundX, backgroundY,
                0, 32 >> 1,
                width, height,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
    }

    private void drawNumber(long number, int x, int countOfDigits) {
        String digit = String.valueOf(number);
        int bound = countOfDigits - digit.length();
        String result = String.format("%0" + countOfDigits + "d", number);
        String zeros = result.substring(0, bound);

        mc.fontRenderer.drawString(zeros, x, y + (18 >> 1), -10724260);
        mc.fontRenderer.drawString(digit, x + mc.fontRenderer.getStringWidth(zeros), y + (18 >> 1), -1);
    }

    public void setUp(int x, int y) {
        this.x = x;
        this.y = y;

        if (reverse) {
            donationLink.updateCords(x - donationLink.getButtonWidth(), y);
        } else {
            donationLink.updateCords(x + width, y);
        }

    }
}
