package com.wesleyhome.stats.feed.request.api;

import java.time.chrono.ChronoLocalDate;

import static com.wesleyhome.stats.feed.request.api.DateConverters.onDate;

public final class GameIdentifiers {

    private GameIdentifiers() {
    }

    public static GameIdentifier gId(Integer gameId) {
        return () -> gameId.toString();
    }

    public static GameIdentifier gId(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr) {
        return () -> String.format("%s-%s-%s", onDate(gameDate).toStringValue(), awayTeamAbbr, homeTeamAbbr);
    }

    public static GameIdentifier gId(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr, int gameIndex) {
        return () -> String.format("%s-%s-%s-%s", onDate(gameDate).toStringValue(), awayTeamAbbr, homeTeamAbbr, gameIndex);
    }
}
