package com.wesleyhome.stats.feed.exception;

public class MySportsFeedException extends RuntimeException {

  public MySportsFeedException() {
    super();
  }

  public MySportsFeedException(String s) {
    super(s);
  }

  public MySportsFeedException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public MySportsFeedException(Throwable throwable) {
    super(throwable);
  }

  protected MySportsFeedException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
