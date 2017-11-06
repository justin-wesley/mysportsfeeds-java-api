package com.wesleyhome.stats.feed.request.impl.nfl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequestTest;

import static com.wesleyhome.stats.feed.MySportsFeedAPI.nfl;
import static com.wesleyhome.stats.feed.request.api.LeagueType.REGULAR;
import static com.wesleyhome.stats.feed.request.impl.JsonNodeAssert.assertThat;

class NflCumulativePlayerStatsRequestTest extends DefaultApiRequestTest {

    @Override
    protected ApiRequest<JsonNode> getRequest(ApiCredentials credentials) {
        return nfl(credentials)
                .cumulativePlayerStates()
                .season(2017)
                .leagueType(REGULAR)
                .team("min")
                .buildRequest(JsonNode.class);
    }

    @Override
    protected void assertResponse(JsonNode response) {
        assertThat(response)
                .child("cumulativeplayerstats")
                .child("lastUpdatedOn")
                .isNodeType(JsonNodeType.STRING);
    }
}