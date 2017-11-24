package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.DateBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.GameListBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PagingBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamBuilderPlugin;

abstract class GameLogsBuilder<B extends GameLogsBuilder<B>> extends RequestBuilder<B> {

    private final TeamBuilderPlugin<B> teams;
    private final GameListBuilderPlugin<B> game;
    private final DateBuilderPlugin<B> date;
    private final PagingBuilderPlugin<B> paging;

    protected GameLogsBuilder(String feedName) {
        super(feedName);
        plugin(
                this.teams = new TeamBuilderPlugin<>(SELF),
                this.game = new GameListBuilderPlugin<>(SELF, "game"),
                this.date = new DateBuilderPlugin<>(SELF),
                this.paging = new PagingBuilderPlugin<>(SELF)
        );
    }

    public TeamBuilderPlugin<B> teams() {
        return teams;
    }

    public GameListBuilderPlugin<B> games() {
        return game;
    }

    public DateBuilderPlugin<B> date() {
        return date;
    }

    public PagingBuilderPlugin<B> paging() {
        return paging;
    }
}
