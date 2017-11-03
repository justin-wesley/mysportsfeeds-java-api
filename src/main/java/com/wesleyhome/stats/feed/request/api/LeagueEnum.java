package com.wesleyhome.stats.feed.request.api;

import com.wesleyhome.stats.feed.request.api.League;

public enum LeagueEnum implements League {
    NHL,
    NFL,
    NBA,
    MLB(false);

    private boolean multiYear;

    LeagueEnum(){
        this(true);
    }

    LeagueEnum(final boolean multiYear){
        this.multiYear = multiYear;
    }

    @Override
    public boolean isMultiYear(){
        return multiYear;
    }

    public String toString(){
        return name().toLowerCase();
    }
}
