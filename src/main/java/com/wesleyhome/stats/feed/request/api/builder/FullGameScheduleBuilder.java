package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.DateRange;
import com.wesleyhome.stats.feed.request.api.GameStatus;

import java.time.LocalDate;

public final class FullGameScheduleBuilder extends
        RequestBuilder<FullGameScheduleBuilder> implements DateBuilder<FullGameScheduleBuilder> {
    public static final String FEED_NAME = "full_game_schedule";
    protected ListManagerBuilder<String> teams = new ListManagerBuilder<>();
    protected DateRange date;
    protected ListManagerBuilder<GameStatus> statuses = new ListManagerBuilder<>();
    protected String sort;
    protected Integer offset;
    protected Integer limit;
    protected Boolean force;

    FullGameScheduleBuilder() {
        super(FEED_NAME);
    }


    public FullGameScheduleBuilder teams(String team, String... additionalTeams) {
        this.teams.add(team, additionalTeams);
        return this;
    }

    public FullGameScheduleBuilder date(DateRange date) {
        if (date == null) {
            onDate(LocalDate.now());
        } else {
            this.date = date;
        }
        return this;
    }

    public FullGameScheduleBuilder statuses(GameStatus status, GameStatus... additionalStatuses) {
        this.statuses.add(status, additionalStatuses);
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
        request.applyParameters("team", teams)
                .applyParameters("status", statuses)
                .applyParameter("date", date)
                .applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit)
                .applyParameter("force", force);
    }

}
