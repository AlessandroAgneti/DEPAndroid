package com.example.depeat.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static final String SharedPrefs = "com.example.depeat.general_prefs";

    public static void putValue(Context context, String key, boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putValue(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean getBooleanValue(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static String getStringValue(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefs, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }
}
