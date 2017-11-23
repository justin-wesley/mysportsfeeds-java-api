package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.League;

abstract class LeagueClass implements League {

//    public static final League NHL = new LeagueClass( "period");
//    public static final League NFL = new LeagueClass( "quarter");
//    public static final League NBA = new LeagueClass( "quarter");
//    public static final League MLB = new LeagueClass( false, "inning");


    private final boolean isMultiYear;
    private final String periodName;


    protected LeagueClass(String periodName) {
        this(true, periodName);
    }

    protected LeagueClass(final boolean isMultiYear, final String periodName) {

        this.isMultiYear = isMultiYear;
        this.periodName = periodName;
    }

    @Override
    public boolean isMultiYear() {
        return isMultiYear;
    }

    @Override
    public String getPeriodName() {
        return periodName;
    }
}
