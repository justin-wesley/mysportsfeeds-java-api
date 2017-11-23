package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.League;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.api.Season;
import com.wesleyhome.stats.feed.request.api.builder.plugins.ForceBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.RequestBuilderPlugin;

public abstract class RequestBuilder<B extends RequestBuilder<B>> {

    private final String feedName;
    private final ForceBuilderPlugin<B> force;
    private ApiCredentials credentials;
    private Season season;
    private Integer startYear;
    private LeagueType leagueType;
    protected final B SELF;
    private League league;
    private ListManagerBuilder<RequestBuilderPlugin> plugins = new ListManagerBuilder<>();

    protected RequestBuilder(String feedName) {
        this.feedName = feedName;
        SELF = (B) this;
        plugin(
                this.force = new ForceBuilderPlugin<>(SELF)
        );
    }

    B plugin(RequestBuilderPlugin plugin, RequestBuilderPlugin... plugins) {
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
        this.startYear = season;
        return SELF;
    }

    public B season(Season season) {
        this.season = season;
        return SELF;
    }

    public B leagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
        return SELF;
    }

    public B force() {
        return force.force(true);
    }

    public League getLeague() {
        return this.league;
    }

    public final <R> R request(Class<R> responseType) {
        DefaultApiRequest request = new DefaultApiRequest(this.credentials, this.feedName, this.league, this.startYear, this.season, this.leagueType);
        plugins.forEach(plugin -> plugin.buildRequest(request));
        return request.sendRequest(responseType);
    }
}
