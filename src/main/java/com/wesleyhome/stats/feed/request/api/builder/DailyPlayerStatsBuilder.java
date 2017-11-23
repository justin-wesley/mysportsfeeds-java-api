package com.wesleyhome.stats.feed.request.api.builder;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import static com.wesleyhome.stats.feed.request.api.DateConverters.onDate;

public final class DailyPlayerStatsBuilder extends PlayerStatsBuilder<DailyPlayerStatsBuilder> {
    public static final String FEED_NAME = "daily_player_stats";
    private ChronoLocalDate forDate = LocalDate.now();

    public DailyPlayerStatsBuilder() {
        super(FEED_NAME);
    }


    public DailyPlayerStatsBuilder forDate(ChronoLocalDate forDate) {
        this.forDate = forDate == null ? LocalDate.now() : forDate;
        return SELF;
    }

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        super.buildRequest(request);
        request.applyParameter("fordate", onDate(this.forDate));
    }
}
