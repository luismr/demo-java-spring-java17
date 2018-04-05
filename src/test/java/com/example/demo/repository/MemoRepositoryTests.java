package com.example.demo.repository;

import com.example.demo.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemoRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private MemoRepository sut;

    @Test
    @Sql(statements = "INSERT INTO memo (id, title, description, done, updated) VALUES (1, 'test title', 'test description', FALSE, CURRENT_TIMESTAMP)")
    public void findOne() {
        Memo expected = testEntityManager.find(Memo.class, 1L);
        Memo actual = sut.findOne(1L);
        assertThat(actual).as("actualは必ず検索できる").isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void save() {
        Memo expected = Memo.of("test title", "test description");
        sut.saveAndFlush(expected);
        Memo actual = testEntityManager.find(Memo.class, expected.getId());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Sql(statements = "INSERT INTO memo (id, title, description, done, updated) VALUES (1, 'test title', 'test description', FALSE, CURRENT_TIMESTAMP)")
    public void delete() {
        Memo expected = testEntityManager.find(Memo.class, 1L);
        sut.delete(expected.getId());
        sut.flush();
        Memo actual = testEntityManager.find(Memo.class, expected.getId());
        assertThat(actual).as("actualは削除されている").isNull();
    }

}
