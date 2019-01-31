package com.example.depeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtPassword, edtemail;
    private Button loginBtn, registerBtn;
    private CheckBox checkbox;
    private final static int MIN_DIMENSION_PASSWORD = 6;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtPassword = findViewById(R.id.id_text_password);
        edtemail = findViewById(R.id.id_text_email);
        loginBtn = findViewById(R.id.id_login);
        registerBtn = findViewById(R.id.id_register);
        checkbox = findViewById(R.id.visualizzaPass);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    //show password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    //Hide password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtemail.getText().toString();
                password = edtPassword.getText().toString();

                boolean b = Utilities.Controll(MainActivity.this, email, password, MIN_DIMENSION_PASSWORD);
                if(b){
                    Utilities.showToast(MainActivity.this, R.string.operation_success);
                }else{
                    Utilities.showToast(MainActivity.this, R.string.operation_fail);
                }
            }
        });







    }


}
