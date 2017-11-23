package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.GameIdentifierBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PeriodBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PlayTypeBuilderPlugin;

public final class GamePlayByPlayBuilder extends RequestBuilder<GamePlayByPlayBuilder> {

    public static final String FEED_NAME = "game_playbyplay";
    private GameIdentifierBuilderPlugin<GamePlayByPlayBuilder> gameIdentifier;
    private PeriodBuilderPlugin<GamePlayByPlayBuilder> period;
    private PlayTypeBuilderPlugin<GamePlayByPlayBuilder> playType;

    GamePlayByPlayBuilder() {
        super(FEED_NAME);
        plugin(
                this.gameIdentifier = new GameIdentifierBuilderPlugin<>(SELF),
                this.period = new PeriodBuilderPlugin<>(SELF),
                this.playType = new PlayTypeBuilderPlugin<>(SELF)
        );
    }

    public GameIdentifierBuilderPlugin<GamePlayByPlayBuilder> gameId() {
        return gameIdentifier;
    }

    public PlayTypeBuilderPlugin<GamePlayByPlayBuilder> playType() {
        return playType;
    }

    public PeriodBuilderPlugin<GamePlayByPlayBuilder> period() {
        return period;
    }
}
