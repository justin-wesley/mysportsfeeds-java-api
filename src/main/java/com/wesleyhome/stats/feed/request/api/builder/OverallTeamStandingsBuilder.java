package com.wesleyhome.stats.feed.request.api.builder;

public class OverallTeamStandingsBuilder extends TeamStandingsBuilder<OverallTeamStandingsBuilder> {

    public static final String FEED_NAME = "overall_team_standings";

    public OverallTeamStandingsBuilder() {
        super(FEED_NAME);
    }
}
