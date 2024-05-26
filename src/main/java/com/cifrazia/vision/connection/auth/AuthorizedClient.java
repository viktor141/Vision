package com.cifrazia.vision.connection.auth;

import com.cifrazia.vision.connection.data.PrivilegeData;
import com.cifrazia.vision.connection.data.element.ModPack;
import com.cifrazia.vision.connection.data.ServerData;
import com.cifrazia.vision.connection.data.ShopData;
import com.cifrazia.vision.connection.data.WarehouseData;
import com.cifrazia.vision.connection.data.element.privilege.PrivilegeRole;
import com.cifrazia.vision.connection.data.element.server.Server;
import com.cifrazia.vision.connection.data.element.shop.ShopCategory;
import com.cifrazia.vision.connection.data.element.shop.ShopItem;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItem;
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
    private final WarehouseData warehouseData;
    private final ServerData serverData;
    private final PrivilegeData privilegeData;

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
        warehouseData = new WarehouseData(this);
        serverData = new ServerData(this);
        privilegeData = new PrivilegeData(this);
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
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/shop/items/" + getModPakParam()),
                        new TypeToken<List<ShopItem>>() {
                        }.getType()
                )
        );
    }

    public List<ShopCategory> getShopCategory() {
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/shop/categories/" + getModPakParam()),
                        new TypeToken<List<ShopCategory>>() {
                        }.getType()
                )
        );
    }

    public List<ShopItem> getCategoryShopList(ShopCategory category) {// /minecraft/shop/categories/183/items/?modpack_id=17
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/shop/categories/" + category.getId() + "/items/" + getModPakParam()),
                        new TypeToken<List<ShopItem>>() {
                        }.getType()
                )
        );
    }

    public List<WarehouseItem> getWarehouseItems() {
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/shop/user/storage/" + getServerId()),
                        new TypeToken<List<WarehouseItem>>() {
                        }.getType()
                )
        );
    }

    public List<Server> getServers() {
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/modpacks/" + modpack.getId() + "/servers"),
                        new TypeToken<List<Server>>() {
                        }.getType()
                )
        );
    }

    public List<PrivilegeRole> getPrivilegeList() {
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/perms/roles/" + getModPakParam()),
                        new TypeToken<List<PrivilegeRole>>() {
                        }.getType()
                )
        );
    }

    public ShopData getShopData() {
        return shopData;
    }

    public WarehouseData getWarehouseData() {
        return warehouseData;
    }

    public ServerData getServerData() {
        return serverData;
    }
}
