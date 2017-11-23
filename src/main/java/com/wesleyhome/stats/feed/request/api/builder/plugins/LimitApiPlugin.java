package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class LimitApiPlugin<B extends RequestBuilder<B>> implements ApiParameterPlugin {
    private final B builder;
    private String sort;
    private Integer offset;
    private Integer limit;

    public LimitApiPlugin(B builder) {
        this.builder = builder;
    }

    public LimitApiPlugin<B> sort(String sort) {
        this.sort = sort;
        return this;
    }

    public LimitApiPlugin<B> offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public LimitApiPlugin<B> limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public B builder() {
        return builder;
    }

    @Override
    public void buildRequest(DefaultApiRequest request) {
        request.applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit);
    }
}
