package com.coursierwallon.bryan.coursierwallonandroidapp.DAO;

import android.util.Log;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AccessToken;
import com.coursierwallon.bryan.coursierwallonandroidapp.View.LoginActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by franc on 23-11-17.
 */

public class ValuesDAO {
    public String getAll(String token) throws Exception{
        URL url = new URL("http://apicoursier.azurewebsites.net/api/Values");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(false);
        connection.setDoInput(true);
        connection.setRequestProperty("Authorization", "Bearer " + token);
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String inputJsonString = "",line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        }
        return null;
    }
}
