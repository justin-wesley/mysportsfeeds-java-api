package com.wesleyhome.stats.feed.request.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.ApiRequest;

import static com.wesleyhome.stats.feed.MySportsFeedAPI.nhl;
import static com.wesleyhome.stats.feed.request.api.LeagueType.REGULAR;
import static com.wesleyhome.stats.feed.request.api.SemanticDate.TOMORROW;
import static com.wesleyhome.stats.feed.request.api.SemanticDate.YESTERDAY;
import static com.wesleyhome.stats.feed.request.impl.JsonNodeAssert.assertThat;

class NhlFullGameScheduleRequestTest extends DefaultApiRequestTest {

    @Override
    protected ApiRequest getRequest(ApiCredentials credentials) {
        return nhl(credentials)
                .fullGameSchedule()
                .season(2017)
                .leagueType(REGULAR)
                .between(YESTERDAY, TOMORROW)
                .team("min") // Go WILD!!!
                .team("chi")
                .buildRequest(JsonNode.class);
    }

    @Override
    protected void assertResponse(JsonNode response) {
        assertThat(response)
                .child("fullgameschedule")
                .child("lastUpdatedOn")
                .isNodeType(JsonNodeType.STRING);
    }


}