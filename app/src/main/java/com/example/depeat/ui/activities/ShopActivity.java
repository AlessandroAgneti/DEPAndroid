package com.example.depeat.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.depeat.R;
import com.example.depeat.datamodels.Food;
import com.example.depeat.ui.activities.adapters.FoodAdapter;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    RecyclerView foodRV;
    RecyclerView.LayoutManager foodLayoutManager;
    FoodAdapter foodAdapter;
    ArrayList<Food> foodArrayList;
    Button buttonCheckout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Modifica grafica
        ShopActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        ShopActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setTitle("Menu");

        setContentView(R.layout.activity_shop);

        foodRV = findViewById(R.id.list_food);
        foodLayoutManager = new LinearLayoutManager(this);
        foodAdapter = new FoodAdapter(this, getData());

        foodRV.setLayoutManager(foodLayoutManager);
        foodRV.setAdapter(foodAdapter);

        buttonCheckout = findViewById(R.id.id_buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
            }
        });
    }

    private ArrayList<Food> getData(){
        foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food(R.drawable.blank_profile, "Mc Donald's", "5 euro", "Esempio di descrizione food"));
        foodArrayList.add(new Food(R.drawable.blank_profile, "Burger king", "10 euro", "Esempio di descrizione food"));
        foodArrayList.add(new Food(R.drawable.blank_profile, "KFC", "7 euro",  "Esempio di descrizione food"));
        foodArrayList.add(new Food(R.drawable.blank_profile, "Subway", "9 euro",  "Esempio di descrizione food"));
        foodArrayList.add(new Food(R.drawable.blank_profile, "Pizza Hut", "3 euro",  "Esempio di descrizione food"));
        foodArrayList.add(new Food(R.drawable.blank_profile, "Starbucks", "7 euro",  "Esempio di descrizione food"));
        foodArrayList.add(new Food(R.drawable.blank_profile, "Domino's Pizza", "5 euro","Esempio di descrizione food"));
        return foodArrayList;
    }
}
