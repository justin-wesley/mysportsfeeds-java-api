package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueEnum;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

class NhlDailyGameScheduleRequest<R> extends DefaultApiRequest<R> {

    NhlDailyGameScheduleRequest(ApiCredentials credentials, Class<R> responseType) {
        this(credentials, null, null, responseType);
    }

    NhlDailyGameScheduleRequest(ApiCredentials credentials, Integer startYear, LeagueType leagueType,
                                Class<R> responseType) {
        super(credentials, "daily_game_schedule", LeagueEnum.NHL, startYear, leagueType, responseType);
    }
}
