package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.*;

abstract class TeamStandingsBuilder<B extends TeamStandingsBuilder<B>> extends RequestBuilder<B> {
    private final TeamsBuilderPlugin<B> teams;
    private final DivisionsBuilderPlugin<B> division;
    private final TeamStatsBuilderPlugin<B> teamstats;
    private final PagingBuilderPlugin<B> paging;

    protected TeamStandingsBuilder(String feedName) {
        super(feedName);
        plugin(
                this.teams = new TeamsBuilderPlugin<>(SELF),
                this.division = new DivisionsBuilderPlugin<>(SELF),
                this.teamstats = new TeamStatsBuilderPlugin<>(SELF),
                this.paging = new PagingBuilderPlugin<>(SELF)
        );
    }

    public TeamsBuilderPlugin<B> teams() {
        return teams;
    }

    public DivisionsBuilderPlugin<B> divisions() {
        return division;
    }

    public TeamStatsBuilderPlugin<B> teamstats() {
        return teamstats;
    }

    public PagingBuilderPlugin<B> paging() {
        return paging;
    }
}
