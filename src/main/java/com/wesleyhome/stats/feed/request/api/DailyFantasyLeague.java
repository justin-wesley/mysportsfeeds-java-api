package com.wesleyhome.stats.feed.request.api;

public enum DailyFantasyLeague implements ValueTransformer {
    DRAFT_KINGS("draftkings"),
    FAN_DUEL("fanduel");

    private final String value;

    DailyFantasyLeague(final String value) {
        this.value = value;
    }

    @Override
    public String toStringValue() {
        return value;
    }
}
