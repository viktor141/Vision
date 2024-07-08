package com.cifrazia.vision.core.ui.util.draw;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.shop.ShopTradeOffer;
import com.cifrazia.vision.core.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

import java.nio.FloatBuffer;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class Draw extends Gui {

    public static void drawModalSquareWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float size) {
        drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, size, size);
    }

    public static void drawGradientRectangle(int left, int top, int right, int bottom, Color startColor, Color endColor, boolean isHorizontal) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);

        bufferbuilder.pos((isHorizontal ? left : right), top, 0.0D).color(startColor.getRf(), startColor.getGf(), startColor.getBf(), startColor.getAf()).endVertex();
        bufferbuilder.pos(left, (isHorizontal ? bottom : top), 0.0D).color(startColor.getRf(), startColor.getGf(), startColor.getBf(), startColor.getAf()).endVertex();

        bufferbuilder.pos((isHorizontal ? right : left), bottom, 0.0D).color(endColor.getRf(), endColor.getGf(), endColor.getBf(), endColor.getAf()).endVertex();
        bufferbuilder.pos(right, (isHorizontal ? top : bottom), 0.0D).color(endColor.getRf(), endColor.getGf(), endColor.getBf(), endColor.getAf()).endVertex();

        tessellator.draw();

        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawCenteredStringWidthShadow(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        fontRendererIn.drawStringWithShadow(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color);
    }

    public static void drawBuyButtonWithPrice(Minecraft mc, int x, int y, int width, int height, ShopTradeOffer offer, int color) {
        int stringX = x + (width >> 1) - (width >> 2);
        int yCenter = y + (height >> 1) - 4;

        String text = "Buy";
        String price = String.valueOf(offer.getShopItem().getPrice() * offer.getCount());

        int stringWidth = mc.fontRenderer.getStringWidth(price) + (16 >> 1);
        int gap = 12;
        stringX -= gap >> 2;
        int priceX = x + (width >> 2) + (width >> 1) - (stringWidth >> 1) - gap;
        int xFixer = (width >> 1) - ((stringWidth >> 1) + (mc.fontRenderer.getStringWidth(text) >> 1) + gap);

        if (xFixer >= 4) {
            xFixer -= 4;
        }

        xFixer = xFixer >> 1;
        stringX += xFixer;
        priceX -= xFixer;

        drawCenteredStringWidthShadow(mc.fontRenderer, text, stringX, yCenter, color);

        drawRect(priceX, yCenter - 3, priceX + stringWidth + (12 >> 1), yCenter + (22 >> 1), Color.SIX_PRESENT.getFullColor());
        drawCenteredStringWidthShadow(mc.fontRenderer, price, priceX + (stringWidth >> 1), yCenter, color);

        mc.getTextureManager().bindTexture(Vision.IN_GAME_KIT);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                priceX + stringWidth, yCenter - 2,
                34 >> 1, 0,
                24 >> 1, 24 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();
    }

    public static void drawPlayerModel(int posX, int posY, int scale, float mouseX, float mouseY) {
        float viewPointX = mouseX != -1 ?  posX - mouseX : -26F;
        float viewPointY = mouseY != -1 ? posY - (scale * 1.66f) - mouseY : -2.94F;
        EntityLivingBase player = Minecraft.getMinecraft().player;

        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY,  - 500);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = player.renderYawOffset;
        float f1 = player.rotationYaw;
        float f2 = player.rotationPitch;
        float f3 = player.prevRotationYawHead;
        float f4 = player.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        frontLightning();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float) Math.atan((viewPointY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        player.renderYawOffset = (float) Math.atan((viewPointX / 40.0F)) * 20.0F;
        player.rotationYaw = (float) Math.atan((viewPointX / 40.0F)) * 40.0F;
        player.rotationPitch = -((float) Math.atan((viewPointY / 40.0F))) * 20.0F;
        player.rotationYawHead = player.rotationYaw;
        player.prevRotationYawHead = player.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        player.renderYawOffset = f;
        player.rotationYaw = f1;
        player.rotationPitch = f2;
        player.prevRotationYawHead = f3;
        player.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }


    public static void frontLightning()
    {
        Vec3d LIGHT0_POS = (new Vec3d(-0.2D, 1.0D, -0.699999988079071D)).normalize();
        Vec3d LIGHT1_POS = (new Vec3d(-0.8D, 1.0D, 0.699999988079071D)).normalize();

        GlStateManager.enableLighting();
        GlStateManager.enableLight(0);
        GlStateManager.enableLight(1);
        GlStateManager.enableColorMaterial();
        GlStateManager.colorMaterial(1032, 5634);
        GlStateManager.glLight(16384, 4611, setColorBuffer((float) LIGHT0_POS.x, (float) LIGHT0_POS.y, (float) LIGHT0_POS.z, 0.0F));
        float f = 0.6F;
        GlStateManager.glLight(16384, 4609, setColorBuffer(f, f, f, 1.0F));
        GlStateManager.glLight(16384, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.glLight(16384, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.glLight(16385, 4611, setColorBuffer((float) LIGHT1_POS.x, (float) LIGHT1_POS.y, (float) LIGHT1_POS.z, 0.0F));
        GlStateManager.glLight(16385, 4609, setColorBuffer(0.6F, 0.6F, 0.6F, 1.0F));
        GlStateManager.glLight(16385, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.glLight(16385, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.shadeModel(7424);
        float f1 = 0.4F;
        GlStateManager.glLightModel(2899, setColorBuffer(f1, f1, f1, 1.0F));
    }

    public static FloatBuffer setColorBuffer(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_)
    {
        FloatBuffer COLOR_BUFFER = GLAllocation.createDirectFloatBuffer(4);
        COLOR_BUFFER.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
        COLOR_BUFFER.flip();
        return COLOR_BUFFER;
    }

    /*public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0D).tex(u * f, (v + height) * f1).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0D).tex((u + width) * f, (v + height) * f1).endVertex();
        bufferbuilder.pos(x + width, y, 0.0D).tex(((u + width) * f), v * f1).endVertex();
        bufferbuilder.pos(x, y, 0.0D).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }*/
}
/*public static void drawGradientRectHorizontal(int left, int top, int right, int bottom, int startColor, int endColor) {
        float sa = (float) (startColor >> 24 & 255) / 255.0F;
        float sr = (float) (startColor >> 16 & 255) / 255.0F;
        float sg = (float) (startColor >> 8 & 255) / 255.0F;
        float sb = (float) (startColor & 255) / 255.0F;

        float ea = (float) (endColor >> 24 & 255) / 255.0F;
        float er = (float) (endColor >> 16 & 255) / 255.0F;
        float eg = (float) (endColor >> 8 & 255) / 255.0F;
        float eb = (float) (endColor & 255) / 255.0F;

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);

        bufferbuilder.pos(left, top, 0.0D).color(sr, sg, sb, sa).endVertex();
        bufferbuilder.pos(left, bottom, 0.0D).color(sr, sg, sb, sa).endVertex();


        bufferbuilder.pos(right, bottom, 0.0D).color(er, eg, eb, ea).endVertex();
        bufferbuilder.pos(right, top, 0.0D).color(er, eg, eb, ea).endVertex();

        tessellator.draw();

        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawGradientRectVertical(int left, int top, int right, int bottom, int startColor, int endColor) {
        float sa = (float) (startColor >> 24 & 255) / 255.0F;
        float sr = (float) (startColor >> 16 & 255) / 255.0F;
        float sg = (float) (startColor >> 8 & 255) / 255.0F;
        float sb = (float) (startColor & 255) / 255.0F;

        float ea = (float) (endColor >> 24 & 255) / 255.0F;
        float er = (float) (endColor >> 16 & 255) / 255.0F;
        float eg = (float) (endColor >> 8 & 255) / 255.0F;
        float eb = (float) (endColor & 255) / 255.0F;

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);

        bufferbuilder.pos(right, top, 0.0D).color(sr, sg, sb, sa).endVertex();
        bufferbuilder.pos(left, top, 0.0D).color(sr, sg, sb, sa).endVertex();

        bufferbuilder.pos(left, bottom, 0.0D).color(er, eg, eb, ea).endVertex();
        bufferbuilder.pos(right, bottom, 0.0D).color(er, eg, eb, ea).endVertex();

        tessellator.draw();

        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }*/