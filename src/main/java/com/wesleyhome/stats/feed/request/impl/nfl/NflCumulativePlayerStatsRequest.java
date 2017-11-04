package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueEnum;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

class NflCumulativePlayerStatsRequest<R> extends DefaultApiRequest<R> {

    NflCumulativePlayerStatsRequest(ApiCredentials credentials, Class<R> responseType) {
        this(credentials, null, null, responseType);
    }

    NflCumulativePlayerStatsRequest(ApiCredentials credentials, Integer startYear,
                                    LeagueType leagueType,
                                    Class<R> responseType) {
        super(credentials, "cumulative_player_stats", LeagueEnum.NFL, startYear, leagueType,
                responseType);
    }
}
