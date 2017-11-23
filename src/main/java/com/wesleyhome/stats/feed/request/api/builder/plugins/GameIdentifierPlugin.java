package com.wesleyhome.stats.feed.request.api.builder.plugins;

import com.wesleyhome.stats.feed.request.api.GameIdentifier;
import com.wesleyhome.stats.feed.request.api.GameIdentifiers;
import com.wesleyhome.stats.feed.request.api.builder.DefaultApiRequest;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;

import java.time.chrono.ChronoLocalDate;

import static com.wesleyhome.stats.feed.request.api.DateConverters.onDate;

public class GameIdentifierPlugin<B extends RequestBuilder<B>> implements ApiParameterPlugin {

    private final B builder;
    private GameIdentifier gameIdentifier;

    public GameIdentifierPlugin(B builder) {
        this.builder = builder;
    }

    public B forGame(Integer gameId) {
        return gameId(GameIdentifiers.gId(gameId));
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
