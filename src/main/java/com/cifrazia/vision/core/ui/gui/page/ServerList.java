package com.cifrazia.vision.core.ui.gui.page;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.ServerData;
import com.cifrazia.vision.connection.data.element.ServerInfo;
import com.cifrazia.vision.connection.data.element.server.Server;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.ScrollableScreen;
import com.cifrazia.vision.core.client.misc.MinecraftServerPing;
import com.cifrazia.vision.core.ui.buttons.SmallButton;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.ScissoredDraw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;
import java.util.List;

import static com.cifrazia.vision.Vision.SERVER_KIT;
import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class ServerList extends ScrollableScreen {
    private final int gapX = 20 >> 1;
    private final int gapY = 30 >> 1;
    protected final int TEXTURE_POS_X = 44 >> 1;
    protected final float TEXTURE_POS_Y = 376 >> 1;
    private final int widthOfPanel = 210 >> 1;
    private final int heightOfPanel = 120 >> 1;
    private final ServerData serverData;
    private final Gui parent;
    private SmallButton[] serverButtons;
    private List<Server> servers;
    private List<MinecraftServerPing> serversPing;
    private ScissoredDraw scissoredDraw;


    public ServerList(Minecraft mc, Gui parent) {
        super(16 >> 1, 4 >> 1);
        this.mc = mc;
        this.parent = parent;

        serverData = ((AuthorizedClient) Vision.getInstance().getAuthorization()).getServerData();
    }

    public void serversUpdate() {
        servers = serverData.getServers();
        serverButtons = new SmallButton[servers.size()];
        serversPing = new ArrayList<>(servers.size());
        for (Server server : servers) {
            MinecraftServerPing minecraftServerPing = new MinecraftServerPing(server);
            serversPing.add(minecraftServerPing);
            minecraftServerPing.serverPing();
        }

        for (int i = 0; i < serverButtons.length; i++) {
            SmallButton serverButton = new SmallButton(this, "Play");
            Server server = servers.get(i);
            serverButton.setEvent(() -> serverData.connect(mc, parent, server));

            serverButtons[i] = serverButton;
        }
    }

    public void drawServerListGui(int mouseX, int mouseY, float partialTicks) {
        scissoredDraw.draw(() -> {
            drawServers(mouseX, mouseY, partialTicks);
        });

        scrollBar.drawScrollBar();
    }

    public void setResolution(int width, int height) {
        int gapDownSide = 146 >> 1;//145
        screenStartX = 304 >> 1;//305
        if (width - (screenStartX << 1) < widthOfPanel) {
            screenStartX = (width - widthOfPanel) >> 1;
        }
        screenStartY = 126 >> 1;//125
        screenEndX = width - screenStartX;
        screenEndY = height - gapDownSide;
        screenWidth = width - (screenStartX << 1);
        screenHeight = height - gapDownSide - screenStartY;

        scrollBar.startPositions(screenEndX + gapX, screenStartY);
        scrollBar.setResolution(screenHeight, screenHeight, screenHeight);

        scissoredDraw = new ScissoredDraw(mc, screenStartX, screenStartY, screenWidth, screenHeight);
    }

    private void drawServers(int mouseX, int mouseY, float partialTicks) {
        int columns = screenWidth / (widthOfPanel + gapX);
        columns = Math.max(Math.min(servers.size(), Math.max(columns, 1)), 1);

        int rows = (Math.max(servers.size(), 1) / columns) + ((servers.size() % columns) > 0 ? 1 : 0);

        scrollBar.setContentHeight((rows * (heightOfPanel + gapY)) - (gapY >> 1));

        int screenGapX = (screenWidth - (columns * (widthOfPanel + gapX))) >> 1;
        int screenGapY = Math.max((screenHeight - (rows * (heightOfPanel + gapY))) >> 1, 0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < Math.min(columns, servers.size() - (columns * i)); j++) {
                int x = screenStartX + (gapX >> 1) + screenGapX + (j * (gapX + widthOfPanel));
                int y = screenStartY + screenGapY + (i * (gapY + heightOfPanel)) + scrollBar.getScrollOffset();

                drawServerPanel(x, y, columns * i + j);

                drawButton(mouseX, mouseY, partialTicks, x + (52 >> 1), y + (92 >> 1), columns * i + j);
            }
        }
    }

    private void drawServerPanel(int x, int y, int serverId) {
        Server server = servers.get(serverId);
        GlStateManager.enableBlend();
        drawServerPanelTexture(x, y);

        GlStateManager.pushMatrix();
        float size = 0.825f;
        GlStateManager.scale(size, size, size);
        drawString(mc.fontRenderer, server.getName(), (int) ((x + (20 >> 1)) / size), (int) ((y + (20 >> 1)) / size), -932221073);
        GlStateManager.popMatrix();

        drawServerStatus(x, y, serverId);
        GlStateManager.disableBlend();
    }

    private void drawServerPanelTexture(int x, int y) {
        mc.getTextureManager().bindTexture(SERVER_KIT);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        drawModalRectWithCustomSizedTexture(
                x,
                y,
                TEXTURE_POS_X, TEXTURE_POS_Y,
                widthOfPanel, heightOfPanel,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
    }

    private void drawServerStatus(int x, int y, int serverId) {
        MinecraftServerPing serverPing = serversPing.get(serverId);
        ServerInfo info = serverPing.getServerInfo();

        int barStartX = (24 >> 1), barStartY = (74 >> 1);

        if (info == null) {
            drawString(mc.fontRenderer, "Offline", x + (20 >> 1), y + (46 >> 1), Color.RED.getFullColor());

            drawRect(x + barStartX, y + barStartY, x + barStartX + (162 >> 1), y + barStartY + (6 >> 1), Color.RED.getFullColor());
        } else {
            int barEndX = (int) (barStartX + (162 >> 1) * (info.getPlayers().getOnline() / (float) info.getPlayers().getMax()));
            int barEndY = barStartY + (6 >> 1);

            drawString(mc.fontRenderer, "Online: " + info.getPlayers().getOnline(), x + (20 >> 1), y + (46 >> 1), -922746881);
            drawGradientRect(x + barStartX, y + barStartY, x + barEndX, y + barEndY, -922746881, -2137614698);
        }
    }

    private void drawButton(int mouseX, int mouseY, float partialTicks, int x, int y, int id) {
        mc.getTextureManager().bindTexture(SERVER_KIT);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        serverButtons[id].updateCords(x, y);
        serverButtons[id].drawButton(mc, mouseX, mouseY, partialTicks);
    }

   /* @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton != 0) return;
        if (mouseX >= screenStartX && mouseY >= screenStartY && mouseX < screenEndX && mouseY < screenEndY) {
            for (SmallButton smallButton : serverButtons) {
                if (smallButton.isMouseOver()) {
                    smallButton.playPressSound(mc.getSoundHandler());
                }
            }
        } else {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }*/

    //drawRect(screenStartX, screenStartY, screenEndX, screenEndY, -923404811);


    /*int scissorY = screenStartY * scaledResolution.getScaleFactor();
        int scissorHeight = screenHeight * scaledResolution.getScaleFactor();
        scissorY = mc.displayHeight - scissorY - scissorHeight;

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(screenStartX * scaledResolution.getScaleFactor(), scissorY, screenWidth * scaledResolution.getScaleFactor(), scissorHeight);

        drawServers(mouseX, mouseY, partialTicks);

        GL11.glDisable(GL11.GL_SCISSOR_TEST);*/
}