package com.w2m.superheroes;

import com.w2m.superheroes.dto.SuperHeroDTO;
import com.w2m.superheroes.model.SuperHero;
import com.w2m.superheroes.repository.SuperHeroRepository;
import com.w2m.superheroes.service.SuperHeroService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@SuppressWarnings("deprecation")
class SuperHeroesServiceTest {

	@InjectMocks
	SuperHeroService service;

	@Mock
	SuperHeroRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getAllEmployeesTest()
	{
		List<SuperHero> list = new ArrayList<>();
		SuperHero s1 = new SuperHero("Superman", "Red");
		SuperHero s2 = new SuperHero("Spiderman", "Red");
		SuperHero s3 = new SuperHero("Antman", "Black");

		list.add(s1);
		list.add(s2);
		list.add(s3);

		when(repository.findAll()).thenReturn(list);

		List<SuperHero> returnList = service.getAllSuperHeroes("");

		Assertions.assertEquals(3, returnList.size());
		verify(repository, times(1)).findAll();
	}

	@Test
	void getEmployeeByIdTest()
	{
		when(repository.findById(1L)).thenReturn(java.util.Optional.of(new SuperHero("Superman","Red")));

		Optional<SuperHero> sh = repository.findById(1L);

		if (sh.isPresent()){
			SuperHero superHero = sh.get();
			Assertions.assertEquals("Superman", superHero.getName());
			Assertions.assertEquals("Red", superHero.getColor());
		}
	}

	@Test
	void createEmployeeTest()
	{
		SuperHeroDTO emp = new SuperHeroDTO("Superman","Red");
		boolean saved = service.createSuperHero(emp);
		Assertions.assertTrue(saved);
	}

}
