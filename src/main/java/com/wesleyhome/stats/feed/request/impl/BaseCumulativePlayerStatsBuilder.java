package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiRequest;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCumulativePlayerStatsBuilder<B extends BaseCumulativePlayerStatsBuilder<B>> extends
        RequestBuilder<B> {

    protected List<String> teams = new ArrayList<>();
    protected List<String> players = new ArrayList<>();
    protected List<String> positions = new ArrayList<>();
    protected List<String> countries = new ArrayList<>();
    protected String sort;
    protected Integer offset;
    protected Integer limit;
    protected Boolean force;

    protected <C extends DefaultApiRequest<T>, T> BaseCumulativePlayerStatsBuilder(Class<C> requestClass) {
        super(requestClass);
    }


    public B teams(List<String> teams) {
        this.teams = teams == null ? new ArrayList<>() : teams;
        return SELF;
    }

    public B team(String team) {
        this.teams.add(team);
        return SELF;
    }

    public B players(List<String> players) {
        this.players = players == null ? new ArrayList<>() : players;
        return SELF;
    }

    public B player(String player) {
        this.players.add(player);
        return SELF;
    }

    public B positions(List<String> positions) {
        this.positions = positions == null ? new ArrayList<>() : positions;
        return SELF;
    }

    public B position(String position) {
        this.positions.add(position);
        return SELF;
    }

    public B countries(List<String> countries) {
        this.countries = countries == null ? new ArrayList<>() : countries;
        return SELF;
    }

    public B country(String country) {
        this.countries.add(country);
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
                .applyListParameters(teams, "team")
                .applyListParameters(countries, "countries")
                .applyListParameters(positions, "positions")
                .applyListParameters(players, "players")
                .applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit)
                .applyParameter("force", force);
    }

}
