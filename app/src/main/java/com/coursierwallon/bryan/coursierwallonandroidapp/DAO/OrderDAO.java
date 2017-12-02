package com.coursierwallon.bryan.coursierwallonandroidapp.DAO;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.ApiConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.OrderModel;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;

/**
 * Created by franc on 01-12-17.
 */

public class OrderDAO {
    public int addOrder(OrderModel newOrder) throws Exception{
        URL url =  new URL(ApiConstant.URL_BASE + ApiConstant.URL_AddOrder);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(false);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        String newOrderString = prepareNewOrder(newOrder);

        byte[] output = newOrderString.getBytes("UTF-8");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(output);
        outputStream.flush();
        outputStream.close();

        int response = connection.getResponseCode();
        connection.disconnect();

        return response;
    }

    private String prepareNewOrder(OrderModel newOrder){
        Gson gson = new Gson();
        if(newOrder.getBillingAddress() != null){
            newOrder.setBillingAddressNavigation(null);
        }
        if(newOrder.getPickUpAddress() != null){
            newOrder.setPickUpAddressNavigation(null);
        }
        if(newOrder.getDepositAddress() != null){
            newOrder.setDepositAddressNavigation(null);
        }
        return gson.toJson(newOrder);
    }
}
