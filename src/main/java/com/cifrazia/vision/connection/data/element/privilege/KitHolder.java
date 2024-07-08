package com.cifrazia.vision.connection.data.element.privilege;

import com.cifrazia.vision.core.ui.util.NBTJsonUtil;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KitHolder {

    private final Kit kit;
    private final List<ItemStack> kitItems;

    public KitHolder(Kit kit) {
        this.kit = kit;

        kitItems = new ArrayList<>(kit.getItems().size());

        try {
            for (Kit.KitItem item : kit.getItems()) {
                kitItems.add(NBTJsonUtil.getItemStack(
                        item.getMaterialName(),
                        item.getCount(),
                        item.getDamage(),
                        item.getNbt()));

            }
        } catch (NBTJsonUtil.JsonException e) {
            throw new RuntimeException(e);
        }
    }

    public Kit getKit() {
        return kit;
    }

    public List<ItemStack> getKitItems() {
        return kitItems;
    }
}
