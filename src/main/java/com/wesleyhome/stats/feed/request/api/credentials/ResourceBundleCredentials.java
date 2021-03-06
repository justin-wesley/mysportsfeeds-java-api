package com.wesleyhome.stats.feed.request.api.credentials;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;

import java.util.ResourceBundle;

public class ResourceBundleCredentials implements ApiCredentials {

    private final String username;
    private final String password;

    public ResourceBundleCredentials(String resourceName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceName);
        username = resourceBundle.getString("username");
        password = resourceBundle.getString("password");
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
