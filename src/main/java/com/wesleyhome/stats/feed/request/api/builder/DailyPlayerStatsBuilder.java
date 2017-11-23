package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateApiPlugin;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public final class DailyPlayerStatsBuilder extends PlayerStatsBuilder<DailyPlayerStatsBuilder> {
    public static final String FEED_NAME = "daily_player_stats";
    private DateApiPlugin<DailyPlayerStatsBuilder> forDate;

    public DailyPlayerStatsBuilder() {
        super(FEED_NAME);
        plugin(
                this.forDate = new DateApiPlugin<>(this)
        );
    }

    public DailyPlayerStatsBuilder forDate(ChronoLocalDate forDate) {
        return this.forDate.onDate(forDate == null ? LocalDate.now() : forDate);
    }

}
