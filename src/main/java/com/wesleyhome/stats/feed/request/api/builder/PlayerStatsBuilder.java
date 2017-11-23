package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.*;

public abstract class PlayerStatsBuilder<B extends PlayerStatsBuilder<B>> extends RequestBuilder<B> {

    private TeamApiPlugin<B> teams;
    private PlayerApiPlugin<B> players;
    private PositionsApiPlugin<B> positions;
    private CountriesApiPlugin<B> countries;
    private PlayerStatsApiPlugin<B> playerStats;

    protected PlayerStatsBuilder(String feedName) {
        super(feedName);
        plugin(
                this.teams = new TeamApiPlugin<>(SELF),
                this.players = new PlayerApiPlugin<>(SELF),
                this.positions = new PositionsApiPlugin<>(SELF),
                this.countries = new CountriesApiPlugin<>(SELF),
                this.playerStats = new PlayerStatsApiPlugin<>(SELF)
        );
    }

    public TeamApiPlugin<B> team() {
        return teams;
    }

    public CountriesApiPlugin<B> countries() {
        return countries;
    }

    public PlayerApiPlugin<B> players() {
        return players;
    }

    public PlayerStatsApiPlugin<B> playerStats() {
        return playerStats;
    }

    public PositionsApiPlugin<B> positions() {
        return positions;
    }
}
