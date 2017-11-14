package com.wesleyhome.stats.feed.request.api;

import java.time.chrono.ChronoLocalDate;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

public final class DateConverters {

    public static SingleDate sd(SemanticDate semanticDate) {
        return semanticDate::converter;
    }

    public static SingleDate sd(ChronoLocalDate localDate) {
        return () -> onDate(localDate);
    }

    public static DateConverter onDate(ChronoLocalDate localDate) {
        return () -> sv(localDate);
    }

    public static DateConverter since(SemanticDate sDate) {
        return () -> s(sv(sDate));
    }

    public static DateConverter since(ChronoLocalDate localDate) {
        return () -> s(sv(localDate));
    }

    public static DateConverter since(int time, TimeUnit timeUnit) {
        return () -> s(sv(time, timeUnit, "ago"));
    }

    public static DateConverter until(SemanticDate sDate) {
        return () -> u(sv(sDate));
    }

    public static DateConverter until(ChronoLocalDate localDate) {
        return () -> u(sv(localDate));
    }

    public static DateConverter until(int time, TimeUnit timeUnit) {
        return () -> s(sv(time, timeUnit, "from-now"));
    }

    public static DateConverter between(SingleDate from, SingleDate to) {
        return () -> "from-" + from.toDateValue() + "-to-" + to.toDateValue();
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
