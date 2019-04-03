package com.example.pokemonapp1.Models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApiPokemon {
    @GET("pokemon/?limit=807")
    Call<PokemonResponse> getListPokemon();
    @GET("pokemon/{id}/")
    Call<PokemonIdResponse> getPokemonDetail(@Path("id") int pokemonId);
}
