package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class TeamStatsApiPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public TeamStatsApiPlugin(B builder) {
        super(builder, "teamstats");
    }
}
