package com.cifrazia.vision.connection.data.element.warehouse;

public class WarehouseUserRequest {
    private String user_uuid;
    private int[] items;

    public WarehouseUserRequest(String user_uuid, int[] items) {
        this.user_uuid = user_uuid;
        this.items = items;
    }
}
