package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;

public abstract class BaseApi {

  private final ApiCredentials credentials;

  protected BaseApi(ApiCredentials credentials) {
    this.credentials = credentials;
  }

  protected ApiCredentials getCredentials() {
    return credentials;
  }
}
