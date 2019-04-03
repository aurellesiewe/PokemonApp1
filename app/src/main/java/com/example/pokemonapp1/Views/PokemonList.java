package com.example.pokemonapp1.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pokemonapp1.Models.Pokemon;
import com.example.pokemonapp1.Models.PokemonResponse;
import com.example.pokemonapp1.Models.RestApiPokemon;
import com.example.pokemonapp1.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestApiPokemon RestApiPokemon = retrofit.create(RestApiPokemon.class);
        Call<PokemonResponse> call = RestApiPokemon.getListPokemon();
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                PokemonResponse pokemonResponse = response.body();
                List<Pokemon> list = pokemonResponse.getPokemon();
                showList(list);
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.d("Erreur", "Api Erreur");
            }
        });
    }

    private void showList(List<Pokemon> list) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view); // recupere l'eltm
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this); // Afficher la liste de facon linéaire
        //layoutManager = new GridLayoutManager(this, 2); // Afficher la liste de facon grille avec 2 colonnes
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new PokemonListAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }
}
