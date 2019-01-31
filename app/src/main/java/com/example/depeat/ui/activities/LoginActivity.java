
package com.example.depeat.ui.activities;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.method.HideReturnsTransformationMethod;
        import android.text.method.PasswordTransformationMethod;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;

        import com.example.depeat.R;
        import com.example.depeat.Utils;

public class LoginActivity extends AppCompatActivity {

    private EditText edtPassword, edtemail;
    private Button loginBtn, registerBtn;
    private CheckBox checkbox;
    private final static int MIN_DIMENSION_PASSWORD = 6;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPassword = findViewById(R.id.id_text_password);
        edtemail = findViewById(R.id.id_text_email);
        loginBtn = findViewById(R.id.id_login);
        registerBtn = findViewById(R.id.id_register);
        checkbox = findViewById(R.id.visualizzaPass);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtemail.getText().toString();
                password = edtPassword.getText().toString();

                boolean b = Utils.Controll(LoginActivity.this, email, password, MIN_DIMENSION_PASSWORD);
                if(b){
                    Utils.showToast(LoginActivity.this, R.string.operation_success);
                }else{
                    Utils.showToast(LoginActivity.this, R.string.operation_fail);
                }
            }
        });

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





    }


}
