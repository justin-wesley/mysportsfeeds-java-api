package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateBuilderPlugin;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public final class DailyPlayerStatsBuilder extends PlayerStatsBuilder<DailyPlayerStatsBuilder> {
    public static final String FEED_NAME = "daily_player_stats";
    private DateBuilderPlugin<DailyPlayerStatsBuilder> forDate;

    public DailyPlayerStatsBuilder() {
        super(FEED_NAME);
        plugin(
                this.forDate = new DateBuilderPlugin<>(this)
        );
    }

    public DailyPlayerStatsBuilder forDate(ChronoLocalDate forDate) {
        return this.forDate.onDate(forDate == null ? LocalDate.now() : forDate);
    }

}
