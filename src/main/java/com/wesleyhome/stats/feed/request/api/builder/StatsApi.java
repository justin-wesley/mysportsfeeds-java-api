package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.League;

public class StatsApi {
    private final ApiCredentials credentials;
    private final League league;

    public StatsApi(ApiCredentials credentials, League league) {
        this.credentials = credentials;
        this.league = league;
    }

    public FullGameScheduleBuilder fullGameSchedule() {
        return new FullGameScheduleBuilder().credentials(credentials).league(league);
    }

    public DailyGameScheduleBuilder dailyGameSchedule() {
        return new DailyGameScheduleBuilder().credentials(credentials).league(league);
    }
}
