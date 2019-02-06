package com.example.depeat.ui.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.depeat.R;
import com.example.depeat.datamodels.Food;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Food> dataFood;
    private LayoutInflater inflater;

    public FoodAdapter(Context context, ArrayList<Food> dataFood){
        inflater = LayoutInflater.from(context);
        this.dataFood = dataFood;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_food, viewGroup, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        FoodViewHolder vh = (FoodViewHolder)viewHolder;
        Food f = dataFood.get(position);
        vh.foodName.setText(f.getNomeFood());
        vh.foodDescription.setText(f.getDescrizioneFood());
        vh.foodPrice.setText(f.getPrezzoFood());
        vh.photoFood.setImageResource(f.getImageFoodId());
    }

    @Override
    public int getItemCount() {
        return dataFood.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder{
        private TextView foodName;
        private TextView foodPrice;
        private TextView foodDescription;
        private ImageView addButton;
        private ImageView removeButton;
        private ImageView photoFood;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodDescription = itemView.findViewById(R.id.id_descrizione_food);
            foodName = itemView.findViewById(R.id.id_name_food);
            foodPrice = itemView.findViewById(R.id.id_prezzo_food);
            addButton = itemView.findViewById(R.id.id_add_food);
            removeButton = itemView.findViewById(R.id.id_remove_food);
            photoFood = itemView.findViewById(R.id.id_image_food);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Aggiunto piatto", Toast.LENGTH_SHORT).show();
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Rimosso piatto", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
