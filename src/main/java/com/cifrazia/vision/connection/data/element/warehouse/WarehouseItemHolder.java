package com.cifrazia.vision.connection.data.element.warehouse;

import com.cifrazia.vision.core.ui.util.NBTJsonUtil;
import net.minecraft.item.ItemStack;

public class WarehouseItemHolder {
    private final WarehouseItem warehouseItem;
    private final ItemStack itemStack;

    public WarehouseItemHolder(WarehouseItem warehouseItem){
        this.warehouseItem = warehouseItem;

        try {
            this.itemStack = NBTJsonUtil.getItemStack(
                    warehouseItem.getMaterialName(),
                    warehouseItem.getCount(),
                    warehouseItem.getDamage(),
                    warehouseItem.getNbt());
        } catch (NBTJsonUtil.JsonException e) {
            throw new RuntimeException(e);
        }
    }

    public WarehouseItem getWarehouseItem() {
        return warehouseItem;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
