package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.impl.nhl.NhlFullGameSchedule.NhlFullGameScheduleBuilder;

public final class NhlApi {

    private ApiCredentials credentials;

    public NhlApi (ApiCredentials credentials){
        this.credentials = credentials;
    }

    private NhlApi(){}

    public NhlFullGameScheduleBuilder fullGameSchedule(){
        return NhlFullGameSchedule.builder().credentials(credentials);
    }
}
