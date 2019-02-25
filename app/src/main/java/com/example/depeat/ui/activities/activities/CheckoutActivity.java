package com.example.depeat.ui.activities.activities;

import android.arch.persistence.room.Database;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.depeat.R;
import com.example.depeat.datamodels.Food;
import com.example.depeat.datamodels.Order;
import com.example.depeat.datamodels.Restaurant;
import com.example.depeat.services.AppDatabase;
import com.example.depeat.ui.activities.adapters.OrderProductsAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener,OrderProductsAdapter.onItemRemovedListener{

    private TextView restaurantIv, restaurantAdress, totalTv;
    private RecyclerView productRv;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    private OrderProductsAdapter adapter;

    private Order order;
    private float total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_checkout);

        restaurantIv = findViewById(R.id.id_name_restaurant);
        restaurantAdress = findViewById(R.id.id_indirizzo_restaurant);
        totalTv = findViewById(R.id.id_total_tv);
        productRv = findViewById(R.id.recyclerview_id);
        payBtn = findViewById(R.id.button_payment);
        //Initialize datamodel object

        //setup recyclerview
        layoutManager = new LinearLayoutManager(this);
        productRv.setLayoutManager(layoutManager);
        adapter = new OrderProductsAdapter(this, null);
        adapter.setOnItemRemovedListener(this);
        productRv.setAdapter(adapter);

        //Set click listener for button
        payBtn.setOnClickListener(this);

        new GetOrder().execute();

    }

    private void bindData(Order order){
        this.order = order;

        adapter.setDataSet(order.getFoods());
        total = order.getTotal();
        restaurantIv.setText(order.getRestaurant().getNome());
        restaurantAdress.setText(order.getRestaurant().getIndirizzo());
        totalTv.setText(String.valueOf(order.getTotal()));
    }

    public class GetOrder extends AsyncTask<Void, Void, Void>{

        private List<Order> orders;

        @Override
        protected Void doInBackground(Void... voids) {
            orders = AppDatabase.getAppDatabase(CheckoutActivity.this).orderDao().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (orders != null) {
                bindData(orders.get(0));
            }
        }
    }


    //TODO hardcoded

    private Order getOrder(){
        Order mockOrder = new Order();
        mockOrder.setFoods(getFood());
        mockOrder.setRestaurant(getRestaurant());
        mockOrder.setTotal(42.00f);
        return mockOrder;
    }

    //TODO hardcoded
    private Restaurant getRestaurant(){
        return new Restaurant("NomeRistorante", "Via Sandro Sandri", 50.00f);
    }

    //TODO hardcoded
    private ArrayList<Food> getFood(){
        ArrayList<Food> food = new ArrayList<>();
        food.add(new Food("McMenu", 5,2));
        food.add(new Food("McMenu", 5,2));
        food.add(new Food("McMenu", 5,2));
        food.add(new Food("McMenu", 5,2));
        food.add(new Food("McMenu", 5,2));

        return food;
    }

    @Override
    public void onClick(View v) {
        //TODO manageClick
    }


    @Override
    public void onItemRemoved(float subtotal) {
        updateTotal(subtotal);
    }

    private void updateTotal(float subtotal) {
        if(total == 0) return;
        total -= subtotal;
        totalTv.setText(String.valueOf(total));
    }


    //TODO verify
    String url = "http://138.68.86.70/orders";
    StringRequest postRequest = new StringRequest(Request.Method.POST,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            return super.getParams();
        }
    };




}
