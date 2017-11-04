package com.wesleyhome.stats.feed;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.impl.BasicApiCredentials;
import com.wesleyhome.stats.feed.request.impl.nfl.NflApi;
import com.wesleyhome.stats.feed.request.impl.nhl.NhlApi;

/**
 *
 */
public final class MySportsFeedAPI {

  public static NhlApi nhl(ApiCredentials credentials) {
    return new NhlApi(credentials);
  }

  public static NhlApi nhl(String username, String password) {
    return new NhlApi(new BasicApiCredentials(username, password));
  }

  public static NflApi nfl(ApiCredentials credentials) {
    return new NflApi(credentials);
  }

  public static NflApi nfl(String username, String password) {
    return new NflApi(new BasicApiCredentials(username, password));
  }
}
