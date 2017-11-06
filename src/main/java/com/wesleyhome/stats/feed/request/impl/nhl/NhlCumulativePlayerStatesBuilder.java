package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.impl.BasePlayerStatsBuilder;

public final class NhlCumulativePlayerStatesBuilder extends BasePlayerStatsBuilder<NhlCumulativePlayerStatesBuilder> {

    public static NhlCumulativePlayerStatesBuilder cumulativePlayerStatesBuilder() {
        return new NhlCumulativePlayerStatesBuilder();
    }

    private NhlCumulativePlayerStatesBuilder() {
        super(NhlCumulativePlayerStatsRequest.class);
    }

}
