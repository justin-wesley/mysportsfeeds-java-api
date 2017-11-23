package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.League;

import java.util.function.Supplier;

public class StatsApi {
    private final ApiCredentials credentials;
    private final League league;

    public StatsApi(ApiCredentials credentials, League league) {
        this.credentials = credentials;
        this.league = league;
    }

    public FullGameScheduleBuilder fullGameSchedule() {
        return b(FullGameScheduleBuilder::new);
    }

    public DailyGameScheduleBuilder dailyGameSchedule() {
        return b(DailyGameScheduleBuilder::new);
    }

    public DailyPlayerStatsBuilder dailyPlayerStats() {
        return b(DailyPlayerStatsBuilder::new);
    }

    public CumulativePlayerStatsBuilder cumulativePlayerStats() {
        return b(CumulativePlayerStatsBuilder::new);
    }

    public GamePlayByPlayBuilder gamePlayByPlay() {
        return b(GamePlayByPlayBuilder::new);
    }

    public GameBoxScoreBuilder gameBoxScore() {
        return b(GameBoxScoreBuilder::new);
    }

    public CurrentSeasonBuilder currentSeason() {
        return b(CurrentSeasonBuilder::new);
    }

    public ScoreboardBuilder scoreboard() {
        return b(ScoreboardBuilder::new);
    }

    public RosterPlayersBuilder rosterPlayers() {
        return b(RosterPlayersBuilder::new);
    }

    public OverallTeamStandingsBuilder overallTeamStandings() {
        return b(OverallTeamStandingsBuilder::new);
    }

    public ConferenceTeamStandingsBuilder conferenceTeamStandings() {
        return b(ConferenceTeamStandingsBuilder::new);
    }

    public DivisionTeamStandingsBuilder divisionTeamStandings() {
        return b(DivisionTeamStandingsBuilder::new);
    }

    public PlayoffTeamStandingsBuilder playoffTeamStandings() {
        return b(PlayoffTeamStandingsBuilder::new);
    }

    public DailyFantasyLeagueBuilder dailyFantasyLeague() {
        return b(DailyFantasyLeagueBuilder::new);
    }

    public LatestUpdatesBuilder latestUpdates() {
        return b(LatestUpdatesBuilder::new);
    }

    private <B extends RequestBuilder<B>> B b(Supplier<B> supplier) {
        return supplier.get().credentials(credentials).league(league);
    }

}
