package com.wesleyhome.stats.feed.request.api.builder;

abstract class LimitBuilder<B extends LimitBuilder<B>> extends ForceBuilder<B> {
    private String sort;
    private Integer offset;
    private Integer limit;

    protected LimitBuilder(String feedName) {
        super(feedName);
    }

    public B sort(String sort) {
        this.sort = sort;
        return SELF;
    }

    public B offset(Integer offset) {
        this.offset = offset;
        return SELF;
    }

    public B limit(Integer limit) {
        this.limit = limit;
        return SELF;
    }

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        super.buildRequest(request);
        request.applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit)
        ;
    }
}
