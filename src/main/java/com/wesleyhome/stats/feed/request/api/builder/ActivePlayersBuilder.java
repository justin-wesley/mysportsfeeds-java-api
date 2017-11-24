package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.*;

public final class ActivePlayersBuilder extends RequestBuilder<ActivePlayersBuilder> {
    public static final String FEED_NAME = "active_players";
    private final PagingBuilderPlugin<ActivePlayersBuilder> paging;
    private TeamBuilderPlugin<ActivePlayersBuilder> teams;
    private PlayerBuilderPlugin<ActivePlayersBuilder> players;
    private PositionsBuilderPlugin<ActivePlayersBuilder> positions;
    private CountriesBuilderPlugin<ActivePlayersBuilder> countries;

    public ActivePlayersBuilder() {
        super(FEED_NAME);
        plugin(
                this.teams = new TeamBuilderPlugin<>(this),
                this.players = new PlayerBuilderPlugin<>(this),
                this.positions = new PositionsBuilderPlugin<>(this),
                this.countries = new CountriesBuilderPlugin<>(this),
                this.paging = new PagingBuilderPlugin<>(this)
        );
    }

    public TeamBuilderPlugin<ActivePlayersBuilder> teams() {
        return teams;
    }

    public PlayerBuilderPlugin<ActivePlayersBuilder> players() {
        return players;
    }

    public PositionsBuilderPlugin<ActivePlayersBuilder> positions() {
        return positions;
    }

    public CountriesBuilderPlugin<ActivePlayersBuilder> countries() {
        return countries;
    }

    public PagingBuilderPlugin<ActivePlayersBuilder> paging() {
        return paging;
    }
}
