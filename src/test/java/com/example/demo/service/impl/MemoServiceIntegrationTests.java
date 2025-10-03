package com.example.demo.service.impl;

import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

/**
 * 結合テスト
 */
@SpringBootTest
class MemoServiceIntegrationTests {

    @Autowired
    private MemoService sut;

    @Transactional(readOnly = true)
    @Test
    void findById() {
        Memo actual = sut.findById(1L).orElse(null);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1L);
    }

    @Transactional(readOnly = true)
    @Test
    void findAll() {
        Pageable page = PageRequest.of(0, 3);
        Page<Memo> actual = sut.findAll(page);

        assertThat(actual).isNotNull();
        assertThat(actual.getContent()).hasSize(3);
    }

}
