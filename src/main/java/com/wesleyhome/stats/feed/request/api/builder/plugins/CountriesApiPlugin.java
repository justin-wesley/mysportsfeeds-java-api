package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class CountriesApiPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, String> {
    public CountriesApiPlugin(B builder) {
        super(builder, "countries");
    }
}
