package com.cifrazia.vision.connection.auth;

import com.cifrazia.vision.connection.data.BuyItemRequest;
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

    protected String makeRequest(String url, BuyItemRequest buyItemRequest) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");

            String signature = round(System.currentTimeMillis() / 1000) + serverID + signatureKey;

            System.out.println(signature);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedSignature = digest.digest(signature.getBytes(StandardCharsets.UTF_8));

            connection.setRequestProperty("X-Server-Signature", DatatypeConverter.printHexBinary(hashedSignature).toLowerCase());
            connection.setRequestProperty("X-Server-Id", serverID);

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String entity = gson.toJson(buyItemRequest);

            System.out.println(entity);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = entity.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            return response(connection);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private long round(long n){
        return n - (n % 10);
    }

    public boolean userBuyItems(BuyItemRequest buyItemRequest) {
        String resp =  makeRequest(cifraziaEndPoint + "/minecraft/shop/buy-items/", buyItemRequest);
        System.out.println(resp);
        return true;
    }

}