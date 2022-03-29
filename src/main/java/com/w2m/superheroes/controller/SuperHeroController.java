package com.w2m.superheroes.controller;


import com.w2m.superheroes.dto.SuperHeroDTO;
import com.w2m.superheroes.model.SuperHero;
import com.w2m.superheroes.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;


    /**
     * Retrieve all superheroes on the DB
     * @param nameSearch search string
     * @return All superheroes in DB
     */
    @GetMapping("/superheroes")
    public ResponseEntity<List<SuperHero>> getAllSuperHeroes(@RequestParam(required = false) String nameSearch) {
        List<SuperHero> superHeroes = superHeroService.getAllSuperHeroes(nameSearch);
        if (!superHeroes.isEmpty()) {
            return new ResponseEntity<>(superHeroes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(superHeroes, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Retrieve single superhero
     * @param id superhero id
     * @return superhero or notfound if doesn't exist
     */
    @GetMapping("/superheroes/{id}")
    public ResponseEntity<SuperHero> getSuperHeroById(@PathVariable("id") long id) {
        SuperHero superHeroData = superHeroService.getSuperHeroById(id);
        if (null != superHeroData.getName() && !"".equals(superHeroData.getName())){
            return new ResponseEntity<>(superHeroData, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Creates a new superhero
     * @param hero input dto to create a new object
     * @return ResponseEntityStatus: OK / ERROR
     */
    @PostMapping("/superheroes")
    public ResponseEntity<SuperHero> createSuperHero(@RequestBody SuperHeroDTO hero) {
        boolean saved = superHeroService.createSuperHero(hero);
        return saved ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Updates superhero if exists
     * @param id superhero to update
     * @param hero new data
     * @return ResponseEntityStatus: OK / ERROR
     */
    @PutMapping("/superheroes/{id}")
    public ResponseEntity<SuperHero> updateSuperHero(@PathVariable("id") long id, @RequestBody SuperHeroDTO hero) {
        SuperHero superHeroData = superHeroService.updateSuperHero(id, hero);
        if (null != superHeroData.getName() && !"".equals(superHeroData.getName())){
            return new ResponseEntity<>(superHeroData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Deletes superhero by id
     * @param id superhero to delete
     * @return ResponseEntityStatus: OK / ERROR
     */
    @DeleteMapping("/superheroes/{id}")
    public ResponseEntity<HttpStatus> deleteSuperHero(@PathVariable("id") long id) {
        boolean deleted = superHeroService.deleteSuperHero(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Deletes all superheroes in DB
     * @return ResponseEntityStatus: OK / ERROR
     */
    @DeleteMapping("/superheroes")
    public ResponseEntity<HttpStatus> deleteAllSuperHeroes() {
        boolean deletedAll = superHeroService.deleteAllSuperHeroes();
        return deletedAll ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}