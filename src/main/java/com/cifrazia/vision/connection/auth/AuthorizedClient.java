package com.cifrazia.vision.connection.auth;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.*;
import com.cifrazia.vision.connection.data.element.CifraziaUser;
import com.cifrazia.vision.connection.data.element.ModPack;
import com.cifrazia.vision.connection.data.element.privilege.Kit;
import com.cifrazia.vision.connection.data.element.privilege.RawPrivileges;
import com.cifrazia.vision.connection.data.element.server.Server;
import com.cifrazia.vision.connection.data.element.shop.ShopCategory;
import com.cifrazia.vision.connection.data.element.shop.ShopItem;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItem;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
    private final PlayerCifraziaData playerCifraziaData;
    private final KitsData kitsData;
    private final UserData userData;

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
        playerCifraziaData = new PlayerCifraziaData(this);
        kitsData = new KitsData(this);
        userData = new UserData(this);
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

    public RawPrivileges getPrivileges() {
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/perms/roles/" + getModPakParam()),
                        new TypeToken<RawPrivileges>() {
                        }.getType()

                ),
                RawPrivileges.class
        );
    }

    public List<Kit> getKits() {
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/minecraft/shop/kits/" + getModPakParam()),
                        new TypeToken<List<Kit>>() {
                        }.getType()
                )
        );
    }

    public CifraziaUser getCifraziaUser() {
        return assignNotNull(
                gson.fromJson(
                        makeRequest(cifraziaEndPoint + "/user/"),
                        new TypeToken<CifraziaUser>() {
                        }.getType()
                ),
                CifraziaUser.class
        );
    }

    public BufferedImage downloadSkin(String url) {
        BufferedImage skinImage = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Access-Token", accessToken);
            connection.setRequestProperty("X-Refresh-Token", refreshToken);

            InputStream inputStream = connection.getInputStream();
            skinImage = ImageIO.read(inputStream);
            inputStream.close();

        } catch (Exception e) {
            Vision.getInstance().logger.warn("Skin can't be downloaded");
        }

        if(skinImage == null){
            Vision.getInstance().logger.info("Skin was null");
        }
        return skinImage;
    }

    public ShopData getShopData() {
        return shopData;
    }

    public PrivilegeData getPrivilegeData() {
        return privilegeData;
    }

    public WarehouseData getWarehouseData() {
        return warehouseData;
    }

    public ServerData getServerData() {
        return serverData;
    }

    public PlayerCifraziaData getPlayerCifraziaData() {
        return playerCifraziaData;
    }

    public KitsData getKitsData() {
        return kitsData;
    }

    public UserData getUserData() {
        return userData;
    }
}
