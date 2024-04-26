package com.cifrazia.vision.core.misc;

import com.cifrazia.vision.connection.data.element.shop.RequestItem;
import com.google.gson.Gson;

public class MessageParser {

    private static Gson gson = new Gson();


    public static String getJsonObject(Object object) {
        return gson.toJson(object);
    }

    public static RequestItem[] getJsonObject(String json) {
        return gson.fromJson(json, RequestItem[].class);
    }
}
