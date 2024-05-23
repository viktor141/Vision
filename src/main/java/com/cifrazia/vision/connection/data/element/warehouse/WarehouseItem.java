package com.cifrazia.vision.connection.data.element.warehouse;

public class WarehouseItem {
    private  int id;
    private  int stack_id;
    private  String material_name;
    private  int damage;
    private  String nbt;
    private  int count;

    public int getId() {
        return id;
    }

    public String getMaterialName() {
        return material_name;
    }

    public int getDamage() {
        return damage;
    }

    public int getCount() {
        return count;
    }

    public String getNbt() {
        return nbt;
    }

    public int getStack_id() {
        return stack_id;
    }
}
