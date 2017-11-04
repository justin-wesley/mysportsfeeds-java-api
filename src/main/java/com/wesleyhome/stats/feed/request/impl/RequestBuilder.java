package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class RequestBuilder<B extends RequestBuilder<B>> {

    private ApiCredentials credentials;
    private Integer season;
    private LeagueType leagueType;
    private final Class<? extends DefaultApiRequest<?>> requestClass;
    protected final B SELF;

    protected <C extends DefaultApiRequest<T>, T> RequestBuilder(Class<C> requestClass) {
        this.requestClass = requestClass;
        SELF = (B) this;
    }

    public B credentials(ApiCredentials credentials) {
        this.credentials = credentials;
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

    protected <R> DefaultApiRequest<R> createRequest(Class<R> responseType) {
        try {
            DefaultApiRequest<R> request;
            if (season == null) {
                Constructor<? extends DefaultApiRequest<?>> constructor = this.requestClass.getDeclaredConstructor(ApiCredentials.class, Class.class);
                constructor.setAccessible(true);
                request = (DefaultApiRequest<R>) constructor.newInstance(credentials, responseType);
            } else {
                if (leagueType == null) {
                    leagueType = (LeagueType.REGULAR);
                }
                Constructor<? extends DefaultApiRequest<?>> constructor = this.requestClass.getDeclaredConstructor(ApiCredentials.class, Integer.class, LeagueType.class, Class.class);
                constructor.setAccessible(true);
                request = (DefaultApiRequest<R>) constructor.newInstance(credentials, season, leagueType, responseType);
            }
            return request;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
