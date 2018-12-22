package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class TeamsBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public TeamsBuilderPlugin(B builder) {
        super(builder, "teams");
    }
}
