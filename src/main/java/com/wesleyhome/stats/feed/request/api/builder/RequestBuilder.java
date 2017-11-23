package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.League;
import com.wesleyhome.stats.feed.request.api.LeagueType;

public abstract class RequestBuilder<B extends RequestBuilder<B>> {

    private final String feedName;
    private ApiCredentials credentials;
    private Integer season;
    private LeagueType leagueType;
    protected final B SELF;
    private League league;

    protected RequestBuilder(String feedName) {
        this.feedName = feedName;
        SELF = (B) this;
    }

    B credentials(ApiCredentials credentials) {
        this.credentials = credentials;
        return SELF;
    }

    B league(League league) {
        this.league = league;
        return SELF;
    }

    public B season(Integer season) {
        this.season = season;
        return SELF;
    }

    public B leagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
        return SELF;
    }

    public final <R> R request(Class<R> responseType) {
        DefaultApiRequest request = createRequest(responseType);
        buildRequest(request);
        return request.sendRequest(responseType);
    }

    protected abstract void buildRequest(DefaultApiRequest request);

    private DefaultApiRequest createRequest(Class<?> responseType) {
        return new DefaultApiRequest(this.credentials, this.feedName, this.league, this.season, this.leagueType);
    }


}
