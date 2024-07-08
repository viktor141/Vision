package com.cifrazia.vision.core.misc;

import com.cifrazia.vision.Vision;

import java.net.URI;

public class LinkBrowser {

    public static void openLink(String url) {
        try {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop").invoke(null);
            oclass.getMethod("browse", URI.class).invoke(object, new URI(url));
        } catch (Throwable throwable) {
            Vision.getInstance().logger.warn(throwable.fillInStackTrace().getLocalizedMessage());
        }
    }
}
