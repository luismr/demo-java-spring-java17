package com.example.demo.controller;

import com.example.demo.Application;
import com.example.demo.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * コントローラーの結合テスト
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemoControllerIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getOne() {
        ResponseEntity<Memo> result = testRestTemplate.getForEntity("/app/memo/{id}", Memo.class, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(result.getBody().getId()).isEqualTo(1L);
    }

    @Test
    public void pagination() {
        ResponseEntity<Memo[]> result = testRestTemplate.getForEntity("/app/memo/list?page={page}&size={size}", Memo[].class, 0, 3);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(result.getBody()).hasSize(3);
    }

}
