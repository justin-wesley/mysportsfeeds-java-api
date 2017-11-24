package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.ListManagerBuilder;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class ListManagerPlugin<B extends RequestBuilder<B>, T> implements RequestBuilderPlugin {

    protected final B builder;
    private final String parameterName;
    private ListManagerBuilder<T> list = new ListManagerBuilder<>();

    public ListManagerPlugin(B builder, String parameterName) {
        this.builder = builder;
        this.parameterName = parameterName;
    }

    public B add(T val1, T... vals) {
        list.add(val1, vals);
        return builder;
    }

    @Override
    public final void buildRequest(DefaultApiRequest request) {
        request.applyParameters(this.parameterName, list);
    }

}
