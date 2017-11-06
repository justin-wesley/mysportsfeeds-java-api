package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiRequest;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGameBoxScoreBuilder<B extends BaseGameBoxScoreBuilder<B>> extends
        RequestBuilder<B> {

    protected List<String> teamstats = new ArrayList<>();
    protected List<String> playerStats = new ArrayList<>();
    protected String sort;
    protected Integer offset;
    protected Integer limit;
    protected Boolean force;

    protected <C extends DefaultApiRequest<T>, T> BaseGameBoxScoreBuilder(Class<C> requestClass) {
        super(requestClass);
    }


    public B teamstats(List<String> teamstats) {
        this.teamstats = teamstats == null ? new ArrayList<>() : teamstats;
        return SELF;
    }

    public B team(String team) {
        this.teamstats.add(team);
        return SELF;
    }


    public B playerStats(List<String> playerStats) {
        this.playerStats = playerStats == null ? new ArrayList<>() : playerStats;
        return SELF;
    }

    public B playerStat(String playerStat) {
        this.playerStats.add(playerStat);
        return SELF;
    }

    public B sort(String sort) {
        this.sort = sort;
        return SELF;
    }

    public B offset(Integer offset) {
        this.offset = offset;
        return SELF;
    }

    public B limit(Integer limit) {
        this.limit = limit;
        return SELF;
    }

    public B force(Boolean force) {
        this.force = force;
        return SELF;
    }

    public final <R> ApiRequest<R> buildRequest(Class<R> responseType) {
        return createRequest(responseType)
                .applyListParameters("teamstats", teamstats)
                .applyListParameters("playerstats", playerStats)
                .applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit)
                .applyParameter("force", force);
    }

}
