package com.wesleyhome.stats.feed.request.impl;

import static java.util.stream.Collectors.joining;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.GameStatus;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFullGameScheduleBuilder<B extends BaseFullGameScheduleBuilder<B, S>, S extends BaseFullGameSchedule> implements
    DateBuilder<B> {

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

  protected abstract S build();

  public final <R> ApiRequest<R> buildRequest(Class<R> responseType) {
    S schedule = build();
    DefaultApiRequest<R> request;
    Integer season = schedule.getSeason();
    if (season == null) {
      request = createRequest(schedule.getCredentials(), responseType);
    } else {
      LeagueType leagueType = schedule.getLeagueType();
      if (leagueType == null) {
        schedule.setLeagueType(LeagueType.REGULAR);
      }
      request = createRequest(schedule.getCredentials(), season, leagueType, responseType);
    }
    if (!schedule.getTeams().isEmpty()) {
      String teamValue = String.join(",", schedule.getTeams());
      request.addParameter("team", teamValue);
    }
    if (!schedule.getStatuses().isEmpty()) {
      String statusValue = schedule.getStatuses().stream().map(GameStatus::toString)
          .collect(joining(","));
      request.addParameter("status", statusValue);
    }
    if (schedule.getDate() != null) {
      String dateValue = schedule.getDate().convert();
      request.addParameter("date", dateValue);
    }
    if (schedule.getSort() != null) {
      request.addParameter("sort", schedule.getSort());
    }
    if (schedule.getOffset() != null) {
      request.addParameter("offset", schedule.getOffset());
    }
    if (schedule.getLimit() != null) {
      request.addParameter("limit", schedule.getLimit());
    }
    if (schedule.getForce() != null) {
      request.addParameter("force", schedule.getForce());
    }
    return request;
  }

  protected abstract <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials,
      Integer season, LeagueType leagueType, Class<R> responseType);

  protected abstract <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials,
      Class<R> responseType);

}
