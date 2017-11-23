package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.GameIdentifierBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PositionsBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.RequestBuilderPlugin;

import java.util.concurrent.atomic.AtomicBoolean;

public final class GameStartingLineupBuilder extends
        RequestBuilder<GameStartingLineupBuilder> {

    public static final String FEED_NAME = "game_startinglineup";
    private AtomicBoolean expectedLineup = new AtomicBoolean(true);
    private final RequestBuilderPlugin lineupType;
    private final PositionsBuilderPlugin<GameStartingLineupBuilder> positions;
    private final GameIdentifierBuilderPlugin<GameStartingLineupBuilder> gameIdentifier;

    GameStartingLineupBuilder() {
        super(FEED_NAME);
        plugin(
                this.positions = new PositionsBuilderPlugin<>(this),
                this.gameIdentifier = new GameIdentifierBuilderPlugin<>(this),
                this.lineupType = request -> request.applyParameter("lineuptype", expectedLineup.get() ? "expected" : "actual")
        );
    }

    public PositionsBuilderPlugin<GameStartingLineupBuilder> positions() {
        return positions;
    }

    public GameIdentifierBuilderPlugin<GameStartingLineupBuilder> gameId() {
        return gameIdentifier;
    }

    public GameStartingLineupBuilder expectedLineup() {
        expectedLineup.set(true);
        return this;
    }

    public GameStartingLineupBuilder actualLineup() {
        expectedLineup.set(false);
        return this;
    }
}
