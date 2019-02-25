package com.example.depeat.ui.activities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.depeat.R;
import com.example.depeat.datamodels.User;
import com.example.depeat.services.RestController;
import com.example.depeat.ui.activities.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<String>{

    EditText email_registration, password_registration, numberphone_registration;
    Button register_registration;

    private static final String TAG = RegisterActivity.class.getSimpleName();

    RestController restController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RegisterActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_register);

        email_registration = findViewById(R.id.id_text_email_registration);
        password_registration = findViewById(R.id.id_text_password_registration);
        numberphone_registration = findViewById(R.id.id_text_numberphone_registration);
        register_registration = findViewById(R.id.register_registration);

        restController = new RestController(this);
        register_registration.setOnClickListener(this);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //register_registration.setEnabled(Utils.enableButton(RegisterActivity.this, email_registration.getText().toString(),
                  //      password_registration.getText().toString(), numberphone_registration.getText().toString(), MIN_DIMENSION_PASSWORD));


            }
        };

        email_registration.addTextChangedListener(textWatcher);
        password_registration.addTextChangedListener(textWatcher);
        numberphone_registration.addTextChangedListener(textWatcher);

        /*
        //TODO resolve
        //Ho dovuto per il momento disattivare il controllo sull'email, password e numeroditelefono. Devo controllarlo
        //levato in activity_register.xml al button register --> android:enabled="false"
        register_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                String url = "http://138.68.86.70/auth/local/register";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", String.valueOf(error));
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<>();
                        params.put("username", numberphone_registration.getText().toString());
                        Log.i("username", numberphone_registration.getText().toString());
                        params.put("email", email_registration.getText().toString());
                        Log.i("email", email_registration.getText().toString());
                        params.put("password", password_registration.getText().toString());
                        Log.i("password", password_registration.getText().toString());

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });

        */




    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_registration){
            Map<String,String> params = new HashMap<>();
            params.put("username", numberphone_registration.getText().toString());
            params.put("email", email_registration.getText().toString());
            params.put("password", password_registration.getText().toString());

            restController.postRequest(User.REGISTER_ENDPOINT, params, this, this);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG, response);
        try{
            JSONObject responseJson = new JSONObject(response);
            String accessToken = responseJson.getString("jwt");
            SharedPreferencesUtils.putValue(this,User.ACCESS_TOKEN_KEY, accessToken);

            User user = new User(responseJson.getJSONObject("user"), accessToken);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
