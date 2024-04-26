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
    private static Vision instance;
    private final SimpleNetworkWrapper network = new SimpleNetworkWrapper(Vision.MOD_ID);
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
        logger.info("mod version: " + (VERSION != null ? VERSION : "unknown"));


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
        return  proxy.getAuthorized();
    }


    /*{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjA5MTM4YjFlNjc0YzRiNjNiYzQ0ZWRkNjc1Mzc1NzM2IiwicGsiOiIwMDA2MTRlMmRmYWE4ZTg4ODZjNjE3ZGRmNWY3NTc3YyIsImlwIjoiOTQuMTgxLjIyLjUxIiwiZXhwaXJlIjoxNzE0NzQ2MDgwLjIyM30.oTegA65SkI9_vXgTVCwaHMFnzoLUiD9Dv-idPXKBGYx_rQMItvjQLKyCCM2x-Dtd_5Zpc4T-JqTYipwDSIqFIFkc3q9AAAEzx7N8S3YCkSC9LMlRuLdgpyB2jt-925Gon-5hkspFYpwdFEPd9mc-rVLsEz_ukF84cIH349cHR8jzM3hF_WfxXSE_qYiafcFUmMoDSB1yxSV9dZkQtGX67W-703lWqjaoylZlB-v6pI715ipNa0jMSkK7FarL8D33xY8ArLSTRs7C7TW23FtIIw7dbBNAKdtJPNaO1AaEcMn2O2_9FIBZOyXr6ulGLhx9VNsy0KGJU00ZJKzZ4_4R3IgwVCDJ9pyTAwJu6Y2ntrLq8nr3844oA2AyVpfEg9RAyFq9JRbyamq966Cxvxz4I0mekfX02EgV_3mNI8SLsaPKT2oKp_C68f9SohJDctX8gYrY6htRFt10l4foRypTh5E05bGX1IK1wS5DblIHq5Le9nSVcbVk-l_dyrSLpgBoqY6YgHlcMG249U-VG1ve0TM6Bjw4Y14XMpLrk3lL3rXQ6mtLnIHms4tkxvSi5vktUqi57niRTL-mIbm25ntlelkncPRvoEdMj4QbYE7ZdOUUxCL3hQ6LKLwWldU7cQ9hA-lLX7U1CLdl2H3fzs2mqPhI6OhU0h2V6LUf5i35_k4",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImFlMDk2YTJmNDNlYzQ3ZjM4MmUyMWVlY2ZjOGU1YTQ4IiwiYWNjZXNzX2lkIjoiMDkxMzhiMWU2NzRjNGI2M2JjNDRlZGQ2NzUzNzU3MzYiLCJleHBpcmUiOjE3MTk5MzAwODAuMjIzfQ.vMmuava2CJiH_5bgTzn7IA-p5mhYg8uM4bg4VunPYgQEUT5GGXicD1Uggc9SZiqEKzUkKMh7VwdPdyL3RtUry8dShG8mDbt6onQcqx6MOzluz0ncQ7cUj9KW8Bo4YCnFEO4Tz4-hSm-4fG051VUsqQzI9hPI2eh8Brj660O-QGBY_ZqI471SJhIJ-_wBvWLhok5qT5acvgU9stDjbyyWXVObw13lGVf0dlzRJQsmWuKrUJHBotj2gHZTpWorIgHAmS5cztNp8KUEgGNwsm8ZBadx4psNQXhfZgoal0EUQQ0a6M4BphXQ1rmBVyAx6ewqwNCpIWMc396qXjGI6fKTVi6ZIcdUEtM-mjxEsrVqxSaN-pPFoViQSmRbl7f6k8Hng-Leb5Y18xDozFOA1XwWC7XTEAaQYikGQxnE7B9bRaTDAhCnbLpkKiIbYWMxZFnr0_tEzGj3hp90auWhC89K0iHgXNxLFxLZt4ntb2sS8VmKRApFxd5fMJ78fVuecgoUm0fr0R6AV14FelD6v-5kFya8ynJ4qydBGbIAhcNlssXFmErRSp0_gqlYjIgxBXOq0CZ2bfHmwe2RjTEnIy44WhtVOWUH8c30ThwvRBsEzEZgOpL2lhITTQtqho92G4IZaZbOKlAN_dVuU0Ibo-f6WuhuLG-ZuVx2Gr62PuzVcZI"

    {
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjcyMWIzNzAwMGU4MjRhNjE5YjRjNjBkYTY1ODdjOGEzIiwicGsiOiIwMDA2MTRlMmRmYWE4ZTg4ODZjNjE3ZGRmNWY3NTc3YyIsImlwIjoiOTQuMTgxLjIyLjUxIiwiZXhwaXJlIjoxNzE0NzQ2MjIxLjg2M30.t0VKgHjgY5C8YYzUf85vg3KPlM5jX8tF2SwjhQ7YP97SuSmrKM3fZc3WHzSKlzj7tMujNgkgE9a6XyhMHhx9_zxA1xX6iEUOJTpTYUnmnT4yCkWxpYWJoS6obQmcqkCfJw_kVL0GuG5NM4kGYhs_m7oMMjoFafv6xKvDOanhc1LWqzIQFGTbUT7liKx7soNKHDNElJ1jX80logMyfAwoR6Og5Jdvvgh9nRmuS8-rjIzBe0YM1mUkGJrUXsjG8F2Bm12_mGH8n25J0y8eCXjbiAVBGMeunBNybpSByAzF7v2sHEvWe5TmBxwm6zX1n9qMCer4gwn1ii3YFzkC1ESKj-UFBZRLYtiZav978VdBzHFwrTNUl0u1o1AgSO7USp6SENUw_r56bRpFr4EMuHUz6b9xkdtBVmjGW5rVSdEEQjs3iSUYWhMlJDteyfyRf8zisUV2ZlUY3GfmbH3fEYZLq1nHAdj2bw2Urp-IfmFJnHiJJezSMXV7WkBMPUTvVKuB2GXAXuc4KfQvt-5lCfOnO5FGztXAJAqQpbkkHDPLX0CjCKDf9bnvbM_6tv9qt60bHC7LNLAgS4KT7QojBeQzYeRxhiDzfoCYQPFYTP5H0SVEV6rCAyRNhFPHfJRjVO-AFyLlsGR5NwBJHMMrpN5fGAjLiRQMCyQxakvAfDMPBsc",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjExZDMxY2ZlYzdlMTQ0NmI5YTQ3M2IwMzZiZjE4ZWNhIiwiYWNjZXNzX2lkIjoiNzIxYjM3MDAwZTgyNGE2MTliNGM2MGRhNjU4N2M4YTMiLCJleHBpcmUiOjE3MTk5MzAyMjEuODYzfQ.sUQtq4vs-3GkoVDbq7BFDRwUSi5oqw7n63GELiGdXSJ_ljG0s0MC7_9EZghrA3-uF3OhXYVNNmUPXQ5nm2mzQ51yN-ReLIgKtATckNE3-y6uwo5hP2oVDHTGUj129O00JNwWqHw4x7BUqJ_jBCda0W6GhJkdeyfqFmKrL7oP4-HWVUfZFWKZS7RbFEmJ7EOthuUHP07cAvZtP9aT1VHGk2jhYwYJfH63YPzwxx_g4XenTgk-iDZmEY7_6rIEx6Zo4OWgeRYS1jQxcjvXGqDuofew7hf3mc-lSBhHtr8de-qX_VjEyV6Gqrhw6MEypJAU7Y95qiVdlBM1X1f3HIlzzVIr-kH-l8kw1wQPEWsojsLKRZJhoyxMdUZIBTnXUzk80jVLKQXvDuJQVaqY4p6JCLhtZSXDTzpC9rYstJFmtztlX6cslun72yhup_UH1g_Q_yLzVqRDakoY_G_YrtEzHxxDakhrr2pKmCScqOwG39LLO0FQz76VsNU4hkRSIo4-xoGA3GqIgbw-LMR6ItZeuFE8cWDfLKpEstBnqeIp-IGwsJ_JXZe30aVFQ8WaVwdI8jJg8DoReKMpwu4ASxJaPr9cv99Snqn2skdu8kHfOW4w6zwsmKGxdLRnUqSk6rawxdyTgBPUvguVr4ZN9wr692W5CSH_g5t5V07Vm26mZ0w"
}
}*/

}
