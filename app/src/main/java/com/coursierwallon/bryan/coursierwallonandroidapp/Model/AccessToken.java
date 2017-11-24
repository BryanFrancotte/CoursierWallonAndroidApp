package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by franc on 23-11-17.
 */

public class AccessToken {
    @SerializedName("access_token")
    private String token;

    public AccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toString(){
        return token;
    }
}
