package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueEnum;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

class NflFullGameScheduleRequest<R> extends DefaultApiRequest<R> {

  NflFullGameScheduleRequest(ApiCredentials credentials, Class<R> responseType) {
    this(credentials, null, null, responseType);
  }

  NflFullGameScheduleRequest(ApiCredentials credentials, Integer startYear, LeagueType leagueType,
      Class<R> responseType) {
    super(credentials, "full_game_schedule", LeagueEnum.NFL, startYear, leagueType, responseType);
  }
}
