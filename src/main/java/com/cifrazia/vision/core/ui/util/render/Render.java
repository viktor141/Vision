package com.cifrazia.vision.core.ui.util.render;

import com.cifrazia.vision.core.abstracts.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class Render {

    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    private final Minecraft mc;
    private final float offsetCoefficient = 0.115f;
    private float scale;
    private int renderGap;

    public Render(Minecraft mc) {
        this.mc = mc;

        setScale(32.0F);
    }

    public void renderEffectsItem(ItemStack stack, int x, int y) {
        renderEffectsItem(stack, x, y, mc.getRenderItem().getItemModelWithOverrides(stack, null, Minecraft.getMinecraft().player));
    }

    public void renderEffectsItem(ItemStack stack, int x, int y, IBakedModel bakedmodel) {
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        // mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        if (!stack.isEmpty()) {
            this.setupGuiTransform(x, y, bakedmodel.isGui3d());
            bakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(bakedmodel, ItemCameraTransforms.TransformType.GUI, false);
            this.renderItem(stack, bakedmodel);
        } else {
            mc.getTextureManager().bindTexture(TextureMap.LOCATION_MISSING_TEXTURE);
            Gui.drawModalRectWithCustomSizedTexture(x - 8, y - 8, 0, 0, (int) scale, (int) scale, scale, scale);
        }
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        float factor = (1 + (scale / 16)) * 0.5F;
        GlStateManager.scale(factor, factor, factor);
        renderItemOverlayIntoGUI(mc.fontRenderer, stack, (int) (x / factor), ((int) (y / factor) - 2), null);
        GlStateManager.popMatrix();

        // mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        // mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
    }

    private void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d) {
        GlStateManager.translate(xPosition, yPosition, 200.0F);
        GlStateManager.translate(8.0F, 8.0F, 0.0F);
        GlStateManager.scale(1.0F, -1.0F, 1.0F);
        GlStateManager.scale(scale, scale, scale);

        GlStateManager.enableLighting();
        GlStateManager.enableLight(0); // GL_LIGHT0 is just one of the available light sources

        // Set ambient light (generally set this to a low value)
        float ambient = 0.75F; // Low ambient light
        GlStateManager.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, RenderHelper.setColorBuffer(ambient, ambient, ambient, 1.0f));

        // Set light position (x, y, z, w). The last parameter (w) determines the type (directional or positional)
        GlStateManager.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, RenderHelper.setColorBuffer(0.0f, 0.5f, 0.5f, 0.7f)); // Coming from above

        // Set diffuse and specular light components
        GlStateManager.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, RenderHelper.setColorBuffer(1.0f, 1.0f, 1.0f, 1.0f)); // Bright white light
        //GlStateManager.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, RenderHelper.setColorBuffer(1.0f, 1.0f, 1.0f, 1.0f)); // Sharp white highlights
    }

    private void renderItem(ItemStack stack, IBakedModel model) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);

        if (model.isBuiltInRenderer()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableRescaleNormal();
            stack.getItem().getTileEntityItemStackRenderer().renderByItem(stack);
        } else {
            this.renderModel(model, stack);

            if (stack.hasEffect()) {
                this.renderEffect(model);
            }
        }

        GlStateManager.popMatrix();
    }

    private void renderModel(IBakedModel model, ItemStack stack) {
        this.renderModel(model, -1, stack);
    }

    private void renderModel(IBakedModel model, int color) {
        this.renderModel(model, color, ItemStack.EMPTY);
    }

    private void renderModel(IBakedModel model, int color, ItemStack stack) {
        if (net.minecraftforge.common.ForgeModContainer.allowEmissiveItems) {
            net.minecraftforge.client.ForgeHooksClient.renderLitItem(mc.getRenderItem(), model, color, stack);
            return;
        }
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.ITEM);

        for (EnumFacing enumfacing : EnumFacing.values()) {
            mc.getRenderItem().renderQuads(bufferbuilder, model.getQuads(null, enumfacing, 0L), color, stack);
        }

        mc.getRenderItem().renderQuads(bufferbuilder, model.getQuads(null, null, 0L), color, stack);
        tessellator.draw();
    }

    private void renderEffect(IBakedModel model) {
        GlStateManager.depthMask(false);
        GlStateManager.depthFunc(514);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
        mc.getTextureManager().bindTexture(RES_ITEM_GLINT);
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
        this.renderModel(model, -8372020);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f1 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.translate(-f1, 0.0F, 0.0F);
        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
        this.renderModel(model, -8372020);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableLighting();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    }

    public void renderItemOverlayIntoGUI(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, @Nullable String text) {
        if (!stack.isEmpty()) {
            if (stack.getCount() != 1 || text != null) {
                String s = text == null ? String.valueOf(stack.getCount()) : text;
                GlStateManager.disableDepth();
                fr.drawStringWithShadow(s, (float) (xPosition + 19 - 2 - fr.getStringWidth(s)), (float) (yPosition + 6 + 3), 16777215);
                GlStateManager.enableDepth();
            }

            /*if (stack.getItem().showDurabilityBar(stack))
            {
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                GlStateManager.disableTexture2D();
                GlStateManager.disableAlpha();
                GlStateManager.disableBlend();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();
                double health = stack.getItem().getDurabilityForDisplay(stack);
                int rgbfordisplay = stack.getItem().getRGBDurabilityForDisplay(stack);
                int i = Math.round(13.0F - (float)health * 13.0F);
                int j = rgbfordisplay;
                this.draw(bufferbuilder, xPosition + 2, yPosition + 13, 13, 2, 0, 0, 0, 255);
                this.draw(bufferbuilder, xPosition + 2, yPosition + 13, i, 1, j >> 16 & 255, j >> 8 & 255, j & 255, 255);
                GlStateManager.enableBlend();
                GlStateManager.enableAlpha();
                GlStateManager.enableTexture2D();
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
            }*/

        }
    }

    public void setScale(float scale) {
        this.scale = scale;
        updateRenderGap();
    }

    public float getScale() {
        return scale;
    }

    private void updateRenderGap() {
        renderGap = (int) (scale / (scale * (offsetCoefficient / (((int) scale - 16) >> 4))));
    }

    public int getRenderGap() {
        return renderGap;
    }

    public int normalize(int position) {
        return position - getRenderGap();
    }

    public boolean isMouseOnItem(int mouseX, int mouseY, int itemX, int itemY, ItemStack itemStack) {
        if (itemStack.isEmpty()) return false;

        return mouseX >= normalize(itemX)
                && mouseX <= normalize(itemX) + scale
                && mouseY >= normalize(itemY)
                && mouseY <= normalize(itemY) + scale;
    }

    /*private void draw(BufferBuilder renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha)
    {
        renderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos((x + 0), (y + 0), 0.0D).color(red, green, blue, alpha).endVertex();
        renderer.pos((x + 0), (y + height), 0.0D).color(red, green, blue, alpha).endVertex();
        renderer.pos((x + width), (y + height), 0.0D).color(red, green, blue, alpha).endVertex();
        renderer.pos((x + width), (y + 0), 0.0D).color(red, green, blue, alpha).endVertex();
        Tessellator.getInstance().draw();
    }*/
}
