package com.cifrazia.vision.connection.data.request;

import com.cifrazia.vision.connection.data.element.shop.RequestItem;

public class BuyItemRequest {
    private final String user_uuid;
    private final RequestItem[] items;

    public BuyItemRequest(String user_uuid, RequestItem[] items) {
        this.user_uuid = user_uuid;
        this.items = items;
    }
}
