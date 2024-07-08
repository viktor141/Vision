package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.privilege.Kit;
import com.cifrazia.vision.connection.data.element.privilege.KitHolder;

import java.util.HashMap;

import static com.cifrazia.vision.Vision.interval;

public class KitsData extends DataAuthClient {

    private HashMap<String, KitHolder> kitsHolder;

    public KitsData(AuthorizedClient service) {
        super(service);
    }

    public HashMap<String, KitHolder> getKits() {
        if (kitsHolder == null || lastUpdateTime < System.currentTimeMillis() - interval) {
            kitsHolder = loadKits();
            lastUpdateTime = System.currentTimeMillis();
        }
        return kitsHolder;
    }

    private HashMap<String, KitHolder> loadKits() {
        HashMap<String, KitHolder> kits = new HashMap<>();

        for (Kit kit : service.getKits()) {
            kits.put(kit.getPermission().getCode_name(), new KitHolder(kit));
        }

        return kits;
    }
}
