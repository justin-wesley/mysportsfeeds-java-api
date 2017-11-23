package com.wesleyhome.stats.feed.request.api.builder.plugins;

final class PeriodConverters {
    private PeriodConverters() {
    }

    static PeriodRange period(Integer period) {
        return () -> period.toString();
    }

    static PeriodRange since(Integer period, String time) {
        return () -> String.format("since-%s(%s)", period, time);
    }

    static PeriodRange until(Integer period, String time) {
        return () -> String.format("until-%s(%s)", period, time);
    }

    static PeriodRange between(Integer startPeriod, String startTime, Integer endPeriod, String endTime) {
        return () -> String.format("from-%s(%s)-to-%s(%s)", startPeriod, startTime, endPeriod, endTime);
    }
}
