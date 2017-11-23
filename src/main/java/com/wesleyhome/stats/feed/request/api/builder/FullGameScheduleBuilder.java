package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateApiPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.GameStatusApiPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.LimitApiPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamApiPlugin;

public final class FullGameScheduleBuilder extends RequestBuilder<FullGameScheduleBuilder> {
    public static final String FEED_NAME = "full_game_schedule";
    private final TeamApiPlugin<FullGameScheduleBuilder> teams;
    private final DateApiPlugin<FullGameScheduleBuilder> date;
    private final GameStatusApiPlugin<FullGameScheduleBuilder> statuses;
    private final LimitApiPlugin<FullGameScheduleBuilder> limit;

    FullGameScheduleBuilder() {
        super(FEED_NAME);
        plugin(
                this.teams = new TeamApiPlugin<>(this),
                this.date = new DateApiPlugin<>(this),
                this.statuses = new GameStatusApiPlugin<>(this),
                this.limit = new LimitApiPlugin<>(this)
        );
    }

    public TeamApiPlugin<FullGameScheduleBuilder> team() {
        return teams;
    }

    public DateApiPlugin<FullGameScheduleBuilder> date() {
        return date;
    }

    public GameStatusApiPlugin<FullGameScheduleBuilder> status() {
        return statuses;
    }

    public LimitApiPlugin<FullGameScheduleBuilder> limit() {
        return limit;
    }
}
