package com.example.depeat.ui.activities.activities;

import android.app.Activity;
import android.arch.persistence.room.Database;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.depeat.DAO.OrderDao;
import com.example.depeat.R;
import com.example.depeat.datamodels.Food;
import com.example.depeat.datamodels.Order;
import com.example.depeat.datamodels.Restaurant;
import com.example.depeat.services.AppDatabase;
import com.example.depeat.services.RestController;
import com.example.depeat.ui.activities.adapters.FoodAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements FoodAdapter.OnQuantityChangedListener, Response.Listener<String>, Response.ErrorListener {

    public static final String RESTAURANT_ID_KEY = "RESTAURANT_ID_KEY";
    private static final String TAG = ShopActivity.class.getSimpleName();
    private static final int LOGIN_REQUEST_CODE = 2001;


    //RecyclerView component
    private RecyclerView foodRV;
    private RecyclerView.LayoutManager foodLayoutManager;
    private FoodAdapter foodAdapter;

    //UI component
    private ImageView imageResturant;
    private TextView nameRestaurant, addressRestaurant, totalPrice;
    private Button buttonCheckout;
    private ProgressBar progressBar;
    private TextView minOrder;

    private Menu menu;

    //data model
    private Restaurant restaurant;

    private float total = 0;

    private RestController restController;
    private String restaurantId;
    private Order order;


    private ArrayList<Food> foods = new ArrayList<>();

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


        nameRestaurant = findViewById(R.id.name_restaurant);
        addressRestaurant = findViewById(R.id.address_restaurant);
        totalPrice = findViewById(R.id.id_total_number);
        minOrder = findViewById(R.id.minOrderNumber);
        imageResturant = findViewById(R.id.img_restaurant_shop);

        buttonCheckout = findViewById(R.id.id_buttonCheckout);
        progressBar = findViewById(R.id.determinateBar);
        foodRV = findViewById(R.id.list_food);

        //restaurant = getRestaurant();
        //restaurant.setFoods(getFood());

        //nameRestaurant.setText(restaurant.getNome());
        //addressRestaurant.setText(restaurant.getIndirizzo());
        //progressBar.setMax((int) restaurant.getMinimoOrdine() * 100);
        //minOrder.setText(String.valueOf(restaurant.getMinimoOrdine()));

        foodLayoutManager = new LinearLayoutManager(this);
        foodAdapter = new FoodAdapter(this);
        foodAdapter.setOnQuantityChangedListener(this);

        foodRV.setLayoutManager(foodLayoutManager);
        ((SimpleItemAnimator) foodRV.getItemAnimator()).setSupportsChangeAnimations(false);
        foodRV.setAdapter(foodAdapter);


        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                order = new Order();

                order.setRestaurant(restaurant);
                order.setTotal(total);
                ArrayList<Food> selected = foodAdapter.getDataFood();

                //TODO filtrare i foods con solo quelli con quantità maggiore di 0
                selected.removeIf(foods -> foods.getQuantity() < 1);
                order.setFoods(selected);

                new InsertOrder().execute();
                startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
            }
        });


        restaurantId = getIntent().getStringExtra(ShopActivity.RESTAURANT_ID_KEY);
        //Log.i("Informazione", restaurantId);
        restController = new RestController(this);
        restController.getRequest(
                Restaurant.ENDPOINT.concat(restaurantId),
                this,
                this);

    }

    //TODO verify

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shop, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    //TODO verify

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

    //TODO hardcore
    private void binData() {
        nameRestaurant.setText(restaurant.getNome());
        addressRestaurant.setText(restaurant.getIndirizzo());
        minOrder.setText(String.valueOf(restaurant.getMinimoOrdine()));
        Glide.with(this).load(restaurant.getImageId()).into(imageResturant);
        progressBar.setMax((int) restaurant.getMinimoOrdine() * 100);
        foodAdapter.setData(restaurant.getFoods());


    }

    private void updateTotal(float item) {
        total = total + item;
        totalPrice.setText(String.valueOf(total));
    }

    private void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    private void enableButton() {
        buttonCheckout.setEnabled(total >= restaurant.getMinimoOrdine());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "requestCode " + requestCode);
        Log.d(TAG, "resultCode " + resultCode);

        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // TODO login is successful manage result

            Log.d(TAG, data.getStringExtra("response"));
            menu.findItem(R.id.login_menu).setTitle(R.string.profile)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            startActivity(new Intent(ShopActivity.this, ProfileActivity.class));
                            return true;
                        }
                    });
        }
    }

    @Override
    public void onChange(float priceFood) {
        updateTotal(priceFood);
        updateProgressBar((int) total * 100);
        enableButton();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            restaurant = new Restaurant(jsonObject);
            JSONArray jsonFoods = jsonObject.getJSONArray("products");


            for (int i = 0; i < jsonFoods.length(); i++) {
                foods.add(new Food(jsonFoods.getJSONObject(i)));
            }

            restaurant.setFoods(foods);

            //Log.i("Informazione", "BeforeBind");
            binData();
            //Log.i("Informazione", "AfterBind");

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public class InsertOrder extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase.getAppDatabase(ShopActivity.this).orderDao().insert(order);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
            super.onPostExecute(aVoid);
        }

        /*
        public void converterType(ArrayList<Food> foods){
            //Per convertire un oggetto al JSON string
            String json = new Gson().toJson(foods);

            //Per convertire una JSON string in un java object
            Type type = new TypeToken<ArrayList<Food>>(){}.getType();
            ArrayList<Food> inplist = new Gson().fromJson(json, type);
            for(int i = 0; i < inplist.size(); i++){
                Order x = inplist.get(i);
            }
        }*/
    }
}
