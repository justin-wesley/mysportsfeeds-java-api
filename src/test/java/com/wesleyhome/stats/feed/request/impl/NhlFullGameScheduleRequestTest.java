package com.wesleyhome.stats.feed.request.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.builder.FullGameScheduleBuilder;

import static com.wesleyhome.stats.feed.MySportsFeedAPI.nhl;
import static com.wesleyhome.stats.feed.request.api.LeagueType.REGULAR;
import static com.wesleyhome.stats.feed.request.api.SemanticDate.TOMORROW;
import static com.wesleyhome.stats.feed.request.api.SemanticDate.YESTERDAY;
import static com.wesleyhome.stats.feed.request.impl.JsonNodeAssert.assertThat;

class NhlFullGameScheduleRequestTest extends DefaultApiRequestTest<FullGameScheduleBuilder> {

    @Override
    protected FullGameScheduleBuilder getRequest(ApiCredentials credentials) {
        return nhl(credentials)
                .fullGameSchedule()
                .season(2017)
                .leagueType(REGULAR)
                .between(YESTERDAY, TOMORROW)
                .teams("min", "chi"); // Go WILD!!!
    }

    @Override
    protected void assertResponse(JsonNode response) {
        assertThat(response)
                .child("fullgameschedule")
                .child("lastUpdatedOn")
                .isNodeType(JsonNodeType.STRING);
    }


}