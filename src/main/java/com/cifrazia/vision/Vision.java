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
        proxy.preInit(event);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        logger = LogManager.getLogger(MOD_ID);
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


    /*{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjFhNGM5ODQ1ZTQ0YzRlN2ZiYmUzOTI3N2JlNzdkMjRhIiwicGsiOiIwMDA2MTRlMmRmYWE4ZTg4ODZjNjE3ZGRmNWY3NTc3YyIsImlwIjoiOTQuMTgxLjIyLjUxIiwiZXhwaXJlIjoxNzE3NTkzNDQwLjkyM30.q149tlJOlg1quqfjovvmutGYBDEtPcYug_f4bP974Cwcr92tjZD-rqRvltxB3Qj799W4apd2yZz_moXTX0VpuNQmxv7sG2FHkXuCcvs1zZ8wUOg4KDnhASRQCCmgyLZdLlEAlZ3tZN2cExJLlQGMK8cO2qrqTt1d9FnWKsNNEuTLXF59K4j4rj4IiJjuP-By2dsBhFIbaYSm2bv-54Sz2yb_gAC2t1Ajx96e7DDBqtjjrAmpmLrfnvfMjNZLCvntL-HH6Db_TyZadxqWU0nBkeKge7GIbYb2RwKVbk46bqoJi3XgUiafqqpKMyHo98BbAZ4dvpSxgoSs3OGFCu7O4vCqnXR8erR6mgMXOeIi2ZD7CZ7Q81LYs-iqx3eH4Rijgv4J-bC4comkC18K_IuNmw3BGGy_4S-s5ffy1nLSAj0BL2uutvyM7CfoGKW8P9SG8_QDHYfK7MjUABLZg6xpzf_8pcmgoVTxCZvzyi3YmEw3r18PvTDLTwkDNkWq8h3ysY7oAAc5ZzLFt2XRkfVkzDetsgmpHTRP47SlAJAWx8QR1S0QfGblxfcvTmdkvSsO6hONxScCBPKqxnnL02QbjwDISTIenOx1nqYMwHLedP1mE_bzs-lNtZZ_vJi43G7bXEF8K5LJigculQjAEYzvJGyf8Mer05Haf5L1TotzJpw",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjhmMGUwYmNkMmMzMzRkYzU5MTljYWJmOTEzNjViM2E1IiwiYWNjZXNzX2lkIjoiMWE0Yzk4NDVlNDRjNGU3ZmJiZTM5Mjc3YmU3N2QyNGEiLCJleHBpcmUiOjE3MjI3Nzc0NDAuOTIzfQ.pUSytGjHPSHU4Ivm-V2yCQPBKdV6RQZKV_zSuZ7hqUo7-RRZbVhoWqbzbfcn--I_pefoXwz73eS_OkCa6skLfu_VqG4jx02gzffeUVS-CGvvk4WNoI1GMjtbiFbtb9oy5UKj3AH2CFkVX0wqBjQYKEZP-RESMHGffRKEfnfKCroI_g_BXGjXBNm7UTjq4xTNiUd8Gd00HQeEwAPAKbiEV41QwWr7ExxIZ1_ZFkvKc9eQDgprOP9KZ5LNjm4WBgqR28GcLA3C4a8X1dG870xdo36ymJRojVU2a2muLAoKbETKP__f-XjAJSk-WeRLx78AfFBphjJs4LHXgL0afPnKTaqYxAQfVOb388WY9JE_aFcBnxzrcyHWs2V6D8QCLdrZzOE6cg1wnmCjs2CN174OgGmF0fmxWUXZEHWUIweMI9ZWGh8qJ2AVZAyKJLoFgb4hKvyyUL1PH4NnePjjT1I8qjCFk3-k0rckGomS4uEgDnINd9b2dlgHSeSFJg33eoQX2ERnARkWJRsDYJxgCIx35mxAWsC-gfPmidcCzCGAeiRZfizL9GDTJtEae53S0ToMx9oKCeuqXPUKPUV60pLGy3q_qf9-ZgmhXEG6XYlXC2BiEXYR5hh6QWnum-k_D8BBsQ-_Tm-s8URU9xJUWI374y66shKlrfOrbZGZKHrFEOk"
}*/

}
