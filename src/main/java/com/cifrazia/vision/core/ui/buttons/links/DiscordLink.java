package com.cifrazia.vision.core.ui.buttons.links;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.SquareButton;

import java.net.URI;

public class DiscordLink extends SquareButton {


    public DiscordLink(Gui parentGUI, int x, int y) {
        super(parentGUI, x, y, 0);

        texturePosX = 190 >> 1;
    }

    @Override
    public void onClick() {
        try
        {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop").invoke(null);
            oclass.getMethod("browse", URI.class).invoke(object, new URI("https://discord.com/"));
        }
        catch (Throwable throwable)
        {
            System.out.println(throwable.fillInStackTrace().getLocalizedMessage());
        }
    }
}
