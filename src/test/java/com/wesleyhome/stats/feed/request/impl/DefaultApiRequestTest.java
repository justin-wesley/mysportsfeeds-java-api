package com.wesleyhome.stats.feed.request.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.api.credentials.ResourceBundleCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class DefaultApiRequestTest {

    private ApiRequest request;

    @BeforeEach
    void setUp() throws Exception {
        request = getRequest(new ResourceBundleCredentials("application"));
    }

    protected abstract ApiRequest getRequest(ApiCredentials credentials);


    @Test
    void sendRequest() throws Exception {
        JsonNode response = request.sendRequest(JsonNode.class);
//        println(response);
        assertResponse(response);
    }

    protected abstract void assertResponse(JsonNode response);

    private void println(JsonNode response) throws Exception {
        System.out
                .println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

}