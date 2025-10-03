package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {HelloControllerIntegrationTests.TestConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "my-app.my-module.foo.name=test"
})
class HelloControllerIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void hello() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/hello/world", String.class);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @SpringBootApplication(scanBasePackages = "com.example.demo")
    public static class TestConfig {
    }

}
