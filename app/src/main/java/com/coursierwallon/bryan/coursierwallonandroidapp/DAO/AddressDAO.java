package com.coursierwallon.bryan.coursierwallonandroidapp.DAO;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.ApiConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by franc on 20-11-17.
 */

public class AddressDAO {
    public ArrayList<AddressModel> getAllPickUpAddressByUser(long userId) throws Exception{
        ArrayList<AddressModel> addresses = new ArrayList<>();
        Gson gson = new Gson();

        URL url = new URL(ApiConstant.URL_BASE + ApiConstant.URL_GetAllPickUpAddressByUser + Long.toString(userId));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(false);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String inputJsonString = "",line;
        while((line = bufferedReader.readLine()) != null){
            builder.append(line);
        }
        bufferedReader.close();
        connection.disconnect();

        JSONArray jsonArray = new JSONArray(builder.toString());
        for(int i =0; i < jsonArray.length(); i++){
            addresses.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), AddressModel.class));
        }
        return addresses;
    }
}
