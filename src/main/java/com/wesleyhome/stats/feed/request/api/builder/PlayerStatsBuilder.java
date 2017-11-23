package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.*;

public abstract class PlayerStatsBuilder<B extends PlayerStatsBuilder<B>> extends RequestBuilder<B> {

    private TeamBuilderPlugin<B> teams;
    private PlayerBuilderPlugin<B> players;
    private PositionsBuilderPlugin<B> positions;
    private CountriesBuilderPlugin<B> countries;
    private PlayerStatsBuilderPlugin<B> playerStats;

    protected PlayerStatsBuilder(String feedName) {
        super(feedName);
        plugin(
                this.teams = new TeamBuilderPlugin<>(SELF),
                this.players = new PlayerBuilderPlugin<>(SELF),
                this.positions = new PositionsBuilderPlugin<>(SELF),
                this.countries = new CountriesBuilderPlugin<>(SELF),
                this.playerStats = new PlayerStatsBuilderPlugin<>(SELF)
        );
    }

    public TeamBuilderPlugin<B> team() {
        return teams;
    }

    public CountriesBuilderPlugin<B> countries() {
        return countries;
    }

    public PlayerBuilderPlugin<B> players() {
        return players;
    }

    public PlayerStatsBuilderPlugin<B> playerStats() {
        return playerStats;
    }

    public PositionsBuilderPlugin<B> positions() {
        return positions;
    }
}
