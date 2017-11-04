package com.wesleyhome.stats.feed.request.api;

public interface BaseBuilder {

  <R> ApiRequest<R> buildRequest(Class<R> responseType);
}
