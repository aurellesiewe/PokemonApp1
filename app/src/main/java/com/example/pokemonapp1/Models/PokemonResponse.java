package com.example.pokemonapp1.Models;

import java.util.List;

public class PokemonResponse {
    private List<Pokemon> results = null;

    public List<Pokemon> getPokemon() {
        return results;
    }

    public void setPokemons(List<Pokemon> pokemon) {
        this.results = pokemon;
    }
}
