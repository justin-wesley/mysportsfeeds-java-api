package com.wesleyhome.stats.feed.request.api;

public enum LeagueType {
    REGULAR, PLAYOFF;

    public String toString() {
        return name().toLowerCase();
    }
}
