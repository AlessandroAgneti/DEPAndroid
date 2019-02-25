package com.example.depeat.ui.activities.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.depeat.R;
import com.example.depeat.datamodels.Restaurant;
import com.example.depeat.ui.activities.activities.ShopActivity;
import java.util.ArrayList;


public class RestaurantAdapter extends RecyclerView.Adapter{

    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    private Context context;
    public static boolean isGridMode=false;


    public RestaurantAdapter(Context context, ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public RestaurantAdapter(Context context){
        //new RestaurantAdapter(context, new ArrayList<Restaurant>()); //Mi creo un secondo costruttore e chiamo quello di sopra passandogli un arraylist vuoto
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
        this.context = context;
    }

    public static boolean isGridMode(){
        return isGridMode;
    }

    public static void setGridMode(boolean gridMode){
        isGridMode = gridMode;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflater = LayoutInflater.from(viewGroup.getContext());
        int layout = isGridMode? R.layout.grid_item_restaurant_cardview : R.layout.item_restaurant_cardview;
        View view = inflater.inflate(layout,viewGroup,false);
        //View view = inflater.inflate(R.layout.item_restaurant_cardview, viewGroup, false); // Il viewGroup sarebbe la recyclerView
        return new RestaurantViewHolder(view); //Vuole un oggetto figlio ovvero che lo estende
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RestaurantViewHolder vh = (RestaurantViewHolder)viewHolder; //Castiamo l'oggetto che mi da di tipo viewholder
        Restaurant r = data.get(position);

        vh.restaurantName.setText(r.getNome()); //Dico a quale posizione si trova il valore che io voglio richiamare nel data.
        vh.restaurantAddress.setText(r.getIndirizzo());
        vh.restaurantMinOrder.setText(String.valueOf(r.getMinimoOrdine()));

        Glide.with(context).load(r.getImageId()).into(vh.restaurantImage);
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public void setData(ArrayList<Restaurant> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView restaurantName;
        private TextView restaurantAddress;
        private ImageView restaurantImage;
        private TextView restaurantMinOrder;
        //private LinearLayout linearLayout;
        private Button menuButton;
        private CardView cardViewGrid;

        public RestaurantViewHolder(@NonNull View itemView){
            super(itemView);
            restaurantName = itemView.findViewById(R.id.name_tv);
            restaurantAddress = itemView.findViewById(R.id.name_indirizzo);
            restaurantImage = itemView.findViewById(R.id.image_restaurant);
            restaurantMinOrder = itemView.findViewById(R.id.name_float);
            //linearLayout = itemView.findViewById(R.id.expandableLayout);
            menuButton = itemView.findViewById(R.id.buttonMenu);
            cardViewGrid =  itemView.findViewById(R.id.cardViewGrid);

            menuButton.setOnClickListener(this);




        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.buttonMenu){
                Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra(ShopActivity.RESTAURANT_ID_KEY, data.get(getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        }

    }

}
