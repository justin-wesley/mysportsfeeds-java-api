package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.*;

import java.time.chrono.ChronoLocalDate;

public class RosterPlayersBuilder extends RequestBuilder<RosterPlayersBuilder> {

    public static final String FEED_NAME = "roster_players";
    private final TeamBuilderPlugin<RosterPlayersBuilder> teams;
    private final PlayerBuilderPlugin<RosterPlayersBuilder> players;
    private final PositionsBuilderPlugin<RosterPlayersBuilder> positions;
    private final PagingBuilderPlugin<RosterPlayersBuilder> paging;
    private final DateBuilderPlugin<RosterPlayersBuilder> date;

    public RosterPlayersBuilder() {
        super(FEED_NAME);
        plugin(
                this.teams = new TeamBuilderPlugin<>(this),
                this.players = new PlayerBuilderPlugin<>(this),
                this.positions = new PositionsBuilderPlugin<>(this),
                this.paging = new PagingBuilderPlugin<>(this),
                this.date = new DateBuilderPlugin<>(this)
        );
    }

    public TeamBuilderPlugin<RosterPlayersBuilder> team() {
        return teams;
    }

    public PlayerBuilderPlugin<RosterPlayersBuilder> players() {
        return players;
    }

    public PositionsBuilderPlugin<RosterPlayersBuilder> positions() {
        return positions;
    }

    public PagingBuilderPlugin<RosterPlayersBuilder> paging() {
        return paging;
    }

    public RosterPlayersBuilder onDate(ChronoLocalDate localDate) {
        return this.date.onDate(localDate);
    }
}
