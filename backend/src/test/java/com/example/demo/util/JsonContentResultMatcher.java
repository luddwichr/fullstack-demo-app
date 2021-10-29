package com.example.demo.util;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.test.web.servlet.ResultMatcher;

import java.nio.charset.StandardCharsets;

import static com.example.demo.util.TestResourceReader.loadResource;

public final class JsonContentResultMatcher {

    public static JsonContentResultMatcher jsonContent() {
        return new JsonContentResultMatcher();
    }

    public ResultMatcher matchesFile(String jsonContentResourcePath) {
        return matchesString(loadResource(jsonContentResourcePath));
    }

    private ResultMatcher matchesString(String jsonContent) {
        return result -> {
            String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
            JSONAssert.assertEquals(jsonContent, content, JSONCompareMode.NON_EXTENSIBLE);
        };
    }
}
