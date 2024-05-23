package com.cifrazia.vision.connection.data.request;

public class WalletUserRequest {
    private String currency;
    private String user_uuid;

    public WalletUserRequest(String currency, String user_uuid) {
        this.currency = currency;
        this.user_uuid = user_uuid;
    }
}
