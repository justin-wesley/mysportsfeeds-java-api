package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.GameStatus;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class GameStatusBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, GameStatus> {
    public GameStatusBuilderPlugin(B builder) {
        super(builder, "status");
    }
}
