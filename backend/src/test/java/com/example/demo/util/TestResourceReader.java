package com.example.demo.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class TestResourceReader {

    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    public static String loadResource(String resourcePath) {
        Resource resource = resourceLoader.getResource(resourcePath);
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}