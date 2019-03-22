package com.example.pokemonapp1.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pokemonapp1.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder>{
    private static final String TAG = "PokemonListAdapter";
    private List<String> values;
    private Context mContext;

    public PokemonListAdapter( Context mContext, List<String> values) {
        this.values = values;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from( viewGroup.getContext()).inflate(R.layout.row_layout,viewGroup, false);
        ViewHolder vH = new ViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder : called");
        final String name = values.get(position);
        viewHolder.textView1.setText(name);
        viewHolder.textView2.setText("footer" + name);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView textView1;
        public TextView textView2;
        public RelativeLayout parenet_layout;

        public ViewHolder( View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.icon);
            textView1 = (TextView) itemView.findViewById(R.id.firstLine);
            textView2 = (TextView) itemView.findViewById(R.id.secondLine);
            parenet_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    public List<String> getvalues() {
        return values;
    }

    public void setvalues(List<String> values) {
        this.values = values;
    }


}
