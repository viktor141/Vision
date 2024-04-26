package com.cifrazia.vision.mixin;

import com.cifrazia.vision.menu.InGameMenu;
import com.cifrazia.vision.menu.PressMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
@Mixin(Minecraft.class)
public abstract class MixinMinecraft {


    @Shadow
    public abstract void displayGuiScreen(@Nullable GuiScreen guiScreenIn);

    @Inject(method = "displayGuiScreen", at = @At(value = "HEAD"), cancellable = true)
    public void displayGuiScreen(GuiScreen guiScreenIn, CallbackInfo ci) {
        if (guiScreenIn == null) return;

        if (guiScreenIn instanceof GuiMainMenu) {
            ci.cancel();
            displayGuiScreen(new PressMenu());
        }
        if(guiScreenIn instanceof GuiIngameMenu){
            ci.cancel();
            displayGuiScreen(new InGameMenu());
        }
    }

}
