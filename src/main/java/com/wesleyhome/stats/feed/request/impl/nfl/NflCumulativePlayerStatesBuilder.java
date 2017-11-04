package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.impl.BaseCumulativePlayerStatsBuilder;

public final class NflCumulativePlayerStatesBuilder extends BaseCumulativePlayerStatsBuilder<NflCumulativePlayerStatesBuilder> {

    public static NflCumulativePlayerStatesBuilder cumulativePlayerStatesBuilder() {
        return new NflCumulativePlayerStatesBuilder();
    }

    private NflCumulativePlayerStatesBuilder() {
        super(NflCumulativePlayerStatsRequest.class);
    }

}
