package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PlayTypeBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public PlayTypeBuilderPlugin(B builder) {
        super(builder, "playtype");
    }
}
