package com.example.demo.config;

import com.example.demo.util.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TimeZoneConfig.class)
class TimeZoneConfigTest {

    @Test
    void defaultTimeZoneIsUtc() {
        assertThat(TimeZone.getDefault().getID()).isEqualTo("UTC");
    }

}
