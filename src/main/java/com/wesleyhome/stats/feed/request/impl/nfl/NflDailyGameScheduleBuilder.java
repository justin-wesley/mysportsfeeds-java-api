package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.impl.BaseDailyGameScheduleBuilder;

@SuppressWarnings("unchecked")
public final class NflDailyGameScheduleBuilder extends
        BaseDailyGameScheduleBuilder<NflDailyGameScheduleBuilder> {


    public static NflDailyGameScheduleBuilder dailyGameSchedule() {
        return new NflDailyGameScheduleBuilder();
    }

    private NflDailyGameScheduleBuilder() {
        super(NflDailyGameScheduleRequest.class);
    }


}
