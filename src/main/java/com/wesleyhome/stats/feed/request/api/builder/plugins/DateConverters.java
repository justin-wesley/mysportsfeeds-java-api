package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.SemanticDate;
import com.wesleyhome.stats.feed.request.api.TimeUnit;

import java.time.chrono.ChronoLocalDate;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

final class DateConverters {

    private DateConverters() {
    }

    static SingleDate onDate(ChronoLocalDate localDate) {
        return () -> sv(localDate);
    }

    static DateRange since(SemanticDate sDate) {
        return () -> s(sv(sDate));
    }

    static DateRange since(ChronoLocalDate localDate) {
        return () -> s(sv(localDate));
    }

    static DateRange since(int time, TimeUnit timeUnit) {
        return () -> s(sv(time, timeUnit, "ago"));
    }

    static DateRange until(SemanticDate sDate) {
        return () -> u(sv(sDate));
    }

    static DateRange until(ChronoLocalDate localDate) {
        return () -> u(sv(localDate));
    }

    static DateRange until(int time, TimeUnit timeUnit) {
        return () -> s(sv(time, timeUnit, "from-now"));
    }

    static DateRange between(SingleDate from, SingleDate to) {
        return () -> "from-" + from.toStringValue() + "-to-" + to.toStringValue();
    }

    private static String sv(int time, TimeUnit timeUnit, String suffix) {
        return format("%s-%s-%s", time, timeUnit.name().toLowerCase(), suffix);
    }

    private static String sv(SemanticDate sDate) {
        return sDate.name().toLowerCase();
    }

    private static String sv(ChronoLocalDate localDate) {
        return ofPattern("yyyyMMdd").format(localDate);
    }

    private static String s(String value) {
        return "since-" + value;
    }

    private static String u(String value) {
        return "until-" + value;
    }
}
