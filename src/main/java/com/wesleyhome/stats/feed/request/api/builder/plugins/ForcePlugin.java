package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class ForcePlugin<B extends RequestBuilder<B>> implements ApiParameterPlugin {
    private final B builder;
    private Boolean force;

    public ForcePlugin(B builder) {
        this.builder = builder;
    }

    public B force(Boolean force) {
        this.force = force;
        return builder;
    }

    @Override
    public void buildRequest(DefaultApiRequest request) {
        request.applyParameter("force", force);
    }
}
