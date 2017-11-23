package com.wesleyhome.stats.feed.request.api.builder;

class NBA extends LeagueClass {

    static final NBA instance = new NBA();

    private NBA() {
        super("quarter");
    }
}
