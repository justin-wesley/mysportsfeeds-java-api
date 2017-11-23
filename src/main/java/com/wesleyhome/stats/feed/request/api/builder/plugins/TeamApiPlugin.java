package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class TeamApiPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public TeamApiPlugin(B builder) {
        super(builder, "team");
    }
}
