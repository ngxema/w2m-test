package com.w2m.superheroes.model;

import javax.persistence.*;

@Entity
@Table(name = "superheros")
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;


    public SuperHero() {
    }

    public SuperHero(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "SuperHero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}