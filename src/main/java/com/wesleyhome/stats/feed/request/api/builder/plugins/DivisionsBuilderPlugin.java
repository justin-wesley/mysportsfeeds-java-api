package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class DivisionsBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public DivisionsBuilderPlugin(B builder) {
        super(builder, "division");
    }
}
