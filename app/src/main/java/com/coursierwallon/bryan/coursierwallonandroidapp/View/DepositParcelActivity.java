package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.os.Bundle;

import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.AddressDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.OrderModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.ViewTemplates.AddressPicker;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by franc on 21-11-17.
 */

public class DepositParcelActivity extends AddressPicker{

    @Override
    public void actionOnNextButton() {
        Gson gson = new Gson();
        OrderModel newOrder = getNewOrder(gson);
        AddressModel depositAddress = (AddressModel) getAddressList().getItemAtPosition(getCurrentSelectedItem());
        newOrder.setDepositAddress(depositAddress.getAddressId());
        newOrder.setDepositAddressNavigation(depositAddress);
        goToIntent(gson, newOrder);
    }

    @Override
    public void actionOnDialog(AddressModel newAddress) {
        Gson gson = new Gson();
        OrderModel newOrder = getNewOrder(gson);
        newOrder.setDepositAddressNavigation(newAddress);
        goToIntent(gson, newOrder);
    }

    @Override
    public ArrayList<AddressModel> getAddressMethod(String userId, String token) throws Exception {
        AddressDAO dao = new AddressDAO();
        return dao.getAllDepositAddressByUser(userId, token);
    }

    public OrderModel getNewOrder(Gson gson){
        Bundle bundle = this.getIntent().getExtras();
        String newOrderString = bundle.getString("newOrder");
        return gson.fromJson(newOrderString, OrderModel.class);
    }

    public void goToIntent(Gson gson, OrderModel newOrder){
        Intent intentToDateTimePicker = new Intent(this, DateTimePickerActivity.class);
        intentToDateTimePicker.putExtra("newOrder", gson.toJson(newOrder));
        startActivity(intentToDateTimePicker);
    }
}
