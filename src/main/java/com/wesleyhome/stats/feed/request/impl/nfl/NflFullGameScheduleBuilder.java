package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.impl.BaseFullGameScheduleBuilder;

@SuppressWarnings("unchecked")
public final class NflFullGameScheduleBuilder extends
        BaseFullGameScheduleBuilder<NflFullGameScheduleBuilder> implements
        DateBuilder<NflFullGameScheduleBuilder> {


    private NflFullGameScheduleBuilder() {
        super(NflFullGameScheduleRequest.class);
    }

    public static NflFullGameScheduleBuilder fullGameSchedule() {
        return new NflFullGameScheduleBuilder();
    }


}
