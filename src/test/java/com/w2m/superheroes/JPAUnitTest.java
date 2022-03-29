package com.w2m.superheroes;

import com.w2m.superheroes.repository.SuperHeroRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    SuperHeroRepository repository;

    @Test
    public void should_store_a_superhero() { }
    @Test
    public void should_find_all_superheroes() { }
    @Test
    public void should_find_superhero_by_id() { }
    @Test
    public void should_update_superhero_by_id() { }
    @Test
    public void should_delete_superhero_by_id() { }
    @Test
    public void should_delete_all_superheroes() { }
}