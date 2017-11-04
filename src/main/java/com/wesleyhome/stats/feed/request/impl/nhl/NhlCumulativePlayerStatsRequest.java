package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.LeagueEnum;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequest;

class NhlCumulativePlayerStatsRequest<R> extends DefaultApiRequest<R> {

  NhlCumulativePlayerStatsRequest(ApiCredentials credentials, Class<R> responseType) {
    this(credentials, null, null, responseType);
  }

  NhlCumulativePlayerStatsRequest(ApiCredentials credentials, Integer startYear,
      LeagueType leagueType,
      Class<R> responseType) {
    super(credentials, "cumulative_player_stats", LeagueEnum.NHL, startYear, leagueType,
        responseType);
  }
}
