package com.wesleyhome.stats.feed.request.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class JsonNodeAssert extends AbstractAssert<JsonNodeAssert, JsonNode> {

    private JsonNodeAssert(JsonNode objectNode) {
        super(objectNode, JsonNodeAssert.class);
    }

    public static JsonNodeAssert assertThat(JsonNode node) {
        return new JsonNodeAssert(node);
    }

    public JsonNodeAssert hasChild(String childName) {
        isNotNull();
        JsonNode jsonNode = actual.get(childName);
        if (jsonNode == null) {
            failWithMessage("Expected child '%s' to exist in %s didn't", childName, prettyPrint(actual));
        }
        return this;
    }

    public JsonNodeAssert isObject() {
        isNotNull();
        isNodeType(JsonNodeType.OBJECT);
        return this;
    }

    public JsonNodeAssert isNodeType(JsonNodeType type) {
        JsonNodeType actualNodeType = actual.getNodeType();
        if (!actualNodeType.equals(type)) {
            failWithMessage("Expected node to be %s but was %s", type, actualNodeType);
        }
        return this;
    }

    public JsonNodeAssert child(String childName) {
        isNotNull();
        hasChild(childName);
        return assertThat(actual.get(childName));
    }

    public JsonNodeAssert hasStringValue(String value) {
        String actualValue = actual.asText();
        Assertions.assertThat(actualValue).isEqualTo(value);
        return this;
    }

    private String prettyPrint(JsonNode node) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (JsonProcessingException e) {
            // this should never happen with JsonNodes
            throw new RuntimeException(e);
        }
    }
}
