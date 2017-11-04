package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.BaseFullGameScheduleBuilder;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

@SuppressWarnings("unchecked")
public final class NhlFullGameScheduleBuilder extends
    BaseFullGameScheduleBuilder<NhlFullGameScheduleBuilder, NhlFullGameSchedule> implements
    DateBuilder<NhlFullGameScheduleBuilder> {


  public static NhlFullGameScheduleBuilder fullGameSchedule() {
    return new NhlFullGameScheduleBuilder();
  }

  @Override
  protected NhlFullGameSchedule build() {
    return new NhlFullGameSchedule(credentials, season, leagueType, teams, date, statuses, sort,
        offset, limit, force);
  }

  @Override
  protected <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials, Integer season,
      LeagueType leagueType, Class<R> responseType) {
    return new NhlFullGameScheduleRequest(credentials, season, leagueType, responseType);
  }

  @Override
  protected <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials,
      Class<R> responseType) {
    return new NhlFullGameScheduleRequest(credentials, responseType);
  }

}
