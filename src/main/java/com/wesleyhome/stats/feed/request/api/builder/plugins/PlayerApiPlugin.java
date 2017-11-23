package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PlayerApiPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public PlayerApiPlugin(B builder) {
        super(builder, "players");
    }
}
