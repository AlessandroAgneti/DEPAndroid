package com.example.depeat.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.depeat.R;
import com.example.depeat.ui.activities.adapters.RestaurantAdapter;

import java.util.ArrayList;
import com.example.depeat.datamodels.Restaurant;


public class MainActivity extends AppCompatActivity {

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        MainActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Restaurants");


        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this, getData());


        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant(R.drawable.blank_profile, "Mc Donald's", "This is a Mc Donald's",  10.0F, "Esempio di descrizione completa"));
        arrayList.add(new Restaurant(R.drawable.blank_profile, "Burger king", "This is a burger king", 8.0F,"Esempio di descrizione completa"));
        arrayList.add(new Restaurant(R.drawable.blank_profile, "KFC", "This is a KFC", 12.0F, "Esempio di descrizione completa"));
        arrayList.add(new Restaurant(R.drawable.blank_profile, "Subway", "This is a Subway", 10.0F, "Esempio di descrizione completa"));
        arrayList.add(new Restaurant(R.drawable.blank_profile, "Pizza Hut", "This is a Pizza Hut", 18.0F, "Esempio di descrizione completa"));
        arrayList.add(new Restaurant(R.drawable.blank_profile, "Starbucks", "This is a Starbucks", 5.0F, "Esempio di descrizione completa"));
        arrayList.add(new Restaurant(R.drawable.blank_profile, "Domino's Pizza", "This is a Domino's Pizza", 12.0F,"Esempio di descrizione completa"));



        return arrayList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.login_menu){
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }else if (item.getItemId() == R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
