package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;

public interface RequestBuilderPlugin {
    void buildRequest(DefaultApiRequest request);
}
