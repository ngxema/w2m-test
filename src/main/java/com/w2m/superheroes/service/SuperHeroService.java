package com.w2m.superheroes.service;

import com.w2m.superheroes.dto.SuperHeroDTO;
import com.w2m.superheroes.model.SuperHero;
import com.w2m.superheroes.repository.SuperHeroRepository;
import com.w2m.superheroes.timecontrol.PerformanceOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroService {

    @Autowired
    private SuperHeroRepository superHeroRepository;


    @Cacheable("allSuperHeroes")
    @PerformanceOn
    public List<SuperHero> getAllSuperHeroes(String nameSearch) {
        List<SuperHero> superHeroes = new ArrayList<>();
        try {
            if (nameSearch == null || "".equals(nameSearch))
                superHeroes.addAll(superHeroRepository.findAll());
            else
                superHeroes.addAll(superHeroRepository.findByNameContaining(nameSearch));

            return superHeroes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Cacheable("heroById")
    @PerformanceOn
    public SuperHero getSuperHeroById(long id) {
        Optional<SuperHero> superHeroData = superHeroRepository.findById(id);
        return superHeroData.orElseGet(SuperHero::new);
    }

    public boolean createSuperHero(SuperHeroDTO hero) {
        try {
            superHeroRepository.save(new SuperHero(hero.getName(), hero.getColor()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SuperHero updateSuperHero(long id, SuperHeroDTO hero) {
        Optional<SuperHero> superHeroData = superHeroRepository.findById(id);
        if (superHeroData.isPresent()) {
            SuperHero superHero = superHeroData.get();
            superHero.setName(hero.getName());
            superHero.setColor(hero.getColor());
            return superHeroRepository.save(superHero);
        } else {
            return superHeroData.orElseGet(SuperHero::new);
        }
    }

    public boolean deleteSuperHero(long id) {
        try {
            superHeroRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @PerformanceOn
    public boolean deleteAllSuperHeroes() {
        try {
            superHeroRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
