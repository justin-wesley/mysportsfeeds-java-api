package com.wesleyhome.stats.feed.request.impl;

import static java.util.stream.Collectors.toList;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.GameStatus;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFullGameScheduleBuilder<B extends BaseFullGameScheduleBuilder<B>> extends
    RequestBuilder implements DateBuilder<B> {

  protected ApiCredentials credentials;
  protected Integer season;
  protected LeagueType leagueType;
  protected List<String> teams = new ArrayList<>();
  protected DateConverter date;
  protected List<GameStatus> statuses = new ArrayList<>();
  protected String sort;
  protected Integer offset;
  protected Integer limit;
  protected Boolean force;
  private final B SELF;

  @SuppressWarnings("unchecked")
  protected BaseFullGameScheduleBuilder() {
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

  public B date(DateConverter date) {
    this.date = date;
    return SELF;
  }

  public B statuses(List<GameStatus> statuses) {
    this.statuses = statuses;
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
    applyListParameters(request, statuses.stream().map(GameStatus::toString).collect(toList()),
        "status");
    applyParameter(request, "date", date::convert);
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
