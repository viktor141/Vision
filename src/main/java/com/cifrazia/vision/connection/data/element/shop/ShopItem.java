package com.cifrazia.vision.connection.data.element.shop;

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

    public long getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getMaterialName() {
        return material_name;
    }

    public int getDamage() {
        return damage;
    }

    public String getNbt() {
        return nbt;
    }
}
