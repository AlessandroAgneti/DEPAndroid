package com.example.depeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.depeat.R;
import com.example.depeat.Utils;


public class RegisterActivity extends AppCompatActivity {

    private final static int MIN_DIMENSION_PASSWORD = 6;
    private EditText email_registration, password_registration, numberphone_registration;
    private Button register_registration;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email_registration = findViewById(R.id.id_text_email_registration);
        password_registration = findViewById(R.id.id_text_password_registration);
        numberphone_registration = findViewById(R.id.id_text_numberphone_registration);
        register_registration = findViewById(R.id.register_registration);
        checkBox = findViewById(R.id.visualizzaPassRegister);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                register_registration.setEnabled(Utils.enableButton(RegisterActivity.this, email_registration.getText().toString(),
                        password_registration.getText().toString(), numberphone_registration.getText().toString(), MIN_DIMENSION_PASSWORD));

            }
        };

        email_registration.addTextChangedListener(textWatcher);
        password_registration.addTextChangedListener(textWatcher);
        numberphone_registration.addTextChangedListener(textWatcher);


        register_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    //show password
                    password_registration.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    //Hide password
                    password_registration.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });



    }


}
