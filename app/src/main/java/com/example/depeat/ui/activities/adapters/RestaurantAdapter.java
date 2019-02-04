package com.example.depeat.ui.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.depeat.R;
import com.example.depeat.datamodels.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    private Context context;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_restaurant_cardview, viewGroup, false); // Il viewGroup sarebbe la recyclerView
        return new RestaurantViewHolder(view); //Vuole un oggetto figlio ovvero che lo estende
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RestaurantViewHolder vh = (RestaurantViewHolder)viewHolder; //Castiamo l'oggetto che mi da di tipo viewholder
        Restaurant r = data.get(position);
        vh.restaurantName.setText(r.getNome()); //Dico a quale posizione si trova il valore che io voglio richiamare nel data.
        vh.restaurantAddress.setText(r.getIndirizzo());
        vh.restaurantImage.setImageResource(r.getImageId());
        vh.restaurantMinOrder.setText(String.valueOf(r.getMinimoOrdine()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurantName;
        public TextView restaurantAddress;
        public ImageView restaurantImage;
        public TextView restaurantMinOrder;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.name_tv);
            restaurantAddress = itemView.findViewById(R.id.name_indirizzo);
            restaurantImage = itemView.findViewById(R.id.image_restaurant);
            restaurantMinOrder = itemView.findViewById(R.id.name_float);

        }
    }
}
