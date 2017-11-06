package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueEnum;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.DateConverters;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

import java.time.chrono.ChronoLocalDate;

class NhlDailyPlayerStatsRequest<R> extends DefaultApiRequest<R> {

    NhlDailyPlayerStatsRequest(ApiCredentials credentials, ChronoLocalDate forDate, Class<R> responseType) {
        this(credentials, forDate, null, null, responseType);
    }

    NhlDailyPlayerStatsRequest(ApiCredentials credentials, ChronoLocalDate forDate, Integer startYear,
                               LeagueType leagueType,
                               Class<R> responseType) {
        super(credentials, "daily_player_stats", LeagueEnum.NHL, startYear, leagueType, responseType);
        addParameter("fordate", DateConverters.onDate(forDate).convert());
    }


}
