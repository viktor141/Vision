package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.CifraziaUser;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

import static com.cifrazia.vision.Vision.interval;

public class UserData extends DataAuthClient {
    private CifraziaUser cifraziaUser;
    private BufferedImage skin;

    public UserData(AuthorizedClient service) {
        super(service);

        update();
    }

    public CifraziaUser getCifraziaUser() {
        if (lastUpdateTime < System.currentTimeMillis() - interval) {
            update();
        }
        return cifraziaUser;
    }

    private CifraziaUser load() {
        return service.getCifraziaUser();
    }

    private void update() {
        CompletableFuture.supplyAsync(()-> {
            CifraziaUser user;
            synchronized (this){
                if (lastUpdateTime < System.currentTimeMillis() - interval) {
                    lastUpdateTime = System.currentTimeMillis();
                    user = load();
                } else {
                    user = cifraziaUser;
                }
                return user;
            }
        }).thenAccept((data) -> {
            cifraziaUser = data;
            CompletableFuture.supplyAsync(() -> service.downloadSkin(cifraziaUser.getSkin())).thenAccept((loadedSkin) ->{
                skin = loadedSkin;
            });
        });
    }

    public BufferedImage getSkin() {
        return skin;
    }
}
