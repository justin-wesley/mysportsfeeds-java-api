package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.GameIdentifier;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

import java.time.chrono.ChronoLocalDate;

import static com.wesleyhome.stats.feed.request.api.builder.plugins.DateConverters.onDate;

public class GameListBuilderPlugin<B extends RequestBuilder<B>> extends ListManagerPlugin<B, GameIdentifier> {

    private final GameIdentifierBuilderPlugin<B> gameIdentifier;

    public GameListBuilderPlugin(B builder, String parameterName) {
        super(builder, parameterName);
        gameIdentifier = new GameIdentifierBuilderPlugin<>(builder);
    }

    @Override
    public B add(GameIdentifier val1, GameIdentifier... vals) {
        return super.add(val1, vals);
    }

    public B add(Integer gameId, Integer... gameIds) {
        B builder = add(() -> gameId.toString());
        if (gameIds != null && gameIds.length > 0) {
            for (Integer _gameId :
                    gameIds) {
                builder = add(() -> _gameId.toString());
            }
        }
        return builder;
    }

    public GameListBuilderPlugin<B> add(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr) {
        add(() -> String.format("%s-%s-%s", onDate(gameDate).toStringValue(), awayTeamAbbr, homeTeamAbbr));
        return this;
    }

    public B builder() {
        return this.builder;
    }
}
