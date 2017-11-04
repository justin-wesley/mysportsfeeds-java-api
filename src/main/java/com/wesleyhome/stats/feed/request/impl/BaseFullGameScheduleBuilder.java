package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.GameStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFullGameScheduleBuilder<B extends BaseFullGameScheduleBuilder<B>> extends
        RequestBuilder<B> implements DateBuilder<B> {

    protected List<String> teams = new ArrayList<>();
    protected DateConverter date;
    protected List<GameStatus> statuses = new ArrayList<>();
    protected String sort;
    protected Integer offset;
    protected Integer limit;
    protected Boolean force;

    protected <C extends DefaultApiRequest<T>, T> BaseFullGameScheduleBuilder(Class<C> requestClass) {
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

    public B date(DateConverter date) {
        this.date = date;
        return SELF;
    }

    public B statuses(List<GameStatus> statuses) {
        this.statuses = statuses;
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
                .applyParameters("status", statuses)
                .applyParameter("date", date::convert)
                .applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit)
                .applyParameter("force", force);
    }

}
