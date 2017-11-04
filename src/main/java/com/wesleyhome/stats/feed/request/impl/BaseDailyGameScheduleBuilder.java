package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.GameStatus;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDailyGameScheduleBuilder<B extends BaseDailyGameScheduleBuilder<B>> extends RequestBuilder<B> {

    private List<String> teams = new ArrayList<>();
    private List<GameStatus> statuses = new ArrayList<>();
    private DateConverter date;
    private Boolean force;

    protected <C extends DefaultApiRequest<T>, T> BaseDailyGameScheduleBuilder(Class<C> requestClass) {
        super(requestClass);
        onDate(LocalDate.now());
    }


    public B teams(List<String> teams) {
        this.teams = teams == null ? new ArrayList<>() : teams;
        return SELF;
    }

    public B team(String team) {
        this.teams.add(team);
        return SELF;
    }

    public B statuses(List<GameStatus> statuses) {
        this.statuses = statuses;
        return SELF;
    }

    public B onDate(ChronoLocalDate localDate) {
        this.date = DateConverters.onDate(localDate);
        return SELF;
    }

    public B force(Boolean force) {
        this.force = force;
        return SELF;
    }

    public final <R> ApiRequest<R> buildRequest(Class<R> responseType) {
        return createRequest(responseType)
                .applyParameters("team", teams)
                .applyParameters("status", statuses)
                .applyParameter("fordate", date::convert)
                .applyParameter("force", force);
    }

}
