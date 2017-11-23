package com.wesleyhome.stats.feed.request.api;

import java.time.chrono.ChronoLocalDate;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

public final class DateConverters {


    public static SingleDate onDate(ChronoLocalDate localDate) {
        return () -> sv(localDate);
    }

    public static DateRange since(SemanticDate sDate) {
        return () -> s(sv(sDate));
    }

    public static DateRange since(ChronoLocalDate localDate) {
        return () -> s(sv(localDate));
    }

    public static DateRange since(int time, TimeUnit timeUnit) {
        return () -> s(sv(time, timeUnit, "ago"));
    }

    public static DateRange until(SemanticDate sDate) {
        return () -> u(sv(sDate));
    }

    public static DateRange until(ChronoLocalDate localDate) {
        return () -> u(sv(localDate));
    }

    public static DateRange until(int time, TimeUnit timeUnit) {
        return () -> s(sv(time, timeUnit, "from-now"));
    }

    public static DateRange between(SingleDate from, SingleDate to) {
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