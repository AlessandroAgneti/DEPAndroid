package com.example.depeat.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.depeat.R;
import com.example.depeat.datamodels.Food;
import com.example.depeat.ui.activities.adapters.FoodAdapter;
import com.example.depeat.ui.activities.adapters.RestaurantAdapter;
import java.util.ArrayList;
import com.example.depeat.datamodels.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.depeat.ui.activities.adapters.RestaurantAdapter.isGridMode;
import static com.example.depeat.ui.activities.adapters.RestaurantAdapter.setGridMode;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList = new ArrayList<>();

    SharedPreferences sharedPreferences;
    private static final String SharedPrefs = "com.example.depeat.general_prefs";
    private static final String VIEW_MODE = "VIEW_MODE";
    private MenuItem viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set istruzioni per modificare dei comportamenti visivi
        MainActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        MainActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setTitle("Restaurants");

        //Lo collego alla sua activity
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        viewButton = findViewById(R.id.view_mode);

        //Comment = layoutManager = new LinearLayoutManager(this);
        layoutManager = getLayoutManager(getSavedLayoutManager());
        adapter = new RestaurantAdapter(this);
        //Devo settare anche l'adapter in modo che vada a prendere l'item corretto
        setGridMode(getSavedLayoutManager());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);


                                        //Prendo JSON
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://5ba19290ee710f0014dd764c.mockapi.io/api/v1/restaurant";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, //HTTP request method
                url,    // Server link
                new Response.Listener<String>() {  // Listener for successful response
                    @Override
                    public void onResponse(String response) {
                        //questo blocco di codice viene eseguito quando si ha successo
                        Log.d(TAG, response);

                        //Start parsing
                        try {
                            JSONObject responseJson = new JSONObject(response); // Sono degli array a tutti gli effetti
                            JSONArray restaurantJsonArray = responseJson.getJSONArray("data");
                            //try catch perché vuole esplodere se non gli passiamo un JSON
                            //Dobbimao scorrere la lista e dobbiamo trasformare ogni singolo elemento di JSON in un oggetto
                            for(int i = 0; i < restaurantJsonArray.length(); i++){
                                Restaurant restaurant = new Restaurant(restaurantJsonArray.getJSONObject(i));
                                arrayList.add(restaurant);
                            }

                            adapter.setData(arrayList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() { // Listener for error response
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //questo blocco di codice viene eseguito quando non si ha successo ma un errore
                        Log.e(TAG, error.getMessage());
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donald's", "This is a Mc Donald's",  10.0F));
        arrayList.add(new Restaurant("Burger king", "This is a burger king", 8.0F));
        arrayList.add(new Restaurant("KFC", "This is a KFC", 12.0F));
        arrayList.add(new Restaurant("Subway", "This is a Subway", 10.0F));
        arrayList.add(new Restaurant("Pizza Hut", "This is a Pizza Hut", 18.0F));
        arrayList.add(new Restaurant("Starbucks", "This is a Starbucks", 5.0F));
        arrayList.add(new Restaurant("Domino's Pizza", "This is a Domino's Pizza", 12.0F));
        return arrayList;
    }

    //Creazione Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        if(isGridMode())
            menu.findItem(R.id.view_mode).setIcon(R.drawable.ic_view_list_black_24dp);
        else
            menu.findItem(R.id.view_mode).setIcon(R.drawable.ic_view_module_black_24dp);
        return true;
    }



    //Utilizzo dei metodi nel menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.view_mode){
            controllo_icona(item);
            setLayoutManager();
            return true;
        }

        if(item.getItemId() == R.id.login_menu){
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }else if (item.getItemId() == R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Funzione per controllare che icona del view model impostare
    public boolean controllo_icona(MenuItem item){
        if (isGridMode) {
            item.setIcon(R.drawable.ic_view_module_black_24dp);
        } else{
            item.setIcon(R.drawable.ic_view_list_black_24dp);
        }
        return true;
    }

    //Questo mi serve per settare se è true o false
    private void setLayoutManager(){
        layoutManager = getLayoutManager(!isGridMode);
        setGridMode(!isGridMode());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
        saveLayoutManager(isGridMode());
    }

    //Salvo i dati nello sharedpreferences
    private void saveLayoutManager(boolean isGridLayout){
        sharedPreferences = getSharedPreferences(SharedPrefs, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(VIEW_MODE, isGridLayout);
        editor.apply();
    }

    //Controllo quale layout prendere
    private RecyclerView.LayoutManager getLayoutManager(boolean isGridLayout){
        return isGridLayout? new GridLayoutManager(this, 2): new LinearLayoutManager(this);
    }

    //Prendo i dati salvati nello sharedpreferences

    private boolean getSavedLayoutManager(){
        sharedPreferences = getSharedPreferences(SharedPrefs, MODE_PRIVATE);
        return sharedPreferences.getBoolean(VIEW_MODE, false);
    }
}
