package com.wesleyhome.stats.feed.request.api.credentials;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;

public class EnvironmentCredentials implements ApiCredentials {


    @Override
    public String getApiToken() {
        return System.getenv("MYSPORTSFEED_API_TOKEN");
    }

    @Override
    public String getPassword() {
        return System.getenv("MYSPORTSFEED_API_PASSWORD");
    }
}
