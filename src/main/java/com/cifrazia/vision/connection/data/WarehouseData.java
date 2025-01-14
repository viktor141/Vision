package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItem;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItemHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.cifrazia.vision.Vision.interval;

public class WarehouseData extends DataAuthClient {
    private List<WarehouseItemHolder> dataHolder;
    private boolean forceUpdate;

    public WarehouseData(AuthorizedClient service) {
        super(service);
    }

    public List<WarehouseItemHolder> getItems() {
        if (dataHolder == null || lastUpdateTime < System.currentTimeMillis() - interval) {
            dataHolder = loadItems();
            lastUpdateTime = System.currentTimeMillis();
        }

        return dataHolder;
    }

    public void forceUpdate() {
        CompletableFuture.supplyAsync(this::loadItems).thenAccept((data) ->{
            dataHolder = data;
            lastUpdateTime = System.currentTimeMillis();
            forceUpdate = true;
        });
    }

    private List<WarehouseItemHolder> loadItems() {
        List<WarehouseItem> warehouseItems = service.getWarehouseItems();

        List<WarehouseItemHolder> itemsHolder = new ArrayList<>(warehouseItems.size());

        for (WarehouseItem item : warehouseItems) {
            itemsHolder.add(new WarehouseItemHolder(item));
        }

        return itemsHolder;
    }

    public boolean isForceUpdated() {
        return forceUpdate;
    }

    public void setUpdated(){
        forceUpdate = false;
    }
}
