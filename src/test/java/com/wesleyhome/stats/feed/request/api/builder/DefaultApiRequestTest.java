package com.wesleyhome.stats.feed.request.api.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.DailyFantasyLeague;
import com.wesleyhome.stats.feed.request.api.LeagueType;
import com.wesleyhome.stats.feed.request.api.credentials.ResourceBundleCredentials;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.wesleyhome.stats.feed.MySportsFeedAPI.nhl;
import static com.wesleyhome.stats.feed.request.api.LeagueType.REGULAR;
import static com.wesleyhome.stats.feed.request.api.builder.JsonNodeAssert.assertThat;

public class DefaultApiRequestTest {
    public static final String GAME_BOX_SCORE = "gameboxscore";
    public static final String FULL_GAME_SCHEDULE = "fullgameschedule";
    public static final String CUMULATIVE_PLAYER_STATS = "cumulativeplayerstats";
    public static final String CURRENT_SEASON = "currentseason";
    public static final String DAILY_GAME_SCHEDULE = "dailygameschedule";
    public static final String GAME_PLAY_BY_PLAY = "gameplaybyplay";
    public static final String SCOREBOARD = "scoreboard";
    public static final String DAILY_PLAYER_STATS = "dailyplayerstats";
    public static final String APPLICATION = "application";
    public static final String ROSTER_PLAYERS = "rosterplayers";
    public static final String MINNESOTA = "min";
    public static final String CHICAGO = "chi";
    public static final String BUFFALO = "buf";
    public static final String GAME_STARTING_LINEUP = "gamestartinglineup";
    public static final String LATEST_UPDATES = "latestupdates";
    public static final String DAILY_DFS = "dailydfs";
    public static final String LAST_UPDATED_ON = "lastUpdatedOn";
    public static final String PLAYER_GAME_LOGS = "playergamelogs";
    public static final String TEAM_GAME_LOGS = "teamgamelogs";
    private ApiCredentials apiCredentials;
    private static final LocalDate NOV_22_2017 = LocalDate.of(2017, Month.NOVEMBER, 22);

    @BeforeAll
    static void classSetup() {
//        DefaultApiRequest.disableSslVerification(); // setup SSL resolver
    }

    @BeforeEach
    void testSetup() {
        this.apiCredentials = new ResourceBundleCredentials("application");
    }

    private StatsApi api() {
        return nhl(apiCredentials);
    }

    @Test
    void cumulativePlayerStats() throws Exception {
        CumulativePlayerStatsBuilder builder = api()
                .cumulativePlayerStats()
                .season(2017)
                .leagueType(REGULAR)
                .team().add(MINNESOTA);
        runTest(builder, CUMULATIVE_PLAYER_STATS);
    }

    @Test
    void fullGameSchedule() throws Exception {
        FullGameScheduleBuilder builder = api()
                .fullGameSchedule()
                .season(2017)
                .leagueType(REGULAR)
                .date()
                .onDate(NOV_22_2017)
                .team()
                .add(MINNESOTA, CHICAGO);
        runTest(builder, FULL_GAME_SCHEDULE);
    }

    @Test
    void dailyGameSchedule() throws Exception {
        DailyGameScheduleBuilder builder = api().dailyGameSchedule()
                .onDate(NOV_22_2017)
                .team().add(MINNESOTA, CHICAGO);
        runTest(builder, DAILY_GAME_SCHEDULE);
    }

    @Test
    void dailyPlayerStats() throws Exception {
        DailyPlayerStatsBuilder builder = api()
                .dailyPlayerStats()
                .season(2017)
                .forDate(NOV_22_2017);
        runTest(builder, DAILY_PLAYER_STATS);
    }

    @Test
    void gameBoxScore() throws Exception {
        GameBoxScoreBuilder builder = api().gameBoxScore()
                .leagueType(LeagueType.REGULAR)
                .season(2017)
                .gameId()
                .forGame(NOV_22_2017, MINNESOTA, BUFFALO);
        runTest(builder, GAME_BOX_SCORE);
    }

    @Test
    void scoreboard() throws Exception {
        runTest(api().scoreboard().season(2017).forDate(NOV_22_2017), SCOREBOARD);
    }

    @Test
    void gamePlayByPlay() throws Exception {
        GamePlayByPlayBuilder builder = api().gamePlayByPlay()
                .leagueType(LeagueType.REGULAR)
                .season(2017)
                .gameId()
                .forGame(NOV_22_2017, MINNESOTA, BUFFALO);
        runTest(builder, GAME_PLAY_BY_PLAY);
    }

    @Test
    void rosterPlayers() throws Exception {
        runTest(api().rosterPlayers().season(2017).onDate(NOV_22_2017).team().add(MINNESOTA, CHICAGO), ROSTER_PLAYERS);
    }

    @Test
    void gameStartingLineup() throws Exception {
        runTest(api()
                        .startingLineup()
                        .actualLineup()
                        .leagueType(LeagueType.REGULAR)
                        .season(2017)
                        .gameId()
                        .forGame(NOV_22_2017, MINNESOTA, BUFFALO)
                , GAME_STARTING_LINEUP);
    }

    @Test
    void playerGameLogs() throws Exception {
        PlayerGameLogsBuilder builder = api()
                .playerGameLogs()
                .games()
                .add(NOV_22_2017, MINNESOTA, BUFFALO)
                .builder();
        runTest(builder, PLAYER_GAME_LOGS);

    }

    @Test
    void teamGameLogs() throws Exception {
        TeamGameLogsBuilder builder = api()
                .teamGameLogs()
                .games()
                .add(NOV_22_2017, MINNESOTA, BUFFALO)
                .builder();
        runTest(builder, TEAM_GAME_LOGS);

    }

    @Test
    void overallTeamStandings() throws Exception {
        OverallTeamStandingsBuilder builder = api()
                .overallTeamStandings()
                .teams().add(MINNESOTA)
                .season(2017);
        runTest(builder, "overallteamstandings");
    }

    @Test
    void divisionTeamStandings() throws Exception {
        DivisionTeamStandingsBuilder builder = api()
                .divisionTeamStandings()
                .teams().add(MINNESOTA)
                .season(2017);
        runTest(builder, "divisionteamstandings");
    }

    @Test
    void conferenceTeamStandings() throws Exception {
        ConferenceTeamStandingsBuilder builder = api()
                .conferenceTeamStandings()
                .teams().add(MINNESOTA)
                .season(2017);
        runTest(builder, "conferenceteamstandings");
    }

    @Test
    void playoffTeamStandings() throws Exception {
        PlayoffTeamStandingsBuilder builder = api()
                .playoffTeamStandings()
                .teams().add(MINNESOTA)
                .season(2017);
        runTest(builder, "playoffteamstandings");
    }


    @Test
    void currentSeason() throws Exception {
        CurrentSeasonBuilder builder = api().currentSeason().forDate(NOV_22_2017);
        runTest(builder, CURRENT_SEASON);
    }

    @Test
    void latestUpdate() throws Exception {
        runTest(api().latestUpdates(), LATEST_UPDATES);
    }

    @Test
    void dailyFantasy() throws Exception {
        runTest(api()
                .dailyFantasyLeague()
                .fantasyLeagues()
                .add(DailyFantasyLeague.DRAFT_KINGS)
                .onDate(NOV_22_2017), DAILY_DFS);
    }

    private void assertResponse(JsonNode response, String firstChild) {
        assertResponse(response, firstChild, JsonNodeType.STRING);
    }

    private void assertResponse(JsonNode response, String firstChild, JsonNodeType type) {
        assertThat(response)
                .child(firstChild).child(LAST_UPDATED_ON).isNodeType(type);
    }

    private void runTest(RequestBuilder<?> builder, String firstChild) {
        JsonNode request = builder.request(JsonNode.class);
        assertResponse(request, firstChild);
    }

    private void runTest(RequestBuilder<?> builder, String firstChild, JsonNodeType type) {
        JsonNode request = builder.request(JsonNode.class);
        assertResponse(request, firstChild, type);
    }
}