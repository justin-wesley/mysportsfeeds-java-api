package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class PagingBuilderPlugin<B extends RequestBuilder<B>> implements RequestBuilderPlugin {
    private final B builder;
    private String sort;
    private Integer offset;
    private Integer limit;

    public PagingBuilderPlugin(B builder) {
        this.builder = builder;
    }

    public PagingBuilderPlugin<B> sort(String sort) {
        this.sort = sort;
        return this;
    }

    public PagingBuilderPlugin<B> offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public PagingBuilderPlugin<B> limit(Integer limit) {
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
