package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.GameIdentifierConverter;

import java.time.chrono.ChronoLocalDate;

public final class GameIdentifiers {

    private GameIdentifiers() {
    }

    public static GameIdentifierConverter gId(Integer gameId) {
        return () -> gameId.toString();
    }

    public static GameIdentifierConverter gId(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr) {
        return () -> String.format("%s-%s-%s", DateConverters.sd(gameDate).convert(), awayTeamAbbr, homeTeamAbbr);
    }

    public static GameIdentifierConverter gId(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr, int gameIndex) {
        return () -> String.format("%s-%s-%s-%s", DateConverters.sd(gameDate).convert(), awayTeamAbbr, homeTeamAbbr, gameIndex);
    }
}
