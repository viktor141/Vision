package com.cifrazia.custommainmenu;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(modid = CustomMainMenu.MOD_ID, name = CustomMainMenu.MOD_NAME, version = CustomMainMenu.VERSION)
public class CustomMainMenu {

    public static final String MOD_ID = "custom_main_menu";
    public static final String MOD_NAME = "Custom Main Menu";
    public static final String VERSION = "0.0.1";

    public static final int SIZE_OF_TEXTURE_KIT = 512 >> 1;
    public static final ResourceLocation SERVER_KIT = new ResourceLocation(CustomMainMenu.MOD_ID, "textures/gui/server_enter_kit.png");
    public static final ResourceLocation IN_GAME_KIT = new ResourceLocation(CustomMainMenu.MOD_ID, "textures/gui/esc_menu_kit.png");
    public static final ResourceLocation NAVIGATION_SHOP_KIT = new ResourceLocation(CustomMainMenu.MOD_ID, "textures/gui/shop_items_kit.png");
    public static final ResourceLocation STOCK_AND_BUNK_KIT = new ResourceLocation(CustomMainMenu.MOD_ID, "textures/gui/stock_and_bank_kit.png");
    public static final ResourceLocation DONATION_KIT = new ResourceLocation(CustomMainMenu.MOD_ID, "textures/gui/donations_kit.png");

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SweetMixinListener());
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    }
}
