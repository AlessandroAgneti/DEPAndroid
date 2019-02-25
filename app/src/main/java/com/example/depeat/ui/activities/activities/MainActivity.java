package com.example.depeat.ui.activities.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.depeat.R;
import com.example.depeat.Utils;
import com.example.depeat.services.RestController;
import com.example.depeat.ui.activities.adapters.RestaurantAdapter;
import java.util.ArrayList;
import com.example.depeat.datamodels.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;

import static com.example.depeat.ui.activities.adapters.RestaurantAdapter.isGridMode;
import static com.example.depeat.ui.activities.adapters.RestaurantAdapter.setGridMode;


public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList = new ArrayList<>();

    private RestController restController;

    SharedPreferences sharedPreferences;
    private SharedPreferences profilePreferences;

    private static final String SharedPrefs = "com.example.depeat.general_prefs";
    private static final String VIEW_MODE = "VIEW_MODE";

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

        //Comment = layoutManager = new LinearLayoutManager(this);
        layoutManager = getLayoutManager(getSavedLayoutManager());
        adapter = new RestaurantAdapter(this);
        //Devo settare anche l'adapter in modo che vada a prendere l'item corretto
        setGridMode(getSavedLayoutManager());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);


        restController = new RestController(this);
        restController.getRequest(Restaurant.ENDPOINT, this, this);

        //SharedPref
        profilePreferences = getSharedPreferences(getResources().getString(R.string.PROFILE_PREFERENCES), Context.MODE_PRIVATE);

        /*
                                        //Prendo JSON
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://138.68.86.70/restaurants";

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

    */
    }

    //Creazione Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        if(isGridMode())
            menu.findItem(R.id.action_mode).setIcon(R.drawable.ic_view_list_black_24dp);
        else
            menu.findItem(R.id.action_mode).setIcon(R.drawable.ic_view_module_black_24dp);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (profilePreferences.getString(getResources().getString(R.string.TOKEN), null) !=  null) {
            menu.findItem(R.id.action_profile).setVisible(true);
            menu.findItem(R.id.action_login).setVisible(false);
        } else {
            menu.findItem(R.id.action_profile).setVisible(false);
            menu.findItem(R.id.action_login).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //Utilizzo dei metodi nel menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                requestLogin();
                break;

            case R.id.action_checkout:
                startActivity(new Intent(this, CheckoutActivity.class));
                break;

            case R.id.action_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }


        /*if(item.getItemId() == R.id.view_mode){
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
        }*/
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

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        try{
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++){
                arrayList.add(new Restaurant(jsonArray.getJSONObject(i)));
            }
            adapter.setData(arrayList);
        }catch (JSONException e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case Utils.REQUEST_LOGIN:
                if (resultCode == Activity.RESULT_OK) {
                    invalidateOptionsMenu();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void requestLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Utils.REQUEST_LOGIN);
    }
}
