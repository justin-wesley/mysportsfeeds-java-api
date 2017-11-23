package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PlayerStatsApiPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public PlayerStatsApiPlugin(B builder) {
        super(builder, "playerstats");
    }
}
