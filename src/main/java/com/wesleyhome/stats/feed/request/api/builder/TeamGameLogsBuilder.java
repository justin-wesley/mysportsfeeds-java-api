package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamStatsBuilderPlugin;

public class TeamGameLogsBuilder extends GameLogsBuilder<TeamGameLogsBuilder> {

    public static final String FEED_NAME = "team_gamelogs";
    private final TeamStatsBuilderPlugin<TeamGameLogsBuilder> teamstats;

    public TeamGameLogsBuilder() {
        super(FEED_NAME);
        plugin(
                this.teamstats = new TeamStatsBuilderPlugin<>(this)
        );
    }

    public TeamStatsBuilderPlugin<TeamGameLogsBuilder> teamstats() {
        return teamstats;
    }
}
