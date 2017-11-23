package com.wesleyhome.stats.feed.request.api.builder;

public final class CumulativePlayerStatsBuilder extends PlayerStatsBuilder<CumulativePlayerStatsBuilder> {

    public static final String FEED_NAME = "cumulative_player_stats";

    CumulativePlayerStatsBuilder() {
        super(FEED_NAME);
    }
}
