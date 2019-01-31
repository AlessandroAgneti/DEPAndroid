package com.example.depeat;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.depeat.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public static boolean isValidEmail(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password){
        if(password.length() > 6){
            return true;
        }else{
            return false;
        }
    }

    public static boolean Controll(Context context, String email, String password, int dimensionPassword){
        if(isValidEmail(email) && isValidPassword(password)){
            return true;
        }else if(!(isValidEmail(email))){
            showToast(context,R.string.email_failed);
            return false;
        }else if(!isValidPassword(password)){
            showToast(context, R.string.error_dimension_pass);
            return false;
        }
        return false;
    }



    public static void showToast(Context context, @StringRes int id){
        Toast.makeText(context,context.getString(id),  Toast.LENGTH_SHORT).show();
    }
}
