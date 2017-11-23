package com.wesleyhome.stats.feed.request.api.builder;

public class ConferenceTeamStandingsBuilder extends TeamStandingsBuilder<ConferenceTeamStandingsBuilder> {

    public static final String FEED_NAME = "conference_team_standings";

    public ConferenceTeamStandingsBuilder() {
        super(FEED_NAME);
    }
}
