package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.GameIdentifier;
import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

import java.time.chrono.ChronoLocalDate;

import static com.wesleyhome.stats.feed.request.api.builder.plugins.DateConverters.onDate;

public class GameIdentifierBuilderPlugin<B extends RequestBuilder<B>> implements RequestBuilderPlugin {

    private final B builder;
    private GameIdentifier gameIdentifier;

    public GameIdentifierBuilderPlugin(B builder) {
        this.builder = builder;
    }

    public B forGame(Integer gameId) {
        return gameId(() -> gameId.toString());
    }


    public B forGame(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr) {
        return gameId(() -> String.format("%s-%s-%s", onDate(gameDate).toStringValue(), awayTeamAbbr, homeTeamAbbr));
    }

    public B forGame(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr, int gameIndex) {
        return gameId(() -> String.format("%s-%s-%s-%s", onDate(gameDate).toStringValue(), awayTeamAbbr, homeTeamAbbr, gameIndex));
    }


    private B gameId(GameIdentifier gameIdentifier) {
        this.gameIdentifier = gameIdentifier;
        return builder;
    }

    @Override
    public void buildRequest(DefaultApiRequest request) {
        request.applyParameter("gameid", this.gameIdentifier);
    }
}
