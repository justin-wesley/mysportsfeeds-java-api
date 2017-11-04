package com.wesleyhome.stats.feed.request.api;

import com.wesleyhome.stats.feed.request.impl.DateConverters;

import java.time.chrono.ChronoLocalDate;

public interface DateBuilder<B extends DateBuilder<B>> {

    B date(DateConverter converter);

    default B onDate(SemanticDate today) {
        return date(today.converter());
    }

    default B onDate(String date) {
        return date(() -> date);
    }

    default B onDate(ChronoLocalDate localDate) {
        return date(DateConverters.onDate(localDate));
    }

    default B since(SemanticDate sDate) {
        return date(DateConverters.since(sDate));
    }

    default B since(ChronoLocalDate localDate) {
        return date(DateConverters.since(localDate));
    }

    default B since(int time, TimeUnit timeUnit) {
        return date(DateConverters.since(time, timeUnit));
    }

    default B until(SemanticDate sDate) {
        return date(DateConverters.until(sDate));
    }

    default B until(ChronoLocalDate localDate) {
        return date(DateConverters.until(localDate));
    }

    default B until(int time, TimeUnit timeUnit) {
        return date(DateConverters.until(time, timeUnit));
    }

    default B between(SingleDate from, SingleDate to) {
        return date(DateConverters.between(from, to));
    }
}
