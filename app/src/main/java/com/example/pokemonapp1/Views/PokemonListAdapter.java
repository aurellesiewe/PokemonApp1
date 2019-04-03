package com.example.pokemonapp1.Views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pokemonapp1.Models.Pokemon;
import com.example.pokemonapp1.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder>{
    private static final String TAG = "PokemonListAdapter";
    private List<Pokemon> values;


    public PokemonListAdapter(  List<Pokemon> values) {
        this.values = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from( viewGroup.getContext()).inflate(R.layout.row_layout,viewGroup, false);
        ViewHolder vH = new ViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder : called");
        final Pokemon currentPokemon = values.get(position);
        viewHolder.textView1.setText(currentPokemon.getName());
        viewHolder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent goToDetail = new Intent(context,PokemonDetail.class);
                goToDetail.putExtra("key_id", position + 1);
                context.startActivity(goToDetail);
            }
        });
        String id = String.valueOf(position+1);
        viewHolder.textView2.setText("id: " + id);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView textView1;
        public TextView textView2;
        public RelativeLayout parent_layout;

        public ViewHolder( View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.icon);
            textView1 =  itemView.findViewById(R.id.firstLine);
            textView2 =  itemView.findViewById(R.id.secondLine);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
