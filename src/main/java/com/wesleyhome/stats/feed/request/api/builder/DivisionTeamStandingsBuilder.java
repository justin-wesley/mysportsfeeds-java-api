package com.wesleyhome.stats.feed.request.api.builder;

public class DivisionTeamStandingsBuilder extends TeamStandingsBuilder<DivisionTeamStandingsBuilder> {

    public static final String FEED_NAME = "division_team_standings";

    public DivisionTeamStandingsBuilder() {
        super(FEED_NAME);
    }
}
