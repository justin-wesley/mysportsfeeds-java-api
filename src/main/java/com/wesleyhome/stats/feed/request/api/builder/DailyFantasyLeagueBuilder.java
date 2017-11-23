package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.DailyFantasyLeague;
import com.wesleyhome.stats.feed.request.api.builder.plugins.*;

public class DailyFantasyLeagueBuilder extends RequestBuilder<DailyFantasyLeagueBuilder> {

    public static final String FEED_NAME = "daily_dfs";
    private TeamBuilderPlugin<DailyFantasyLeagueBuilder> teams;
    private PlayerBuilderPlugin<DailyFantasyLeagueBuilder> players;
    private PositionsBuilderPlugin<DailyFantasyLeagueBuilder> positions;
    private CountriesBuilderPlugin<DailyFantasyLeagueBuilder> countries;
    private ListManagerPlugin<DailyFantasyLeagueBuilder, DailyFantasyLeague> fantasyLeague;

    public DailyFantasyLeagueBuilder() {
        super(FEED_NAME);
        plugin(
                this.teams = new TeamBuilderPlugin<>(SELF),
                this.players = new PlayerBuilderPlugin<>(SELF),
                this.positions = new PositionsBuilderPlugin<>(SELF),
                this.countries = new CountriesBuilderPlugin<>(SELF),
                this.fantasyLeague = new ListManagerPlugin<>(SELF, "dfstype")
        );
    }

    public TeamBuilderPlugin<DailyFantasyLeagueBuilder> teams() {
        return teams;
    }

    public PlayerBuilderPlugin<DailyFantasyLeagueBuilder> players() {
        return players;
    }

    public PositionsBuilderPlugin<DailyFantasyLeagueBuilder> positions() {
        return positions;
    }

    public CountriesBuilderPlugin<DailyFantasyLeagueBuilder> countries() {
        return countries;
    }

    public ListManagerPlugin<DailyFantasyLeagueBuilder, DailyFantasyLeague> fantasyLeagues() {
        return fantasyLeague;
    }
}
