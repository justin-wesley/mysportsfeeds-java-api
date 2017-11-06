package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.impl.BaseApi;

public final class NflApi extends BaseApi {


    public NflApi(ApiCredentials credentials) {
        super(credentials);
    }

    NflFullGameScheduleBuilder fullGameSchedule() {
        return NflFullGameScheduleBuilder.fullGameSchedule().credentials(getCredentials());
    }

    NflCumulativePlayerStatesBuilder cumulativePlayerStates() {
        return NflCumulativePlayerStatesBuilder.cumulativePlayerStatesBuilder().credentials(getCredentials());
    }

    NflDailyPlayerStatsBuilder dailyPlayerStates() {
        return NflDailyPlayerStatsBuilder.dailyPlayerStatesBuilder().credentials(getCredentials());
    }

    NflDailyGameScheduleBuilder dailyGameSchedule() {
        return NflDailyGameScheduleBuilder.dailyGameSchedule().credentials(getCredentials());
    }
}
