package com.wesleyhome.stats.feed.request.api;

public enum SemanticDate implements SingleDate {
    YESTERDAY,
    TODAY,
    TOMORROW;


    @Override
    public DateConverter converter() {
        return () -> name().toLowerCase();
    }
}
