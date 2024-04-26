package com.cifrazia.vision.connection.auth;

import com.cifrazia.vision.connection.data.ModPack;
import com.cifrazia.vision.connection.data.ShopData;
import com.cifrazia.vision.connection.data.element.shop.ShopCategory;
import com.cifrazia.vision.connection.data.element.shop.ShopItem;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


@SideOnly(Side.CLIENT)
public class AuthorizedClient extends Authorized {
    private String accessToken;
    private String refreshToken;
    private final ShopData shopData;

    public AuthorizedClient() {
        for (int i = 0; i < argumentList.size(); i++) {
            if (argumentList.get(i).equals("--accessToken") && argumentList.size() > (i + 1)) {
                HashMap<String, String> tokens = gson.fromJson(argumentList.get(i + 1), HashMap.class);

                accessToken = tokens.get("access_token");
                refreshToken = tokens.get("refresh_token");
            } else if (argumentList.get(i).equals("--modpack") && argumentList.size() > (i + 1)) {
                modpack = gson.fromJson(argumentList.get(i + 1), ModPack.class);
            }
        }
        shopData = new ShopData(this);
    }


    protected String makeRequest(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Access-Token", accessToken);
            connection.setRequestProperty("X-Refresh-Token", refreshToken);

            return response(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<ShopItem> getAllShopList() {
        List<ShopItem> items = gson.fromJson(
                makeRequest(cifraziaEndPoint + "/minecraft/shop/items/" + getModPakParam()),
                new TypeToken<List<ShopItem>>() {}.getType());

        return items;
    }

    public List<ShopCategory> getShopCategory() {
        List<ShopCategory> categories = gson.fromJson(
                makeRequest(cifraziaEndPoint + "/minecraft/shop/categories/" + getModPakParam()),
                new TypeToken<List<ShopCategory>>() {}.getType());

        return categories;
    }

    public List<ShopItem> getCategoryShopList(ShopCategory category) {// /minecraft/shop/categories/183/items/?modpack_id=17
        List<ShopItem> items = gson.fromJson(
                makeRequest(cifraziaEndPoint + "/minecraft/shop/categories/" + category.getId() + "/items/" + getModPakParam()),
                new TypeToken<List<ShopItem>>() {}.getType());

        return items;
    }


    public ShopData getShopData() {
        return shopData;
    }

}
