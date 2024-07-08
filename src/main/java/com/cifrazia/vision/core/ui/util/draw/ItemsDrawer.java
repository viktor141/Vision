package com.cifrazia.vision.core.ui.util.draw;

import com.cifrazia.vision.core.interfaces.FrameTileDraw;
import com.cifrazia.vision.core.ui.util.ScrollBar;

public class ItemsDrawer {

    private ScrollBar scrollBar;
    private ScissoredDraw scissoredDraw;
    private int screenWidth;
    private final int frameSizeX;
    private final int frameSizeY;
    private final int gapBetweenItems;
    private final int gapOfScreen;
    private int itemCount;
    private int x;
    private int y;

    public ItemsDrawer(ScrollBar scrollBar, ScissoredDraw scissoredDraw, int frameSizeX, int frameSizeY, int gapBetweenItems, int gapOfScreen, int itemCount) {
        this.scrollBar = scrollBar;
        this.scissoredDraw = scissoredDraw;
        this.frameSizeX = frameSizeX;
        this.frameSizeY = frameSizeY;
        this.gapBetweenItems = gapBetweenItems;
        this.gapOfScreen = gapOfScreen;
        this.itemCount = itemCount;
    }

    public ItemsDrawer(ScrollBar scrollBar, ScissoredDraw scissoredDraw, int frameSize, int gapBetweenItems, int gapOfScreen, int itemCount) {
        this(scrollBar, scissoredDraw, frameSize, frameSize, gapBetweenItems, gapOfScreen, itemCount);
    }

    public ItemsDrawer(int frameSize, int gapBetweenItems, int gapOfScreen) {
        this(frameSize, frameSize, gapBetweenItems, gapOfScreen);
    }

    public ItemsDrawer(int frameSizeX, int frameSizeY, int gapBetweenItems, int gapOfScreen) {
        this(null, null, frameSizeX, frameSizeY, gapBetweenItems, gapOfScreen, 0);
    }

    public void draw(FrameTileDraw frameTileDraw) {
        int itemsScreenWidth = screenWidth - (scrollBar.getBarWidth() + gapOfScreen);

        int columns = itemsScreenWidth / (frameSizeX + gapBetweenItems);
        int xFixer = (itemsScreenWidth - ((frameSizeX + gapBetweenItems) * columns)) >> 1;
        int rows = (itemCount / columns) + ((itemCount % columns) > 0 ? 1 : 0);

        scrollBar.setContentHeight((rows * (frameSizeY + gapBetweenItems)) - (gapBetweenItems >> 1));

        scissoredDraw.draw(() -> {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < Math.min(columns, itemCount - (columns * i)); j++) {
                    frameTileDraw.run(
                            x + xFixer + gapOfScreen + (gapBetweenItems >> 1) + (j * (frameSizeX + gapBetweenItems)),
                            y + (i * (frameSizeY + gapBetweenItems)) + scrollBar.getScrollOffset(),
                            columns * i + j);
                }
            }
        });
    }

    public void setResolution(int x, int y, int screenWidth) {
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setScrollBar(ScrollBar scrollBar) {
        this.scrollBar = scrollBar;
    }

    public void setScissoredDraw(ScissoredDraw scissoredDraw) {
        this.scissoredDraw = scissoredDraw;
    }
}
