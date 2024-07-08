package com.cifrazia.vision.core.ui.util;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import com.cifrazia.vision.core.ui.util.texture.TextureData;
import com.cifrazia.vision.core.ui.util.texture.TextureSizeData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;
import java.util.List;

public class Element {
    private final Minecraft mc;
    private final String text;
    private final String underText;
    private final TextureSizeData size = new TextureSizeData(Vision.SIZE_OF_TEXTURE_KIT);
    private final TextureData box;
    private final TextureData check;
    private final TextureData cross;
    private final Color textColor;
    private final Color underTextColor;
    private int textGap;
    private ScaledResolution scaledResolution;
    private boolean flag;
    private int scaledSize;
    private int maxLength;
    private int x;
    private int y;
    private float boxScale;
    private float textScale;


    public Element(Minecraft mc, String text, String underText, Color textColor, Color underTextColor, boolean flag) {
        this.mc = mc;
        this.text = text;
        this.underText = underText;
        this.textColor = textColor;
        this.underTextColor = underTextColor;
        this.flag = flag;

        box = new TextureData(size, 210 >> 1, 0, 48 >> 1, 48 >> 1);
        check = new TextureData(size, 306 >> 1, 0, 32 >> 1, 32 >> 1);
        cross = new TextureData(size, 266 >> 1, 0, 32 >> 1, 32 >> 1);
    }

    public Element(Minecraft mc, String text, String underText, Color textColor, Color underTextColor) {
        this(mc, text, underText, textColor, underTextColor, true);
    }

    public Element(Minecraft mc, String text, String underText) {
        this(mc, text, underText, Color.WHITE_100, Color.WHITE_100);

    }

    public Element(Minecraft mc, String text, Color textColor) {
        this(mc, text, null, textColor, Color.WHITE_100);
    }

    public Element(Minecraft mc, String text, Color textColor, boolean flag) {
        this(mc, text, null, textColor, Color.WHITE_100, flag);
    }

    public void drawElement(int mouseX, int mouseY) {
        if (maxLength == 0) return;

        mc.getTextureManager().bindTexture(Vision.DONATION_KIT);


        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.pushMatrix();
        GlStateManager.scale(boxScale, boxScale, 1.0f);
        Draw.drawModalSquareWithCustomSizedTexture(
                (int) (x / boxScale), (int) (y / boxScale),
                box.getX(), box.getY(),
                box.getWidth(), box.getHeight(),
                box.getSize());

        if (flag) {
            Draw.drawModalSquareWithCustomSizedTexture(
                    (int) (x / boxScale) + (8 >> 1), (int) (y / boxScale) + (8 >> 1),
                    check.getX(), check.getY(),
                    check.getWidth(), check.getHeight(),
                    check.getSize());
        } else {
            Draw.drawModalSquareWithCustomSizedTexture(
                    (int) (x / boxScale) + (8 >> 1), (int) (y / boxScale) + (8 >> 1),
                    cross.getX(), cross.getY(),
                    cross.getWidth(), cross.getHeight(),
                    cross.getSize());
        }
        GlStateManager.popMatrix();

        /*if (underText != null) {

            int upperDesc = scaledSize >> 2;
            int lowerDesc = scaledSize - upperDesc;

            upperDesc += upperDesc % 2;
            lowerDesc += lowerDesc % 2;

            if (flag) {
                drawDescription(upperDesc, boxScale, text, textColor.getFullColor());
                drawDescription(lowerDesc, textScale, underText, underTextColor.getFullColor());
            } else {
                drawDescription(upperDesc, textScale, text, textColor.getFullColor());
                drawDescription(lowerDesc, textScale, underText, underTextColor.getFullColor());
            }

        } else {
            drawDescription(scaledSize >> 1, textScale, text, textColor.getFullColor());
        }*/

        List<String> info = new ArrayList<>();
        int addedX = scaledSize + 4;
        int allowedLength = maxLength - (addedX + 15);


        if (underText != null) {
            if(flag){
                addText(info, underText, (int) (allowedLength / textScale));
                drawInfo(info, text, addedX, textColor, underTextColor);
            } else {
                addText(info, text, (int) (allowedLength / textScale));
                drawInfo(info, underText, addedX, underTextColor, textColor);
            }

        } else {
            addText(info, text, (int) (allowedLength / textScale));
            drawAbility(info, addedX);
        }

    }

    private void drawInfo(List<String> description, String text, int addedX, Color upperTextColor, Color lowerTextColor) {
        float upperTextScale;
        float lowerTextScale = textScale;
        if (flag) {
            upperTextScale = boxScale;
        } else {
            upperTextScale = textScale;
        }

        int heightOfText = 0;
        int heightOfCommand = scaleCorrection(mc.fontRenderer.FONT_HEIGHT, upperTextScale);
        int heightOfDescription = scaleCorrection(((mc.fontRenderer.FONT_HEIGHT + textGap) * description.size()) - textGap, lowerTextScale);

        heightOfText += heightOfCommand;
        heightOfText += heightOfDescription;

        int startY = y + (scaledSize >> 1) - (heightOfText >> 1);

        drawText(text, x + addedX, flag ? (startY) : (startY + heightOfDescription + textGap + 1), upperTextColor, upperTextScale);

        for (int i = 0; i < description.size(); i++) {
            drawText(
                    description.get(i),
                    x + addedX,
                    (flag ? startY + heightOfCommand + textGap : startY) + (scaleCorrection(mc.fontRenderer.FONT_HEIGHT + textGap, lowerTextScale) * i),
                    lowerTextColor, lowerTextScale);
        }
    }

    private void drawAbility(List<String> description, int addedX) {
        int heightOfDescription = scaleCorrection(((mc.fontRenderer.FONT_HEIGHT + textGap) * description.size()) - textGap, textScale);

        int startY = y + (scaledSize >> 1) - (heightOfDescription >> 1);

        for (int i = 0; i < description.size(); i++) {
            drawText(description.get(i), x + addedX, startY + (scaleCorrection(mc.fontRenderer.FONT_HEIGHT + textGap, textScale) * i), textColor, textScale);
        }
    }


    private void addText(List<String> info, String text, int allowedLength) {
        if (text == null) return;
        info.addAll(mc.fontRenderer.listFormattedStringToWidth(text, allowedLength));
    }

    private void drawText(String text, int x, int y, Color color, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1.0f);

        mc.fontRenderer.drawString(text, (int) (x / scale), (int) (y / scale), color.getFullColor());

        GlStateManager.popMatrix();
    }

    private int scaleCorrection(int value, float scale) {
        return (int) (value * scale);
    }

    private void drawDescription(int addedY, float scale, String text, int color) {
        int addedX = getSize() + (10 >> 1);
        int gap = 1;

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1.0f);

        int allowedLength = maxLength - (addedX + 15);
        List<String> list = mc.fontRenderer.listFormattedStringToWidth(text, (int) (allowedLength / scale));
        int fixY = (((mc.fontRenderer.FONT_HEIGHT + gap) * list.size()) - gap) >> 1;

        for (int i = 0; i < list.size(); i++) {
            mc.fontRenderer.drawString(
                    list.get(i),
                    (int) ((x + addedX) / scale),
                    ((int) ((y + addedY) / scale)) + ((mc.fontRenderer.FONT_HEIGHT + gap) * i) - fixY,
                    color);
        }

        GlStateManager.popMatrix();
    }


    public void setUp(int x, int y, int maxLength, float boxScale, float textScale, int scaledSize) {
        this.x = x;
        this.y = y;
        this.maxLength = maxLength;
        this.boxScale = boxScale;
        this.textScale = textScale;
        this.scaledSize = scaledSize;
        this.textGap = 2;
    }

    public void updateY(int y) {
        this.y = y;
    }

    public int getSize() {
        return scaledSize;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getBoxWidth() {
        return box.getWidth();
    }
}
