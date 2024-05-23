package com.cifrazia.vision.connection.data.element.shop;

import com.cifrazia.vision.core.ui.util.NBTJsonUtil;
import net.minecraft.item.ItemStack;


public class ShopTradeOffer {
    private final ShopItem shopItem;
    private final ItemStack itemStack;

    public ShopTradeOffer(ShopItem shopItem) {
        this.shopItem = shopItem;

        try {
            this.itemStack = NBTJsonUtil.getItemStack(
                    shopItem.getMaterialName(),
                    shopItem.getCount(),
                    shopItem.getDamage(),
                    shopItem.getNbt());
        } catch (NBTJsonUtil.JsonException e) {
            throw new RuntimeException(e);
        }
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ShopItem getShopItem() {
        return shopItem;
    }
}
