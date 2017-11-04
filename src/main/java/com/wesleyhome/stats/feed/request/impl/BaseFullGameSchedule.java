package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.GameStatus;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFullGameSchedule {

  private final ApiCredentials credentials;
  private final Integer season;
  private LeagueType leagueType;
  private List<String> teams = new ArrayList<>();
  private DateConverter date;
  private List<GameStatus> statuses = new ArrayList<>();
  private String sort;
  private Integer offset;
  private Integer limit;
  private Boolean force;

  protected BaseFullGameSchedule(Integer season, DateConverter date, Boolean force,
      LeagueType leagueType, List<String> teams, Integer offset, ApiCredentials credentials,
      Integer limit, String sort, List<GameStatus> statuses) {
    this.season = season;
    this.date = date;
    this.force = force;
    this.leagueType = leagueType;
    this.teams = teams;
    this.offset = offset;
    this.credentials = credentials;
    this.limit = limit;
    this.sort = sort;
    this.statuses = statuses;
  }

  public ApiCredentials getCredentials() {
    return credentials;
  }

  public Integer getSeason() {
    return season;
  }

  public LeagueType getLeagueType() {
    return leagueType;
  }

  public void setLeagueType(LeagueType leagueType) {
    this.leagueType = leagueType;
  }

  public List<String> getTeams() {
    return teams;
  }

  public void setTeams(List<String> teams) {
    this.teams = teams;
  }

  public DateConverter getDate() {
    return date;
  }

  public void setDate(DateConverter date) {
    this.date = date;
  }

  public List<GameStatus> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<GameStatus> statuses) {
    this.statuses = statuses;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Boolean getForce() {
    return force;
  }

  public void setForce(Boolean force) {
    this.force = force;
  }
}
