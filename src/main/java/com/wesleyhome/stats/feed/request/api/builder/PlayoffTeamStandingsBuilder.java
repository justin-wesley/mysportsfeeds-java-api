package com.wesleyhome.stats.feed.request.api.builder;

public class PlayoffTeamStandingsBuilder extends TeamStandingsBuilder<PlayoffTeamStandingsBuilder> {

    public static final String FEED_NAME = "playoff_team_standings";

    public PlayoffTeamStandingsBuilder() {
        super(FEED_NAME);
    }
}
