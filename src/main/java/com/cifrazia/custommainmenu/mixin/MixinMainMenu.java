package com.cifrazia.custommainmenu.mixin;


import com.cifrazia.custommainmenu.GuiSec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Calendar;
import java.util.Date;

@Mixin(GuiMainMenu.class)
public abstract class MixinMainMenu extends GuiScreen{





    /*private int x = 10;



    @Shadow private String splashText;
    @Shadow private DynamicTexture viewportTexture;
    @Shadow private final Object threadLock = new Object();
    @Shadow private int openGLWarning2Width;
    @Shadow private int openGLWarning1Width;
    @Shadow private int openGLWarningX1;
    @Shadow private int openGLWarningY1;
    @Shadow private int openGLWarningX2;
    @Shadow private int openGLWarningY2;
    @Shadow private String openGLWarning1;
    @Shadow private String openGLWarning2;
    @Shadow private ResourceLocation backgroundTexture;
    @Shadow private boolean hasCheckedForRealmsNotification;
    @Shadow private GuiScreen realmsNotification;
    @Shadow private int widthCopyright;
    @Shadow private int widthCopyrightRest;
    @Shadow private GuiButton modButton;
    @Shadow private net.minecraftforge.client.gui.NotificationModUpdateScreen modUpdateNotification;
    @Shadow private GuiButton realmsButton;

    @Shadow protected abstract void addDemoButtons(int p_73972_1_, int p_73972_2_);
    //@Shadow protected abstract void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_);
    @Shadow protected abstract boolean areRealmsNotificationsEnabled();

    @ModifyArg(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V"), index = 1)
    private String injectedd(String s) {
        return "Huesos";
    }

    *//**
     * @author
     * @reason
     *//*




    *//**
     * @author
     * @reason
     *//*
    @Overwrite
    public void initGui()
    {
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        this.widthCopyright = this.fontRenderer.getStringWidth("Copyright Mojang AB. Do not distribute!");
        this.widthCopyrightRest = this.width - this.widthCopyright - 2;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        if (calendar.get(2) + 1 == 12 && calendar.get(5) == 24)
        {
            this.splashText = "Merry X-mas!";
        }
        else if (calendar.get(2) + 1 == 1 && calendar.get(5) == 1)
        {
            this.splashText = "Happy new year!";
        }
        else if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31)
        {
            this.splashText = "OOoooOOOoooo! Spooky!";
        }

        int i = 24;
        int j = this.height / 4 + 48;

        if (this.mc.isDemo())
        {
            this.addDemoButtons(j, 24);
        }
        else
        {
            this.addSingleplayerMultiplayerButtons(j, 24);
        }

        this.buttonList.add(new GuiButton(0, x, j + 72 + 12, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(4, x, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
        this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, j + 72 + 12));

        synchronized (this.threadLock)
        {
            this.openGLWarning1Width = this.fontRenderer.getStringWidth(this.openGLWarning1);
            this.openGLWarning2Width = this.fontRenderer.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.openGLWarning1Width, this.openGLWarning2Width);
            this.openGLWarningX1 = (this.width - k) / 2;
            this.openGLWarningY1 = (this.buttonList.get(0)).y - 24;
            this.openGLWarningX2 = this.openGLWarningX1 + k;
            this.openGLWarningY2 = this.openGLWarningY1 + 24;
        }

        this.mc.setConnectedToRealms(false);

        if (Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && !this.hasCheckedForRealmsNotification)
        {
            RealmsBridge realmsbridge = new RealmsBridge();
            this.realmsNotification = realmsbridge.getNotificationScreen(this);
            this.hasCheckedForRealmsNotification = true;
        }

        if (this.areRealmsNotificationsEnabled())
        {
            this.realmsNotification.setGuiSize(this.width, this.height);
            this.realmsNotification.initGui();
        }
        modUpdateNotification = net.minecraftforge.client.gui.NotificationModUpdateScreen.init((GuiMainMenu)(Object)this, modButton);
    }

    *//**
     * @author
     * @reason
     *//*
    @Overwrite
    private void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_)
    {
        this.buttonList.add(new GuiButton(1, x, p_73969_1_, I18n.format("menu.singleplayer")));
        this.buttonList.add(new GuiButton(2, x, p_73969_1_ + p_73969_2_ * 1, I18n.format("menu.multiplayer")));
        this.realmsButton = this.addButton(new GuiButton(14, x, p_73969_1_ + p_73969_2_ * 2, 98, 20, I18n.format("menu.online").replace("Minecraft", "").trim()));
        this.buttonList.add(modButton = new GuiButton(6, x, p_73969_1_ + p_73969_2_ * 2, 98, 20, I18n.format("fml.menu.mods")));
    }*/



}
