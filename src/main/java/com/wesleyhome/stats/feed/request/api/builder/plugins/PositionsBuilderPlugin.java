package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PositionsBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public PositionsBuilderPlugin(B builder) {
        super(builder, "positions");
    }
}
