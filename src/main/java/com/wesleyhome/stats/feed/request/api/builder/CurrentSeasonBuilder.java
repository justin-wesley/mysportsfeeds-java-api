package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateBuilderPlugin;

import java.time.chrono.ChronoLocalDate;

public final class CurrentSeasonBuilder extends RequestBuilder<CurrentSeasonBuilder> {

    public static final String FEED_NAME = "current_season";
    private DateBuilderPlugin<CurrentSeasonBuilder> forDate;


    CurrentSeasonBuilder() {
        super(FEED_NAME);
        plugin(forDate = new DateBuilderPlugin<>(this));
    }


    public CurrentSeasonBuilder forDate(ChronoLocalDate forDate) {
        return this.forDate.onDate(forDate);
    }

}
