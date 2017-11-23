package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.League;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.api.builder.plugins.ApiParameterPlugin;

public abstract class RequestBuilder<B extends RequestBuilder<B>> {

    private final String feedName;
    private ApiCredentials credentials;
    private Integer season;
    private LeagueType leagueType;
    protected final B SELF;
    private League league;
    private ListManagerBuilder<ApiParameterPlugin> plugins = new ListManagerBuilder<>();

    protected RequestBuilder(String feedName) {
        this.feedName = feedName;
        SELF = (B) this;
    }

    B plugin(ApiParameterPlugin plugin, ApiParameterPlugin... plugins) {
        this.plugins.add(plugin, plugins);
        return SELF;
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
        plugins.forEach(plugin -> plugin.buildRequest(request));
        return request.sendRequest(responseType);
    }


    private DefaultApiRequest createRequest(Class<?> responseType) {
        return new DefaultApiRequest(this.credentials, this.feedName, this.league, this.season, this.leagueType);
    }


}
