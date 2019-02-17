package com.example.depeat;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    private static boolean isValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isValidPassword(String password, int dimension_password){
        if(password.length() > dimension_password){
            return true;
        }else{
            return false;
        }
    }

    private static boolean isNumberValid(String numberPhone) {
        String expression = "^\\+[0-9]{10,13}$";
        //String expression = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(numberPhone);
        return matcher.matches();
    }

    public static boolean Controll(Context context, String email, String password, int dimension_password){
        if(isValidEmail(email) && isValidPassword(password, dimension_password)){
            return true;
        }else if(!(isValidEmail(email))){
            showToast(context,R.string.email_failed);
            return false;
        }else if(!isValidPassword(password, dimension_password)){
            showToast(context, R.string.error_dimension_pass);
            return false;
        }
        return false;
    }



    public static void showToast(Context context, @StringRes int id){
        Toast.makeText(context,context.getString(id),  Toast.LENGTH_SHORT).show();
    }

    public static boolean enableButton(Context context, String email, String password, String numberPhone, int dimension_password){
        if(isValidEmail(email) && isValidPassword(password, dimension_password) && isNumberValid(numberPhone)){
            return true;
        }else if (!isValidEmail(email)){
            return false;
        }else if (!isValidPassword(password, dimension_password)){
            return false;
        }else if(!isNumberValid(numberPhone)){
            return false;
        }
        return false;
    }
}
