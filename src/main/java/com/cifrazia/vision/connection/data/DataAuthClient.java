package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.connection.auth.AuthorizedClient;

public abstract class DataAuthClient {
    protected final AuthorizedClient service;
    protected long lastUpdateTime;

    public DataAuthClient(AuthorizedClient service) {
        this.service = service;
    }
}
