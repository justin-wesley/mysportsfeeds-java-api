package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PlayerBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public PlayerBuilderPlugin(B builder) {
        super(builder, "players");
    }
}
