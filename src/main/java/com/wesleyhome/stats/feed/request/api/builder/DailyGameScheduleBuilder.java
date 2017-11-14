package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.DateConverters;
import com.wesleyhome.stats.feed.request.api.GameStatus;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

final class DailyGameScheduleBuilder extends RequestBuilder<DailyGameScheduleBuilder> {
    public static final String FEED_NAME = "daily_game_schedule";

    private List<String> teams = new ArrayList<>();
    private List<GameStatus> statuses = new ArrayList<>();
    private ChronoLocalDate forDate; // TODO: Implemented incorrectly. Must be in YYYYMMDD format
    private Boolean force;

    DailyGameScheduleBuilder() {
        super(FEED_NAME);
        onDate(LocalDate.now());
    }


    public DailyGameScheduleBuilder teams(List<String> teams) {
        this.teams = teams == null ? new ArrayList<>() : teams;
        return this;
    }

    public DailyGameScheduleBuilder team(String team) {
        this.teams.add(team);
        return this;
    }

    public DailyGameScheduleBuilder statuses(List<GameStatus> statuses) {
        this.statuses = statuses;
        return this;
    }

    public DailyGameScheduleBuilder onDate(ChronoLocalDate localDate) {
        this.forDate = localDate == null ? LocalDate.now() : localDate;
        return this;
    }

    public DailyGameScheduleBuilder force(Boolean force) {
        this.force = force;
        return this;
    }

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        request.applyParameter("for-date", DateConverters.onDate(forDate).toDateValue())
                .applyParameters("team", teams)
                .applyParameters("status", statuses)
                .applyParameter("force", force);
    }


}
