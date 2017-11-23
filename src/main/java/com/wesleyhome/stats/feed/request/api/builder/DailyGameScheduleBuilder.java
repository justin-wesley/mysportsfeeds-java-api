package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateApiPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.ForcePlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.GameStatusApiPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamApiPlugin;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public final class DailyGameScheduleBuilder extends RequestBuilder<DailyGameScheduleBuilder> {
    public static final String FEED_NAME = "daily_game_schedule";
    private final ForcePlugin<DailyGameScheduleBuilder> forcePlugin;
    private final DateApiPlugin<DailyGameScheduleBuilder> forDate;
    private final TeamApiPlugin<DailyGameScheduleBuilder> teams;

    private final GameStatusApiPlugin<DailyGameScheduleBuilder> statuses;

    DailyGameScheduleBuilder() {
        super(FEED_NAME);
        plugin(
                this.forcePlugin = new ForcePlugin<>(this),
                this.forDate = new DateApiPlugin<>(this),
                this.teams = new TeamApiPlugin<>(this),
                this.statuses = new GameStatusApiPlugin<>(this)
        );
        onDate(LocalDate.now());
    }

    public DailyGameScheduleBuilder force() {
        return forcePlugin.force(true);
    }

    public TeamApiPlugin<DailyGameScheduleBuilder> team() {
        return teams;
    }

    public GameStatusApiPlugin<DailyGameScheduleBuilder> status() {
        return statuses;
    }

    public DailyGameScheduleBuilder onDate(ChronoLocalDate localDate) {
        return this.forDate.onDate(localDate);
    }
}
