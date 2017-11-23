package com.wesleyhome.stats.feed.request.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.builder.RequestBuilder;
import com.wesleyhome.stats.feed.request.api.credentials.ResourceBundleCredentials;
import org.junit.jupiter.api.Test;

public abstract class DefaultApiRequestTest<B extends RequestBuilder<B>> {


    protected abstract B getRequest(ApiCredentials credentials);


    @Test
    void sendRequest() throws Exception {
        JsonNode response = getRequest(new ResourceBundleCredentials("application")).request(JsonNode.class);
//        println(response);
        assertResponse(response);
    }

    protected abstract void assertResponse(JsonNode response);

    private void println(JsonNode response) throws Exception {
        System.out
                .println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

}