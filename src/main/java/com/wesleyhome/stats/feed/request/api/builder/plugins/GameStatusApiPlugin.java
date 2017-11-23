package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.GameStatus;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

public class GameStatusApiPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, GameStatus> {
    public GameStatusApiPlugin(B builder) {
        super(builder, "status");
    }
}
