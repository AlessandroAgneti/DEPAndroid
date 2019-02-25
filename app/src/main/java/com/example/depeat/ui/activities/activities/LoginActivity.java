
package com.example.depeat.ui.activities.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.depeat.R;
import com.example.depeat.Utils;
import com.example.depeat.datamodels.User;
import com.example.depeat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<String> {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText edtPassword, edtemail;
    private Button loginBtn;
    private TextView registerBtn;
    private final static int MIN_DIMENSION_PASSWORD = 6;

    private RestController restController;
    public static final String EMAIL_KEY = "email";

    private SharedPreferences sharedPreferences;

    //private String email;
    //private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_login);
        edtPassword = findViewById(R.id.id_text_password);
        edtemail = findViewById(R.id.id_text_email);
        loginBtn = findViewById(R.id.id_login);
        registerBtn = findViewById(R.id.id_register);


        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        restController = new RestController(this);
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.PROFILE_PREFERENCES), Context.MODE_PRIVATE);

       /* loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtemail.getText().toString();
                password = edtPassword.getText().toString();

                boolean b = Utils.Controll(LoginActivity.this, email, password, MIN_DIMENSION_PASSWORD);
                if(b){
                    //Utils.showToast(LoginActivity.this, R.string.operation_success);
                    Map<String, String> params = new HashMap<>();
                    params.put("identifier" , email);
                    params.put("password", password);

                    restController.postRequest(User.LOGIN_ENDPOINT, params, this, this);

                }else{
                    Utils.showToast(LoginActivity.this, R.string.operation_fail);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });   */


    }


    private void doLogin() {
        String email = edtemail.getText().toString();
        String password = edtPassword.getText().toString();

        boolean b = Utils.Controll(LoginActivity.this, email, password, MIN_DIMENSION_PASSWORD);
        if (b) {
            Utils.showToast(LoginActivity.this, R.string.operation_success);
        } else {
            Utils.showToast(LoginActivity.this, R.string.operation_fail);
        }

        Map<String, String> params = new HashMap<>();
        params.put("identifier", email);
        params.put("password", password);

        restController.postRequest(User.LOGIN_ENDPOINT, params, this, this);
    }

    private void showToast(@StringRes int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        showToast(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG, response);
        String token = null;
        try {
            JSONObject jsonObject = new JSONObject(response);
            token = jsonObject.optString("jwt");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (token != null) {
            sharedPreferences.edit()
                    .putString(getResources().getString(R.string.TOKEN), token)
                    .apply();
            setResult(Activity.RESULT_OK);
            finish();
        } else {
            //Log error
            Log.e(TAG, "Error with the token");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_login) {
            doLogin();
        } else if (v.getId() == R.id.id_register) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
