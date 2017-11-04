package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.impl.BaseFullGameScheduleBuilder;

@SuppressWarnings("unchecked")
public final class NhlFullGameScheduleBuilder extends
        BaseFullGameScheduleBuilder<NhlFullGameScheduleBuilder> implements
        DateBuilder<NhlFullGameScheduleBuilder> {


    protected NhlFullGameScheduleBuilder() {
        super(NhlFullGameScheduleRequest.class);
    }

    public static NhlFullGameScheduleBuilder fullGameSchedule() {
        return new NhlFullGameScheduleBuilder();
    }


}
