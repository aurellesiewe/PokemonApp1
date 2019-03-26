package com.example.pokemonapp1.Views;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemonapp1.Models.PokemonIdResponse;
import com.example.pokemonapp1.Models.RestApiPokemon;
import com.example.pokemonapp1.Models.Sprite;
import com.example.pokemonapp1.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestApiPokemon restApiPokemon = retrofit.create(RestApiPokemon.class);
        int id = getIntent().getIntExtra("key_id",1);
        Call<PokemonIdResponse> call = restApiPokemon.getPokemonDetail(id);
        call.enqueue(new Callback<PokemonIdResponse>() {

            @Override
            public void onResponse(Call<PokemonIdResponse> call, Response<PokemonIdResponse> response) {
                PokemonIdResponse pokemonIdResponse = response.body();
                showDetails(pokemonIdResponse.getName(),
                        pokemonIdResponse.getId(),
                        pokemonIdResponse.getHeight(),
                        pokemonIdResponse.getWeight(),
                        pokemonIdResponse.getSprites());
            }

            @Override
            public void onFailure(Call<PokemonIdResponse> call, Throwable t) {
                Log.d("erreur","API K.O");
            }
        });
    }



    public void showDetails(String name, int id, int height, int weight, Sprite sprite){
        TextView nameView = findViewById(R.id.pokName);
        nameView.setText(name);

        TextView idView = findViewById(R.id.pokId);
        String idString = String.valueOf(id);
        idView.setText(idString);

        TextView heightView = findViewById(R.id.pokHeight);
        heightView.setText("Height :" +height+ "m");

        TextView weightView = findViewById(R.id.pokWeight);
        weightView.setText("Weight :" +weight+ "kg");

        ImageView spriteview = findViewById(R.id.icon2);
        Picasso.with(this).
                load(sprite.getFrontDefault())
                //.resize(1000,1000)
                //.centerCrop()
                .fit()
                .into(spriteview);

    }
}

