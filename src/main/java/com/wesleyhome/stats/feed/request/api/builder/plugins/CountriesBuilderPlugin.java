package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class CountriesBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public CountriesBuilderPlugin(B builder) {
        super(builder, "country");
    }
}
