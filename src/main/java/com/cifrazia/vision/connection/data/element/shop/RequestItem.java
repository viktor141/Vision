package com.cifrazia.vision.connection.data.element.shop;

public class RequestItem {
    private int id;
    private int count;

    public RequestItem(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }


}
