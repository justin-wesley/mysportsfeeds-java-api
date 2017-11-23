package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.GameStatusBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PagingBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamBuilderPlugin;

public final class FullGameScheduleBuilder extends RequestBuilder<FullGameScheduleBuilder> {
    public static final String FEED_NAME = "full_game_schedule";
    private final TeamBuilderPlugin<FullGameScheduleBuilder> teams;
    private final DateBuilderPlugin<FullGameScheduleBuilder> date;
    private final GameStatusBuilderPlugin<FullGameScheduleBuilder> statuses;
    private final PagingBuilderPlugin<FullGameScheduleBuilder> limit;

    FullGameScheduleBuilder() {
        super(FEED_NAME);
        plugin(
                this.teams = new TeamBuilderPlugin<>(this),
                this.date = new DateBuilderPlugin<>(this, "date"),
                this.statuses = new GameStatusBuilderPlugin<>(this),
                this.limit = new PagingBuilderPlugin<>(this)
        );
    }

    public TeamBuilderPlugin<FullGameScheduleBuilder> team() {
        return teams;
    }

    public DateBuilderPlugin<FullGameScheduleBuilder> date() {
        return date;
    }

    public GameStatusBuilderPlugin<FullGameScheduleBuilder> status() {
        return statuses;
    }

    public PagingBuilderPlugin<FullGameScheduleBuilder> limit() {
        return limit;
    }
}
