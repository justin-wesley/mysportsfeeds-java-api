package com.wesleyhome.stats.feed.request.api;

public class RequestValidationException extends RuntimeException {

    public RequestValidationException(String s) {
        super(s);
    }

    public RequestValidationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
