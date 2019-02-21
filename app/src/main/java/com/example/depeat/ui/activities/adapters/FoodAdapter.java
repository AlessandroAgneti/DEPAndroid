package com.example.depeat.ui.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.depeat.R;
import com.example.depeat.datamodels.Food;
import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter {

    private ArrayList<Food> dataFood;
    private LayoutInflater inflater;

    public FoodAdapter(Context context, ArrayList<Food> dataFood){
        inflater = LayoutInflater.from(context);
        this.dataFood = dataFood;
    }

    public interface OnQuantityChangedListener{
        void onChange(float priceFood);
    }

    public void setData(ArrayList<Food> data) {
        this.dataFood = data;
        notifyDataSetChanged();
    }

    public FoodAdapter(Context context) {

        this.dataFood = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }



    public OnQuantityChangedListener getOnQuantityChangedListener(){
        return onQuantityChangedListener;
    }

    public void setOnQuantityChangedListener(OnQuantityChangedListener onQuantityChangedListener){
     this.onQuantityChangedListener = onQuantityChangedListener;
    }

    private OnQuantityChangedListener onQuantityChangedListener;

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
        //vh.foodDescription.setText(f.getDescrizioneFood());
        vh.foodPrice.setText(String.valueOf(f.getPrezzoFood()));
        vh.foodQuantity.setText(String.valueOf(f.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return dataFood.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView foodName;
        private TextView foodPrice;
        //private TextView foodDescription;
        private ImageView addButton;
        private ImageView removeButton;
        private TextView foodQuantity;
        private ImageView foodImage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            //foodDescription = itemView.findViewById(R.id.id_description_food);
            foodName = itemView.findViewById(R.id.id_name_food);
            foodPrice = itemView.findViewById(R.id.id_price_food);
            addButton = itemView.findViewById(R.id.id_add_food);
            removeButton = itemView.findViewById(R.id.id_remove_food);
            foodQuantity = itemView.findViewById(R.id.id_number_food);

            addButton.setOnClickListener(this);
            removeButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Food food = dataFood.get(getAdapterPosition()); //Mi vado a prendere l'oggetto che è stato prodotto in quel momento
            if(v.getId() == R.id.id_add_food){
                food.increaseQuantitaty();
                notifyItemChanged(getAdapterPosition());
                onQuantityChangedListener.onChange(food.getPrezzoFood());
            }else if(v.getId() == R.id.id_remove_food){
                if(food.getQuantity() == 0)
                    return;
                food.decreaseQuantity();
                onQuantityChangedListener.onChange(food.getPrezzoFood()*-1);
            }

            notifyItemChanged(getAdapterPosition());

        }
    }
}
