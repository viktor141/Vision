package com.cifrazia.vision.connection.auth;

import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItem;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseUserRequest;
import com.cifrazia.vision.connection.data.request.BuyItemRequest;
import com.cifrazia.vision.connection.data.request.WalletUserRequest;
import com.cifrazia.vision.connection.data.response.UserBalanceResponse;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;

@SideOnly(Side.SERVER)
public class AuthorizedServer extends Authorized {

    private final String signatureKey;
    private final String serverID;

    public AuthorizedServer() {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("cifrazia" + File.separator + "tcp.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        signatureKey = (String) prop.get("tcp.signature.key");
        serverID = (String) prop.get("server.id");
    }


    protected String makePostRequest(String url, String entity) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedSignature = digest.digest(generateSignature().getBytes(StandardCharsets.UTF_8));

            connection.setRequestProperty("X-Server-Signature", DatatypeConverter.printHexBinary(hashedSignature).toLowerCase());
            connection.setRequestProperty("X-Server-Id", serverID);

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            System.out.println(entity);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = entity.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            return response(connection);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    protected String generateSignature() {
        return round(System.currentTimeMillis() / 1000) + serverID + signatureKey;
    }

    private long round(long n) {
        return n - (n % 10);
    }

    public boolean userBuyItems(BuyItemRequest buyItemRequest) {
        return Boolean.parseBoolean(
                makePostRequest(cifraziaEndPoint + "/minecraft/shop/buy-items/", gson.toJson(buyItemRequest))
        );
    }

    public List<WarehouseItem> userWarehouseRetrieve(WarehouseUserRequest request) {
        return assignNotNull(
                gson.fromJson(
                        makePostRequest(cifraziaEndPoint + "/minecraft/shop/user/storage/retrieve/", gson.toJson(request)),
                        new TypeToken<List<WarehouseItem>>() {
                        }.getType()
                )
        );
    }

    public UserBalanceResponse getUserWallet(WalletUserRequest request) {
        return assignNotNull(
                gson.fromJson(
                        makePostRequest(cifraziaEndPoint + "/minecraft/shop/user/wallet/", gson.toJson(request)),
                        new TypeToken<UserBalanceResponse>() {
                        }.getType()),
                UserBalanceResponse.class);
    }

}
