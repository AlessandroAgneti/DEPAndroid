package com.example.depeat.services;

import android.arch.persistence.room.TypeConverter;

import com.example.depeat.datamodels.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static ArrayList<Food> fromString(String value){
        Type listType = new TypeToken<ArrayList<Food>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Food> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
