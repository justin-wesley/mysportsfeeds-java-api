package com.wesleyhome.stats.feed;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueEnum;
import com.wesleyhome.stats.feed.request.api.builder.StatsApi;
import com.wesleyhome.stats.feed.request.api.credentials.BasicApiCredentials;

/**
 *
 */
public final class MySportsFeedAPI {

    public static StatsApi nhl(ApiCredentials credentials) {
        return new StatsApi(credentials, LeagueEnum.NHL);
    }

    public static StatsApi nhl(String username, String password) {
        return nhl(new BasicApiCredentials(username, password));
    }

    public static StatsApi nfl(ApiCredentials credentials) {
        return new StatsApi(credentials, LeagueEnum.NFL);
    }

    public static StatsApi nfl(String username, String password) {
        return nhl(new BasicApiCredentials(username, password));
    }

}
