package com.example.depeat.datamodels;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public static final String REGISTER_ENDPOINT = "auth/local/register";
    public static final String LOGIN_ENDPOINT = "auth/local";
    public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";

    private String id, username, email, accessToken;

    public User(JSONObject user, String accessToken) throws JSONException{
        this.accessToken = accessToken;
        email = user.getString("email");
        id = user.getString("id");
        username = user.getString("username");

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
