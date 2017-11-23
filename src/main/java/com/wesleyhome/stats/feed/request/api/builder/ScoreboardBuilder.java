package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateApiPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.ForcePlugin;

import java.time.chrono.ChronoLocalDate;

public class ScoreboardBuilder extends RequestBuilder<ScoreboardBuilder> {

    public static final String FEED_NAME = "scoreboard";
    private final ForcePlugin<ScoreboardBuilder> forcePlugin;

    private final DateApiPlugin<ScoreboardBuilder> dateApiPlugin;

    ScoreboardBuilder() {
        super(FEED_NAME);
        plugin(
                dateApiPlugin = new DateApiPlugin<>(this),
                this.forcePlugin = new ForcePlugin<>(this)
        );
    }

    public ScoreboardBuilder onDate(ChronoLocalDate localDate) {
        return dateApiPlugin.onDate(localDate);
    }
}
