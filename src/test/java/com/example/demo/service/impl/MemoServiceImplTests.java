package com.example.demo.service.impl;

import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;

/**
 * サービス単体テスト
 */
public class MemoServiceImplTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private MemoRepository repository;
    @InjectMocks
    private MemoServiceImpl sut;

    @Test
    public void findById() {
        Memo expected = Memo.of(1L,"test title", "test description");
        Mockito.when(repository.findOne(anyLong())).thenReturn(expected);

        Memo actual = sut.findById(1L).orElse(null);

        assertThat(actual).as("actualは必ず検索できる").isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAll() {
        Pageable page = new PageRequest(0, 5);
        List<Memo> expected = Arrays.asList(
            Memo.of(1L,"test title 1", "test description 1"),
            Memo.of(2L,"test title 2", "test description 2"),
            Memo.of(3L,"test title 3", "test description 3")
        );

        Mockito.when(repository.findAll(eq(page))).thenReturn(new PageImpl<>(expected, page, 3));

        Page<Memo> actual = sut.findAll(page);

        assertThat(actual.getContent()).as("actualは必ず検索できる").isNotNull();
        assertThat(actual.getContent()).hasSize(3);
    }

}
