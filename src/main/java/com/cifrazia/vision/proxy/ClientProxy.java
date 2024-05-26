package com.cifrazia.vision.proxy;

import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.core.network.handlers.client.ClientPacketBuyHandler;
import com.cifrazia.vision.core.network.packets.BuyItemPacket;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        vision.getNetwork().registerMessage(new ClientPacketBuyHandler(), BuyItemPacket.class, id++, Side.CLIENT);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);

        authorized = new AuthorizedClient();

    }


}
