package com.wesleyhome.stats.feed.request.api;

public enum GameStatus {
  UNPLAYED,
  IN_PROGRESS,
  POSTGAME_REVIEWING,
  FINAL;

  @Override
  public String toString() {
    return name().toLowerCase().replace('_', '-');
  }
}
