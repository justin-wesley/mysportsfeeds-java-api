package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PositionsApiPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public PositionsApiPlugin(B builder) {
        super(builder, "positions");
    }
}
