package com.wesleyhome.stats.feed.request.api;

public enum LeagueEnum implements League {
    NHL,
    NFL,
    NBA,
    MLB(false);

    private final boolean multiYear;

    LeagueEnum() {
        this(true);
    }

    LeagueEnum(final boolean multiYear) {
        this.multiYear = multiYear;
    }

    @Override
    public boolean isMultiYear() {
        return multiYear;
    }

    public String toString() {
        return name().toLowerCase();
    }
}
