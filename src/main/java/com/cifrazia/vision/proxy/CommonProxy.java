package com.cifrazia.vision.proxy;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.Authorized;
import com.cifrazia.vision.core.network.handlers.client.ClientBalanceHandler;
import com.cifrazia.vision.core.network.handlers.client.ClientWarehouseHandler;
import com.cifrazia.vision.core.network.handlers.server.ServerBalanceHandler;
import com.cifrazia.vision.core.network.handlers.server.ServerWarehouseRetrieveHandler;
import com.cifrazia.vision.core.network.packets.client.ClientBalanceResponsePacket;
import com.cifrazia.vision.core.network.packets.client.ClientWarehouseResultPacket;
import com.cifrazia.vision.core.network.packets.server.ServerBalanceRequestPacket;
import com.cifrazia.vision.core.network.packets.server.ServerWarehouseRetrievePacket;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    protected int id = 0;

    protected Authorized authorized;
    protected Vision vision = Vision.getInstance();

    public void preInit(FMLPreInitializationEvent event)
    {
        vision.getNetwork().registerMessage(new ServerWarehouseRetrieveHandler(), ServerWarehouseRetrievePacket.class, id++, Side.SERVER);
        vision.getNetwork().registerMessage(new ClientWarehouseHandler(), ClientWarehouseResultPacket.class, id++, Side.CLIENT);

        vision.getNetwork().registerMessage(new ServerBalanceHandler(), ServerBalanceRequestPacket.class, id++, Side.SERVER);
        vision.getNetwork().registerMessage(new ClientBalanceHandler(), ClientBalanceResponsePacket.class, id++, Side.CLIENT);
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
