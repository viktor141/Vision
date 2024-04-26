package com.cifrazia.vision.proxy;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.Authorized;
import com.cifrazia.vision.core.network.ServerBuyItemPacket;
import com.cifrazia.vision.core.network.ServerPacketHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    protected Authorized authorized;

    public void preInit(FMLPreInitializationEvent event)
    {
        int id = 0;
        Vision.getInstance().getNetwork().registerMessage(new ServerPacketHandler(), ServerBuyItemPacket.class, id++, Side.SERVER);
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public Authorized getAuthorized() {
        return authorized;
    }
}
