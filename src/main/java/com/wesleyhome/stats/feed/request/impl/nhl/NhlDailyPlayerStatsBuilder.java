package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.impl.BasePlayerStatsBuilder;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public final class NhlDailyPlayerStatsBuilder extends BasePlayerStatsBuilder<NhlDailyPlayerStatsBuilder> {

    public static NhlDailyPlayerStatsBuilder dailyPlayerStatesBuilder() {
        return new NhlDailyPlayerStatsBuilder();
    }

    private ChronoLocalDate forDate = LocalDate.now();

    private NhlDailyPlayerStatsBuilder() {
        super(NhlDailyPlayerStatsRequest.class);
    }

    @Override
    protected <R> DefaultApiRequest<R> createRequest(Class<R> responseType) {
        return super.createRequest(responseType);
    }

    @Override
    protected Class<?>[] getCurrentSeasonParameterTypes() {
        return new Class<?>[]{ApiCredentials.class, ChronoLocalDate.class, Class.class};
    }

    public NhlDailyPlayerStatsBuilder forDate(ChronoLocalDate forDate) {
        this.forDate = forDate == null ? LocalDate.now() : forDate;
        return this;
    }
}
