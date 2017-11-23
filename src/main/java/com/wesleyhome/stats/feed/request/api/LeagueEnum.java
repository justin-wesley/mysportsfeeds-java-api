package com.wesleyhome.stats.feed.request.api;

public enum LeagueEnum implements League {
    NHL("period"),
    NFL("quarter"),
    NBA("quarter"),
    MLB(false, "inning");

    private final boolean multiYear;
    private final String periodName;

    LeagueEnum(final String periodName) {
        this(true, periodName);
    }

    LeagueEnum(final boolean multiYear, final String periodName) {
        this.multiYear = multiYear;
        this.periodName = periodName;
    }

    @Override
    public boolean isMultiYear() {
        return multiYear;
    }

    public String getPeriodName() {
        return this.periodName;
    }

    public String toString() {
        return name().toLowerCase();
    }
}
