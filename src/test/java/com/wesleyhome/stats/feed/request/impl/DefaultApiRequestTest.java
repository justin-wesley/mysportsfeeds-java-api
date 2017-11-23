package com.wesleyhome.stats.feed.request.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.builder.*;
import com.wesleyhome.stats.feed.request.api.credentials.ResourceBundleCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.function.Consumer;

import static com.wesleyhome.stats.feed.MySportsFeedAPI.nhl;
import static com.wesleyhome.stats.feed.request.api.LeagueType.REGULAR;
import static com.wesleyhome.stats.feed.request.api.SemanticDate.TODAY;
import static com.wesleyhome.stats.feed.request.api.SemanticDate.YESTERDAY;
import static com.wesleyhome.stats.feed.request.impl.JsonNodeAssert.assertThat;

public class DefaultApiRequestTest {
    private ApiCredentials apiCredentials;


//    protected abstract B getRequest(ApiCredentials credentials);

    @BeforeEach
    void testSetup() throws Exception {
        this.apiCredentials = new ResourceBundleCredentials("application");
    }

    @Test
    void testFullGameSchedule() throws Exception {
        FullGameScheduleBuilder builder = nhl(apiCredentials)
                .fullGameSchedule()
                .season(2017)
                .leagueType(REGULAR)
                .date()
                .between(YESTERDAY, TODAY)
                .team()
                .add("min", "chi");
        runTest(builder, this::assertFullGameScheduleResponse);
    }

    @Test
    void testCumulativePlayerStats() throws Exception {
        CumulativePlayerStatsBuilder builder = nhl(apiCredentials)
                .cumulativePlayerStats()
                .season(2017)
                .leagueType(REGULAR)
                .team().add("min");
        runTest(builder, this::assertCumulativePlayerStats);
    }

    @Test
    void testCurrentSeason() throws Exception {
        CurrentSeasonBuilder builder = nhl(apiCredentials).currentSeason();
        runTest(builder, this::assertCurrentSeason);
    }

    @Test
    void testDailyGameSchedule() throws Exception {
        DailyGameScheduleBuilder builder = nhl(apiCredentials).dailyGameSchedule()
                .onDate(LocalDate.now().minusDays(10))
                .team().add("min", "chi");
        runTest(builder, this::assertDailyGameSchedule);
    }

    private void assertDailyGameSchedule(JsonNode response) {
        assertResponse(response, "dailygameschedule");
    }

    private void assertCurrentSeason(JsonNode response) {
        assertResponse(response, "currentseason");
    }

    private void assertCumulativePlayerStats(JsonNode response) {
        assertResponse(response, "cumulativeplayerstats");
    }

    private void assertFullGameScheduleResponse(JsonNode response) {
        assertResponse(response, "fullgameschedule");
    }

    private void assertResponse(JsonNode response, String firstChild) {
        assertResponse(response, firstChild, JsonNodeType.STRING);
    }

    private void assertResponse(JsonNode response, String firstChild, JsonNodeType type) {
        assertThat(response)
                .child(firstChild).child("lastUpdatedOn").isNodeType(type);
    }

    private void runTest(RequestBuilder<?> builder, Consumer<JsonNode> testAssertion) throws Exception {
        JsonNode request = builder.request(JsonNode.class);
        testAssertion.accept(request);
    }

}