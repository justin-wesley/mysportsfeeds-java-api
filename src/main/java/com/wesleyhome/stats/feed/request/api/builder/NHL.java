package com.wesleyhome.stats.feed.request.api.builder;

class NHL extends LeagueClass {

    static final NHL instance = new NHL();

    private NHL() {
        super("period");
    }
}
