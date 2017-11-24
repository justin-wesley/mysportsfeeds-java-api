package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.SemanticDate;
import com.wesleyhome.stats.feed.request.api.TimeUnit;
import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

import java.time.chrono.ChronoLocalDate;

public class DateBuilderPlugin<B extends RequestBuilder<B>> implements RequestBuilderPlugin {

    private final B builder;
    private final String parameterName;
    private DateRange dateRange;

    public DateBuilderPlugin(B builder, String parameterName) {
        this.builder = builder;
        this.parameterName = parameterName;
    }

    public DateBuilderPlugin(B builder) {
        this(builder, "fordate");
    }

    public B date(DateRange dateRange) {
        this.dateRange = dateRange;
        return builder;
    }

    public B onDate(SemanticDate today) {
        return date(today);
    }

    public B onDate(String date) {
        return date(() -> date);
    }

    public B onDate(ChronoLocalDate localDate) {
        return date(DateConverters.onDate(localDate));
    }

    public B since(SemanticDate sDate) {
        return date(DateConverters.since(sDate));
    }

    public B since(ChronoLocalDate localDate) {
        return date(DateConverters.since(localDate));
    }

    public B since(int time, TimeUnit timeUnit) {
        return date(DateConverters.since(time, timeUnit));
    }

    public B until(SemanticDate sDate) {
        return date(DateConverters.until(sDate));
    }

    public B until(ChronoLocalDate localDate) {
        return date(DateConverters.until(localDate));
    }

    public B until(int time, TimeUnit timeUnit) {
        return date(DateConverters.until(time, timeUnit));
    }

    public B between(SingleDate from, SingleDate to) {
        return date(DateConverters.between(from, to));
    }


    @Override
    public void buildRequest(DefaultApiRequest request) {
        request.applyParameter(parameterName, this.dateRange);
    }
}
