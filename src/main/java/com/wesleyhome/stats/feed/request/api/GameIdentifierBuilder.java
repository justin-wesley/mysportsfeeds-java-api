package com.wesleyhome.stats.feed.request.api;

import java.time.chrono.ChronoLocalDate;

import static com.wesleyhome.stats.feed.request.api.GameIdentifiers.gId;

public class GameIdentifierBuilder<GI extends GameIdentifierHolder<GI>> {


    private final GI gameIdentifierHolder;
    private ChronoLocalDate gameDate;
    private String awayTeamAbbr;
    private String homeTeamAbbr;
    private Integer gameIndex;

    public GameIdentifierBuilder(GI gameIdentifierHolder) {
        this.gameIdentifierHolder = gameIdentifierHolder;
    }

    public GameIdentifierBuilder onDate(ChronoLocalDate gameDate) {
        this.gameDate = gameDate;

        return this;
    }

    public GameIdentifierBuilder awayTeam(String awayTeamAbbr) {
        this.awayTeamAbbr = awayTeamAbbr;
        return this;
    }

    public GameIdentifierBuilder homeTeam(String homeTeamAbbr) {
        this.homeTeamAbbr = homeTeamAbbr;
        return this;
    }

    public GameIdentifierBuilder gameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
        return this;
    }

    public GI toGameIdentifier() {
        GameIdentifier gameIdentifier = gameIndex == null ? gId(gameDate, awayTeamAbbr, homeTeamAbbr) : gId(gameDate, awayTeamAbbr, homeTeamAbbr, gameIndex);
        return gameIdentifierHolder.gameId(gameIdentifier);
    }
}
