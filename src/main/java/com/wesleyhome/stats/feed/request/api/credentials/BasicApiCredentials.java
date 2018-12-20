package com.wesleyhome.stats.feed.request.api.credentials;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;

public class BasicApiCredentials implements ApiCredentials {

    private final String username;
    private final String password;

    public BasicApiCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getApiToken() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
