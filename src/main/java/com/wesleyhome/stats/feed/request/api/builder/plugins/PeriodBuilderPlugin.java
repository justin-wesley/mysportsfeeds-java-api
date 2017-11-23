package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PeriodBuilderPlugin<B extends RequestBuilder<B>> implements RequestBuilderPlugin {

    private final B builder;
    private PeriodRange periodRange;

    public PeriodBuilderPlugin(B builder) {
        this.builder = builder;
    }

    private B p(PeriodRange periodRange) {
        this.periodRange = periodRange;
        return builder;
    }

    public B at(Integer period) {
        return p(() -> period.toString());
    }

    public B since(Integer period, String time) {
        return p(() -> String.format("since-%s(%s)", period, time));
    }

    public B until(Integer period, String time) {
        return p(() -> String.format("until-%s(%s)", period, time));
    }

    public B between(Integer startPeriod, String startTime, Integer endPeriod, String endTime) {
        return p(() -> String.format("from-%s(%s)-to-%s(%s)", startPeriod, startTime, endPeriod, endTime));
    }


    @Override
    public void buildRequest(DefaultApiRequest request) {
        request.applyParameter(builder.getLeague().getPeriodName(), this.periodRange);
    }
}
