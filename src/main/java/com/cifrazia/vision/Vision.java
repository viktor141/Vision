package com.cifrazia.vision;


import com.cifrazia.vision.connection.auth.Authorized;
import com.cifrazia.vision.proxy.CommonProxy;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(modid = Vision.MOD_ID, name = Vision.MOD_NAME, version = "1.12")
public class Vision {
    public static final String MOD_ID = "vision";
    public static final String MOD_NAME = "Vision";
    public static final String VERSION = Vision.class.getPackage().getImplementationVersion();
    public static final int SIZE_OF_TEXTURE_KIT = 512 >> 1;
    public static final ResourceLocation BACKGROUND = new ResourceLocation(Vision.MOD_ID, "textures/gui/server_enter.png");
    public static final ResourceLocation SERVER_KIT = new ResourceLocation(Vision.MOD_ID, "textures/gui/server_enter_kit.png");
    public static final ResourceLocation IN_GAME_KIT = new ResourceLocation(Vision.MOD_ID, "textures/gui/esc_menu_kit.png");
    public static final ResourceLocation NAVIGATION_SHOP_KIT = new ResourceLocation(Vision.MOD_ID, "textures/gui/shop_items_kit.png");
    public static final ResourceLocation STOCK_AND_BUNK_KIT = new ResourceLocation(Vision.MOD_ID, "textures/gui/stock_and_bank_kit.png");
    public static final ResourceLocation DONATION_KIT = new ResourceLocation(Vision.MOD_ID, "textures/gui/donations_kit.png");
    public static final ResourceLocation MODAL_KIT = new ResourceLocation(Vision.MOD_ID, "textures/gui/modals_shop_kit.png");
    public static final long interval = 5 * 60000; //5 min
    private static Vision instance;
    private final SimpleNetworkWrapper network = new SimpleNetworkWrapper(Vision.MOD_ID);
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    public static boolean wasTest;
    public Logger logger;

    @SidedProxy(clientSide = "com.cifrazia.vision.proxy.ClientProxy", serverSide = "com.cifrazia.vision.proxy.ServerProxy")
    public static CommonProxy proxy;

    public Vision() {
        instance = this;
    }

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = LogManager.getLogger(MOD_ID);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);


        logger.info("mod version: {}", VERSION != null ? VERSION : "unknown");


        List<String> list = (List<String>) Launch.blackboard.get("ArgumentList");

        for (String string : list) {
            System.out.println("argument carasya = " + string);
        }

        for (Map.Entry entry : Launch.blackboard.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        
    }


    public static Vision getInstance() {
        return instance;
    }

    public SimpleNetworkWrapper getNetwork() {
        return network;
    }

    public Authorized getAuthorization() {
        return proxy.getAuthorized();
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

}
