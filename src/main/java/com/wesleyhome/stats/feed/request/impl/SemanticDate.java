package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.DateConverter;
import com.wesleyhome.stats.feed.request.api.SingleDate;

public enum SemanticDate implements SingleDate {
    YESTERDAY,
    TODAY,
    TOMORROW;


    @Override
    public DateConverter converter() {
        return () -> name().toLowerCase();
    }
}
