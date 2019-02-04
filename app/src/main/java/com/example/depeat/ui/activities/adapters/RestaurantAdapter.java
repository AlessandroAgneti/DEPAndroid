package com.example.depeat.ui.activities.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        this.context = context;
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
        vh.desc_complete.setText(r.getDesc_complete());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView restaurantName;
        private TextView restaurantAddress;
        private ImageView restaurantImage;
        private TextView restaurantMinOrder;
        private ImageView plusButton;
        private LinearLayout linearLayout;
        private TextView desc_complete;
        private Button menuButton;
        private Animation animationUp;
        private Animation animationDown;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.name_tv);
            restaurantAddress = itemView.findViewById(R.id.name_indirizzo);
            restaurantImage = itemView.findViewById(R.id.image_restaurant);
            restaurantMinOrder = itemView.findViewById(R.id.name_float);
            plusButton = itemView.findViewById(R.id.button_plus);
            linearLayout = itemView.findViewById(R.id.expandableLayout);
            desc_complete = itemView.findViewById(R.id.desc_complete);
            menuButton = itemView.findViewById(R.id.buttonMenu);

            //animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
            //animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayout.getVisibility() == View.GONE){
                        linearLayout.setVisibility(View.VISIBLE);
                        //linearLayout.startAnimation(animationUp);

                        plusButton.setImageResource(R.drawable.ic_notexpand);
                    }else if(linearLayout.getVisibility() == View.VISIBLE){
                        linearLayout.setVisibility(View.GONE);
                        //linearLayout.startAnimation(animationDown);

                        plusButton.setImageResource(R.drawable.ic_expand);
                    }
                }
            });

            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(this, CheckoutActivity.class));
                }
            });


        }


    }


}
