package com.cifrazia.vision.proxy;

import com.cifrazia.vision.connection.auth.AuthorizedServer;
import com.cifrazia.vision.core.network.handlers.server.ServerBuyPacketHandler;
import com.cifrazia.vision.core.network.packets.BuyItemPacket;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        vision.getNetwork().registerMessage(new ServerBuyPacketHandler(), BuyItemPacket.class, id++, Side.SERVER);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        authorized = new AuthorizedServer();
    }
}
