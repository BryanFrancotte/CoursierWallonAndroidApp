package com.coursierwallon.bryan.coursierwallonandroidapp.DAO;

import android.support.annotation.RequiresApi;
import android.util.Log;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.ApiConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Exceptions.HttpResultException;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AccessToken;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.UserModel;
import com.google.android.gms.fido.u2f.api.messagebased.RequestType;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by franc on 11-11-17.
 */

public class UserDAO {

    public AccessToken connexion(UserModel user)throws Exception{

        Gson gson = new Gson();
        String outputJsonString = gson.toJson(user);
        URL url = new URL(ApiConstant.URL_BASE + ApiConstant.URL_JWT);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        byte[] outputBytes = outputJsonString.getBytes("UTF-8");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(outputBytes);
        outputStream.flush();
        outputStream.close();

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String inputJsonString = "",line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
            connection.disconnect();
            inputJsonString = stringBuilder.toString();
            return gson.fromJson(inputJsonString,AccessToken.class);
        }else {
            throw new HttpResultException(connection.getResponseCode());
        }
    }

    public int registration(UserModel newUser)throws Exception{

        int resultCode;
        Gson gson = new Gson();
        String outputJsonString = gson.toJson(newUser);
        URL url = new URL(ApiConstant.URL_BASE + ApiConstant.URL_ACCOUNT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(ApiConstant.REQUEST_POST);
        connection.setDoInput(false);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        byte[] outputBytes = outputJsonString.getBytes("UTF-8");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(outputBytes);
        outputStream.flush();
        outputStream.close();
        resultCode = connection.getResponseCode();
        connection.disconnect();
        return resultCode;
    }
}