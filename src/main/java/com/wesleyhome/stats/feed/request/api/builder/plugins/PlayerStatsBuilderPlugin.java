package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PlayerStatsBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public PlayerStatsBuilderPlugin(B builder) {
        super(builder, "playerstats");
    }
}
