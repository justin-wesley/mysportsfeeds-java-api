package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.GameStatusBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamBuilderPlugin;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public final class DailyGameScheduleBuilder extends RequestBuilder<DailyGameScheduleBuilder> {
    public static final String FEED_NAME = "daily_game_schedule";
    private final DateBuilderPlugin<DailyGameScheduleBuilder> forDate;
    private final TeamBuilderPlugin<DailyGameScheduleBuilder> teams;

    private final GameStatusBuilderPlugin<DailyGameScheduleBuilder> statuses;

    DailyGameScheduleBuilder() {
        super(FEED_NAME);
        plugin(
                this.forDate = new DateBuilderPlugin<>(this),
                this.teams = new TeamBuilderPlugin<>(this),
                this.statuses = new GameStatusBuilderPlugin<>(this)
        );
        onDate(LocalDate.now());
    }

    public TeamBuilderPlugin<DailyGameScheduleBuilder> team() {
        return teams;
    }

    public GameStatusBuilderPlugin<DailyGameScheduleBuilder> status() {
        return statuses;
    }

    public DailyGameScheduleBuilder onDate(ChronoLocalDate localDate) {
        return this.forDate.onDate(localDate);
    }
}
