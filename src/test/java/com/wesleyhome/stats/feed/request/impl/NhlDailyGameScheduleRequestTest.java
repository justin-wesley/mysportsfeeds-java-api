package com.wesleyhome.stats.feed.request.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.builder.DailyGameScheduleBuilder;

import java.time.LocalDate;
import java.time.Month;

import static com.wesleyhome.stats.feed.MySportsFeedAPI.nhl;
import static com.wesleyhome.stats.feed.request.api.LeagueType.REGULAR;
import static com.wesleyhome.stats.feed.request.impl.JsonNodeAssert.assertThat;

class NhlDailyGameScheduleRequestTest extends DefaultApiRequestTest<DailyGameScheduleBuilder> {

    @Override
    protected DailyGameScheduleBuilder getRequest(ApiCredentials credentials) {
        return nhl(credentials)
                .dailyGameSchedule()
                .team("min")
                .onDate(LocalDate.of(2017, Month.NOVEMBER, 20))
                .season(2017)
                .leagueType(REGULAR);
    }

    @Override
    protected void assertResponse(JsonNode response) {
        assertThat(response)
                .child("dailygameschedule")
                .child("lastUpdatedOn")
                .isNodeType(JsonNodeType.STRING);
    }
}