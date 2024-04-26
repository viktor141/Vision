package com.cifrazia.vision.connection.auth;


import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.ModPack;
import com.google.gson.Gson;
import net.minecraft.launchwrapper.Launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Authorized {
    protected static final String cifraziaEndPoint = "https://minecraft.cifrazia.com";
    protected final List<String> argumentList = (List<String>) Launch.blackboard.get("ArgumentList");
    protected final Gson gson = new Gson();
    protected ModPack modpack;

    public Authorized() {

    }

    protected static String getParamsString(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        Iterator<Entry<String, String>> entries = params.entrySet().iterator();

        while (entries.hasNext()) {
            Entry<String, String> entry = entries.next();
            result.append("?" + entry.getKey() + "=" + entry.getValue());

            if (entries.hasNext()) {
                result.append("&");
            }
        }

        return result.toString();
    }

    protected String getModPakParam() {//?modpack_id=17
        HashMap<String, String> params = new HashMap<>();
        params.put("modpack_id", String.valueOf(modpack.getId()));

        return getParamsString(params);
    }


    protected String response(HttpURLConnection connection) {
        try {
            int responseCode = connection.getResponseCode();
            Vision.getInstance().logger.info("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                return response.toString();
            } else {
                Vision.getInstance().logger.error("Error while request: {}", connection.getURL());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
