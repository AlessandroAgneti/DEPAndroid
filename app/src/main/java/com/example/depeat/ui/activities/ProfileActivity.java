package com.example.depeat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.depeat.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    Button button_logOut;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences(getResources().getString(R.string.PROFILE_PREFERENCES), Context.MODE_PRIVATE);

        button_logOut = findViewById(R.id.button_logOut);
        button_logOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_logOut){
            Log.i("TOKEN", getResources().getString(R.string.TOKEN));
            sharedPreferences.edit()
                    .remove(getResources().getString(R.string.TOKEN))
                    .apply();

            invalidateOptionsMenu();
            startActivity(new Intent(this, MainActivity.class));
            finish();

            //TODO repair...
        }
    }
}
