package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.impl.BasePlayerStatsBuilder;

public final class NflCumulativePlayerStatesBuilder extends BasePlayerStatsBuilder<NflCumulativePlayerStatesBuilder> {

    public static NflCumulativePlayerStatesBuilder cumulativePlayerStatesBuilder() {
        return new NflCumulativePlayerStatesBuilder();
    }

    private NflCumulativePlayerStatesBuilder() {
        super(NflCumulativePlayerStatsRequest.class);
    }

}
