package com.wesleyhome.stats.feed.request.api.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
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
    private ApiCredentials apiCredentials;
    private static final LocalDate NOV_22_2017 = LocalDate.of(2017, Month.NOVEMBER, 22);

    @BeforeAll
    static void classSetup() throws Exception {
        DefaultApiRequest.disableSslVerification(); // setup SSL resolver
    }

    @BeforeEach
    void testSetup() throws Exception {
        this.apiCredentials = new ResourceBundleCredentials(APPLICATION);
    }

    private StatsApi api() {
        return nhl(apiCredentials);
    }

    @Test
    void rosterPlayers() throws Exception {
        runTest(api().rosterPlayers().season(2017).onDate(NOV_22_2017).team().add("min", "chi"), ROSTER_PLAYERS);
    }

    @Test
    void scoreboard() throws Exception {
        runTest(api().scoreboard().season(2017).forDate(NOV_22_2017), SCOREBOARD);
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
    void fullGameSchedule() throws Exception {
        FullGameScheduleBuilder builder = api()
                .fullGameSchedule()
                .season(2017)
                .leagueType(REGULAR)
                .date()
                .onDate(NOV_22_2017)
                .team()
                .add("min", "chi");
        runTest(builder, FULL_GAME_SCHEDULE);
    }

    @Test
    void cumulativePlayerStats() throws Exception {
        CumulativePlayerStatsBuilder builder = api()
                .cumulativePlayerStats()
                .season(2017)
                .leagueType(REGULAR)
                .team().add("min");
        runTest(builder, CUMULATIVE_PLAYER_STATS);
    }

    @Test
    void currentSeason() throws Exception {
        CurrentSeasonBuilder builder = api().currentSeason();
        runTest(builder, CURRENT_SEASON);
    }

    @Test
    void dailyGameSchedule() throws Exception {
        DailyGameScheduleBuilder builder = api().dailyGameSchedule()
                .onDate(NOV_22_2017)
                .team().add("min", "chi");
        runTest(builder, DAILY_GAME_SCHEDULE);
    }

    @Test
    void gamePlayByPlay() throws Exception {
        GamePlayByPlayBuilder builder = api().gamePlayByPlay()
                .gameId()
                .forGame(NOV_22_2017, "min", "buf");
        runTest(builder, GAME_PLAY_BY_PLAY);
    }

    @Test
    void gameBoxScore() throws Exception {
        GameBoxScoreBuilder builder = api().gameBoxScore()
                .gameId()
                .forGame(NOV_22_2017, "min", "buf");
        runTest(builder, GAME_BOX_SCORE);
    }

    private void assertResponse(JsonNode response, String firstChild) {
        assertResponse(response, firstChild, JsonNodeType.STRING);
    }

    private void assertResponse(JsonNode response, String firstChild, JsonNodeType type) {
        assertThat(response)
                .child(firstChild).child("lastUpdatedOn").isNodeType(type);
    }

    private void runTest(RequestBuilder<?> builder, String firstChild) throws Exception {
        JsonNode request = builder.request(JsonNode.class);
        assertResponse(request, firstChild);
    }

    private void runTest(RequestBuilder<?> builder, String firstChild, JsonNodeType type) throws Exception {
        JsonNode request = builder.request(JsonNode.class);
        assertResponse(request, firstChild, type);
    }
}