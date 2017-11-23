package com.wesleyhome.stats.feed.request.api;

import com.wesleyhome.stats.feed.request.api.builder.plugins.SingleDate;

public enum SemanticDate implements SingleDate {
    YESTERDAY,
    TODAY,
    TOMORROW;

    @Override
    public String toStringValue() {
        return name().toLowerCase();
    }
}
