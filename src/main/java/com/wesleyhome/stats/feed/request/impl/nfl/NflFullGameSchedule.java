package com.wesleyhome.stats.feed.request.impl.nfl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.GameStatus;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.impl.BaseFullGameSchedule;
import java.util.List;

public class NflFullGameSchedule extends BaseFullGameSchedule {

  NflFullGameSchedule(ApiCredentials credentials, Integer season, LeagueType leagueType,
      List<String> teams, DateConverter date, List<GameStatus> statuses, String sort,
      Integer offset, Integer limit, Boolean force) {
    super(season, date, force, leagueType, teams, offset, credentials, limit, sort, statuses);
  }

}
