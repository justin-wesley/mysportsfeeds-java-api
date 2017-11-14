package com.wesleyhome.stats.feed.request.api;

import java.time.chrono.ChronoLocalDate;

public final class GameIdentifiers {

    private GameIdentifiers() {
    }

    public static GameIdentifier gId(Integer gameId) {
        return () -> gameId.toString();
    }

    public static GameIdentifier gId(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr) {
        return () -> String.format("%s-%s-%s", DateConverters.sd(gameDate).toDateValue(), awayTeamAbbr, homeTeamAbbr);
    }

    public static GameIdentifier gId(ChronoLocalDate gameDate, String awayTeamAbbr, String homeTeamAbbr, int gameIndex) {
        return () -> String.format("%s-%s-%s-%s", DateConverters.sd(gameDate).toDateValue(), awayTeamAbbr, homeTeamAbbr, gameIndex);
    }
}
