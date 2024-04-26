package com.cifrazia.vision.connection.data.element.shop;

import com.cifrazia.vision.core.ui.util.NBTJsonUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class ShopItem {
    private int id;
    private String material_name;
    private int damage;
    private String nbt;
    private int count;
    private byte stack_size;
    private byte discount;
    private long price;
    private String currency;
    private int category_id;

    @Override
    public String toString() {
        return "ShopItem{" +
                "id=" + id +
                ", material_name='" + material_name + '\'' +
                ", damage=" + damage +
                ", nbt='" + nbt + '\'' +
                ", count=" + count +
                ", stack_size=" + stack_size +
                ", discount=" + discount +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", category_id=" + category_id +
                '}';
    }

    public ItemStack getItemStack() throws NBTJsonUtil.JsonException {
        Item item = Item.REGISTRY.getObject(new ResourceLocation(material_name));

        return item != null ? new ItemStack(item, count, damage, (nbt != null) ? NBTJsonUtil.Convert(nbt) : new NBTTagCompound()) : ItemStack.EMPTY;
    }

    public long getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
