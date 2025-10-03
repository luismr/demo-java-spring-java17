package com.example.demo.controller;

import com.example.demo.Application;
import com.example.demo.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * コントローラーの結合テスト
 */
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemoControllerIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getMemo() {
        ResponseEntity<Memo> result = testRestTemplate.getForEntity("/memo/{id}", Memo.class, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(result.getBody().getId()).isEqualTo(1L);
    }

    @Test
    void pagination() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/memo/list?page={page}&size={size}", String.class, 0, 3);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(result.getBody()).contains("id");
    }

}
