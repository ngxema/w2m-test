package com.w2m.superheroes.repository;

import java.util.List;

import com.w2m.superheroes.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {

    List<SuperHero> findByNameContaining(String searchString);
}