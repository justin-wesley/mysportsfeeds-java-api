package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueEnum;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

class NflDailyPlayerStatsRequest<R> extends DefaultApiRequest<R> {

    NflDailyPlayerStatsRequest(ApiCredentials credentials, Class<R> responseType) {
        this(credentials, null, null, responseType);
    }

    NflDailyPlayerStatsRequest(ApiCredentials credentials, Integer startYear,
                               LeagueType leagueType,
                               Class<R> responseType) {
        super(credentials, "daily_player_stats", LeagueEnum.NFL, startYear, leagueType,
                responseType);
    }
}
