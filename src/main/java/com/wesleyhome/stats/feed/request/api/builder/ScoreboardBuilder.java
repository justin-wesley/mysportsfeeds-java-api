package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.GameStatusBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamBuilderPlugin;

import java.time.chrono.ChronoLocalDate;

public class ScoreboardBuilder extends RequestBuilder<ScoreboardBuilder> {

    public static final String FEED_NAME = "scoreboard";
    private final DateBuilderPlugin<ScoreboardBuilder> forDate;
    private final TeamBuilderPlugin<ScoreboardBuilder> team;
    private final GameStatusBuilderPlugin<ScoreboardBuilder> status;

    ScoreboardBuilder() {
        super(FEED_NAME);
        plugin(
                this.forDate = new DateBuilderPlugin<>(this),
                this.team = new TeamBuilderPlugin<>(this),
                this.status = new GameStatusBuilderPlugin<>(this)
        );
    }

    public TeamBuilderPlugin<ScoreboardBuilder> team() {
        return team;
    }

    public ScoreboardBuilder forDate(ChronoLocalDate date) {
        return this.forDate.onDate(date);
    }

    public GameStatusBuilderPlugin<ScoreboardBuilder> status() {
        return status;
    }
}
