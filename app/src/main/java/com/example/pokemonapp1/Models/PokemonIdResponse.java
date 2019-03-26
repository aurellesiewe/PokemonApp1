package com.example.pokemonapp1.Models;

public class PokemonIdResponse {
    private Integer id;
    private Integer height;
    private Integer weight;
    private String name;
    private Sprite sprites;


    public Integer getId() {
        return id;
    }
    public Integer getHeight() {
        return height;
    }
    public Integer getWeight() {
        return weight;
    }
    public String getName() {
        return name;
    }
    public Sprite getSprites () {
        return sprites;
    }
}
