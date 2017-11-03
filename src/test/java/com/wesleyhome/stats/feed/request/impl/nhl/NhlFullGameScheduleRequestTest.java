package com.wesleyhome.stats.feed.request.impl.nhl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.impl.DefaultApiRequestTest;

import static com.wesleyhome.stats.feed.request.api.LeagueType.REGULAR;
import static com.wesleyhome.stats.feed.request.impl.SemanticDate.TOMORROW;
import static com.wesleyhome.stats.feed.request.impl.SemanticDate.YESTERDAY;

class NhlFullGameScheduleRequestTest extends DefaultApiRequestTest<NhlFullGameScheduleRequest<ObjectNode>, ObjectNode> {

    @Override
    protected NhlFullGameScheduleRequest<ObjectNode> getRequest(ApiCredentials credentials) {
        return new NhlApi(credentials)
                .fullGameSchedule()
                .season(2017)
                .leagueType(REGULAR)
                .between(YESTERDAY, TOMORROW)
                .team("min")
                .buildRequest(ObjectNode.class);
    }

    @Override
    protected void println(ObjectNode response) throws Exception {
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

}