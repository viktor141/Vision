package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.core.network.packets.server.ServerBalanceRequestPacket;

import static com.cifrazia.vision.Vision.interval;

public class PlayerCifraziaData extends DataAuthClient {

    private long ruby;
    private long gold;

    public PlayerCifraziaData(AuthorizedClient service) {
        super(service);
    }

    public void update() {
        Vision.getInstance().getNetwork().sendToServer(new ServerBalanceRequestPacket());
        lastUpdateTime = System.currentTimeMillis();
    }

    public void setBalanceData(long ruby, long gold) {
        this.ruby = ruby;
        this.gold = gold;
        lastUpdateTime = System.currentTimeMillis();
    }

    public long getGold() {
        if (lastUpdateTime < System.currentTimeMillis() - interval) {
            update();
        }
        return gold;
    }

    public long getRuby() {
        if (lastUpdateTime < System.currentTimeMillis() - interval) {
            update();
        }
        return ruby;
    }
}
