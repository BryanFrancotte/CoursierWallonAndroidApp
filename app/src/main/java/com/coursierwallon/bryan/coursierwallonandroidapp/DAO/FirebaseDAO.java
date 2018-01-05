package com.coursierwallon.bryan.coursierwallonandroidapp.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.auth0.android.jwt.JWT;
import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.ApiConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.DTO.FirebaseTokenDTO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Exceptions.HttpResultException;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bryan on 03-01-18.
 */

public class FirebaseDAO {
    public void sendRegistrationToServer(String userId, String fireBaseToken, String token) throws Exception{
        FirebaseTokenDTO dto = new FirebaseTokenDTO(userId, fireBaseToken);

        Gson gson = new Gson();

        URL url =  new URL(ApiConstant.URL_BASE + ApiConstant.URL_UPDATE_FIREBASE_TOKEN);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(false);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");

        String firebaseTokenJson = gson.toJson(dto);

        byte[] output = firebaseTokenJson.getBytes("UTF-8");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(output);
        outputStream.flush();
        outputStream.close();

        if(connection.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST){
            throw new HttpResultException(connection.getResponseCode());
        }
        connection.disconnect();
    }
}
