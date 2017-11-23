package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;

public interface ApiParameterPlugin {
    void buildRequest(DefaultApiRequest request);
}
