package com.cifrazia.vision.core.ui.util;

import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

public class ScrollBar extends Gui {
    private int startX;
    private int startY;
    private int screenHeight;
    private int barBackgroundHeight;
    private final int barBackgroundWidth;
    private int barHeight;
    private int barWidth;
    private final int barGap;
    private int scrollOffset;
    private int maxScroll;
    private float ratio;
    private long timeSinceLastClick = -1;

    public ScrollBar(int barBackgroundWidth, int barGap) {
        this.barBackgroundWidth = barBackgroundWidth;
        this.barGap = barGap;
        maxScroll = 0;
    }

    public void startPositions(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void drawScrollBar() {
        if (maxScroll >= 0) return;
        drawRect(startX, startY, startX + barBackgroundWidth, startY + barBackgroundHeight, -2147483648);

        int offset = (int) (scrollOffset * ratio);

        drawGradientRect(
                startX + barGap, startY + barGap + Math.abs(offset),
                startX + barGap + barWidth, startY + (int) (barHeight * ratio) + Math.abs(offset),
                -1, -6908266);
    }

    private void moveBarSlider(int mouseX, int mouseY, long timeSinceLastClick) {
        if ((mouseX >= startX && mouseY >= startY && mouseX < startX + barBackgroundWidth && mouseY < startY + barBackgroundHeight && timeSinceLastClick <= 100) ||
                (timeSinceLastClick >= this.timeSinceLastClick && this.timeSinceLastClick != -1)) {
            int localMouseY = mouseY - startY - barGap;

            scrollOffset = Math.min(0, Math.max((int) -((localMouseY - ((barHeight * ratio) / 2)) / ratio), maxScroll));

            this.timeSinceLastClick = timeSinceLastClick;
        }
    }

    public void setResolution(int screenHeight, int barBackgroundHeight, int contentHeight) {
        this.screenHeight = screenHeight;
        this.barBackgroundHeight = barBackgroundHeight;
        barHeight = barBackgroundHeight - (barGap << 1);
        barWidth = barBackgroundWidth - (barGap << 1);
        setContentHeight(contentHeight);

        if(scrollOffset < maxScroll && maxScroll < 0) {
            scrollOffset = maxScroll;
        }
    }

    public void setContentHeight(int contentHeight) {
        maxScroll = screenHeight - contentHeight;

        ratio = 1;
        if (maxScroll < 0) ratio = (float) screenHeight / (-maxScroll + screenHeight);
    }

    public int getContentHeight() {
        return screenHeight - maxScroll;
    }

    private void setScrollOffset(int scroll) {
        if (scroll != 0) {
            scrollOffset += scroll / 5;
            scrollOffset = Math.min(0, Math.max(scrollOffset, maxScroll));
        }
    }

    public void handleMouseInput() {
        setScrollOffset(Mouse.getDWheel());
    }

    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (clickedMouseButton != 0) return;
        moveBarSlider(mouseX, mouseY, timeSinceLastClick);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton != 0) return;
        moveBarSlider(mouseX, mouseY, 0);
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (state != 0) return;
        timeSinceLastClick = -1;
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public int getBarWidth() {
        return barBackgroundWidth;
    }

    public void update() {
        scrollOffset = 0;
    }

    public int getMaxScroll() {
        return maxScroll;
    }
}
