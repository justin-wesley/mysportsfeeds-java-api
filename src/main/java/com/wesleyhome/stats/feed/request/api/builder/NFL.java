package com.wesleyhome.stats.feed.request.api.builder;

class NFL extends LeagueClass {

    static final NFL instance = new NFL();

    private NFL() {
        super("quarter");
    }
}
