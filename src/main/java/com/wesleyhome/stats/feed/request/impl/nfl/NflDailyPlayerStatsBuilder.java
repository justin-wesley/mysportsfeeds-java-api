package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.impl.BasePlayerStatsBuilder;

public final class NflDailyPlayerStatsBuilder extends BasePlayerStatsBuilder<NflDailyPlayerStatsBuilder> {

    public static NflDailyPlayerStatsBuilder dailyPlayerStatesBuilder() {
        return new NflDailyPlayerStatsBuilder();
    }

    private NflDailyPlayerStatsBuilder() {
        super(NflDailyPlayerStatsRequest.class);
    }

}
