package com.wesleyhome.stats.feed.request.api.builder;

class MLB extends LeagueClass {

    static final MLB instance = new MLB();

    private MLB() {
        super(false, "inning");
    }
}
