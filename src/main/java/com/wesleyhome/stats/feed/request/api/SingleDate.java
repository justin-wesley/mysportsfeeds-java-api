package com.wesleyhome.stats.feed.request.api;

public interface SingleDate {

  DateConverter converter();

  default String convert() {
    return converter().convert();
  }

}
