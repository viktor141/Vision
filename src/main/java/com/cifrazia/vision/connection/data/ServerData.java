package com.cifrazia.vision.connection.data;

import com.cifrazia.authlib.AuthLibClient;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.server.Server;
import com.cifrazia.vision.core.abstracts.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;

import java.util.List;

import static com.cifrazia.vision.Vision.interval;

public class ServerData extends DataAuthClient {
    private List<Server> servers;

    public ServerData(AuthorizedClient service) {
        super(service);
    }

    public List<Server> getServers() {
        if (servers == null || lastUpdateTime < System.currentTimeMillis() - interval) {
            servers = loadServers();
            lastUpdateTime = System.currentTimeMillis();
        }

        return servers;
    }

    private List<Server> loadServers() {
        return service.getServers();
    }

    public void connect(Minecraft mc, Gui parent, Server server) {
        mc.displayGuiScreen(new GuiConnecting(parent, mc, server.getIp(), server.getPort()));
        AuthLibClient.getInstance().connected(server.getId());
        service.setCurrentServerId(server.getId());
    }

}
