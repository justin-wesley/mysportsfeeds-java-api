package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class TeamStatsBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public TeamStatsBuilderPlugin(B builder) {
        super(builder, "teamstats");
    }
}
