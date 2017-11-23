package com.wesleyhome.stats.feed.request.api.builder;

abstract class ForceBuilder<B extends ForceBuilder<B>> extends RequestBuilder<B> {
    private Boolean force;

    protected ForceBuilder(String feedName) {
        super(feedName);
    }

    public B force(Boolean force) {
        this.force = force;
        return SELF;
    }

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        request.applyParameter("force", force);
    }
}
