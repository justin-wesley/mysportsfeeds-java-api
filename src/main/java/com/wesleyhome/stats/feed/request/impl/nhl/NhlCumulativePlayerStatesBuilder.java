package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.impl.BaseCumulativePlayerStatsBuilder;

public final class NhlCumulativePlayerStatesBuilder extends BaseCumulativePlayerStatsBuilder<NhlCumulativePlayerStatesBuilder> {

    public static NhlCumulativePlayerStatesBuilder cumulativePlayerStatesBuilder() {
        return new NhlCumulativePlayerStatesBuilder();
    }

    private NhlCumulativePlayerStatesBuilder() {
        super(NhlCumulativePlayerStatsRequest.class);
    }

}
