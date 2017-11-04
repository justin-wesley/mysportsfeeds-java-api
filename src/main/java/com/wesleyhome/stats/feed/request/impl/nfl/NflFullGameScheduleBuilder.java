package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.DateBuilder;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.BaseFullGameScheduleBuilder;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

@SuppressWarnings("unchecked")
public final class NflFullGameScheduleBuilder extends
    BaseFullGameScheduleBuilder<NflFullGameScheduleBuilder, NflFullGameSchedule> implements
    DateBuilder<NflFullGameScheduleBuilder> {


  public static NflFullGameScheduleBuilder fullGameSchedule() {
    return new NflFullGameScheduleBuilder();
  }

  @Override
  protected NflFullGameSchedule build() {
    return new NflFullGameSchedule(credentials, season, leagueType, teams, date, statuses, sort,
        offset, limit, force);
  }

  @Override
  protected <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials, Integer season,
      LeagueType leagueType, Class<R> responseType) {
    return new NflFullGameScheduleRequest(credentials, season, leagueType, responseType);
  }

  @Override
  protected <R> DefaultApiRequest<R> createRequest(ApiCredentials credentials,
      Class<R> responseType) {
    return new NflFullGameScheduleRequest(credentials, responseType);
  }

}
