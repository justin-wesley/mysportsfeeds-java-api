package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.impl.BaseApi;

public final class NhlApi extends BaseApi {


    public NhlApi(ApiCredentials credentials) {
        super(credentials);
    }

    NhlFullGameScheduleBuilder fullGameSchedule() {
        return NhlFullGameScheduleBuilder.fullGameSchedule().credentials(getCredentials());
    }

    NhlCumulativePlayerStatesBuilder cumulativePlayerStates() {
        return NhlCumulativePlayerStatesBuilder.cumulativePlayerStatesBuilder().credentials(getCredentials());
    }

    NhlDailyPlayerStatsBuilder dailyPlayerStates() {
        return NhlDailyPlayerStatsBuilder.dailyPlayerStatesBuilder().credentials(getCredentials());
    }

    NhlDailyGameScheduleBuilder dailyGameSchedule() {
        return NhlDailyGameScheduleBuilder.dailyGameSchedule().credentials(getCredentials());
    }
}
