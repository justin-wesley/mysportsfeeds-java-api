package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.impl.BaseDailyGameScheduleBuilder;

@SuppressWarnings("unchecked")
public final class NhlDailyGameScheduleBuilder extends
        BaseDailyGameScheduleBuilder<NhlDailyGameScheduleBuilder> {


    public static NhlDailyGameScheduleBuilder dailyGameSchedule() {
        return new NhlDailyGameScheduleBuilder();
    }

    private NhlDailyGameScheduleBuilder() {
        super(NhlDailyGameScheduleRequest.class);
    }


}
