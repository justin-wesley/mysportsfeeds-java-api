package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.GameStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

final class FullGameScheduleBuilder extends
        RequestBuilder<FullGameScheduleBuilder> implements DateBuilder<FullGameScheduleBuilder> {
    public static final String FEED_NAME = "full_game_schedule";
    protected List<String> teams = new ArrayList<>();
    protected DateConverter date;
    protected List<GameStatus> statuses = new ArrayList<>();
    protected String sort;
    protected Integer offset;
    protected Integer limit;
    protected Boolean force;

    FullGameScheduleBuilder() {
        super(FEED_NAME);
    }


    public FullGameScheduleBuilder teams(List<String> teams) {
        this.teams = teams == null ? new ArrayList<>() : teams;
        return this;
    }

    public FullGameScheduleBuilder team(String team) {
        this.teams.add(team);
        return this;
    }

    public FullGameScheduleBuilder date(DateConverter date) {
        if (date == null) {
            onDate(LocalDate.now());
        } else {
            this.date = date;
        }
        return this;
    }

    public FullGameScheduleBuilder statuses(List<GameStatus> statuses) {
        this.statuses = statuses;
        return this;
    }

    public FullGameScheduleBuilder sort(String sort) {
        this.sort = sort;
        return this;
    }

    public FullGameScheduleBuilder offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public FullGameScheduleBuilder limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public FullGameScheduleBuilder force(Boolean force) {
        this.force = force;
        return this;
    }

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        request.applyListParameters("team", teams)
                .applyParameters("status", statuses)
                .applyParameter("date", date::toDateValue)
                .applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit)
                .applyParameter("force", force);
    }

}
