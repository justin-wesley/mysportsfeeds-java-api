package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCumulativePlayerStatsBuilder<B extends BaseCumulativePlayerStatsBuilder<B>> extends
    RequestBuilder {

  protected ApiCredentials credentials;
  protected Integer season;
  protected LeagueType leagueType;
  protected List<String> teams = new ArrayList<>();
  protected List<String> players = new ArrayList<>();
  protected List<String> positions = new ArrayList<>();
  protected List<String> countries = new ArrayList<>();
  protected String sort;
  protected Integer offset;
  protected Integer limit;
  protected Boolean force;
  private final B SELF;

  @SuppressWarnings("unchecked")
  protected BaseCumulativePlayerStatsBuilder() {
    //noinspection unchecked
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

  public B teams(List<String> teams) {
    this.teams = teams == null ? new ArrayList<>() : teams;
    return SELF;
  }

  public B team(String team) {
    this.teams.add(team);
    return SELF;
  }

  public B players(List<String> players) {
    this.players = players == null ? new ArrayList<>() : players;
    return SELF;
  }

  public B player(String player) {
    this.players.add(player);
    return SELF;
  }

  public B positions(List<String> positions) {
    this.positions = positions == null ? new ArrayList<>() : positions;
    return SELF;
  }

  public B position(String position) {
    this.positions.add(position);
    return SELF;
  }

  public B countries(List<String> countries) {
    this.countries = countries == null ? new ArrayList<>() : countries;
    return SELF;
  }

  public B country(String country) {
    this.countries.add(country);
    return SELF;
  }

  public B sort(String sort) {
    this.sort = sort;
    return SELF;
  }

  public B offset(Integer offset) {
    this.offset = offset;
    return SELF;
  }

  public B limit(Integer limit) {
    this.limit = limit;
    return SELF;
  }

  public B force(Boolean force) {
    this.force = force;
    return SELF;
  }

  public final <R> ApiRequest<R> buildRequest(Class<R> responseType) {
//    S schedule = build();
    DefaultApiRequest<R> request;
    if (season == null) {
      request = createRequest(credentials, responseType);
    } else {
      if (leagueType == null) {
        leagueType = (LeagueType.REGULAR);
      }
      request = createRequest(credentials, season, leagueType, responseType);
    }
    applyListParameters(request, teams, "team");
    applyListParameters(request, countries, "countries");
    applyListParameters(request, positions, "positions");
    applyListParameters(request, players, "players");
    applyParameter(request, "sort", sort);
    applyParameter(request, "offset", offset);
    applyParameter(request, "limit", limit);
    applyParameter(request, "force", force);
    return request;
  }

  protected abstract <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials,
      Integer season, LeagueType leagueType, Class<R> responseType);

  protected abstract <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials,
      Class<R> responseType);

}
