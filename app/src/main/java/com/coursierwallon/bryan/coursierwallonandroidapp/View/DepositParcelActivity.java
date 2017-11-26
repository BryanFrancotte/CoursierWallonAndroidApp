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
        Bundle bundle = this.getIntent().getExtras();
        String newOrderString = bundle.getString("newOrder");
        OrderModel newOrder = gson.fromJson(newOrderString, OrderModel.class);
        AddressModel DepositAddress = (AddressModel) getAddressList().getItemAtPosition(getCurrentSelectedItem());
        newOrder.setDepositAddress(DepositAddress.getAddressId());
        Intent intentToDateTimePicker = new Intent(this, DateTimePickerActivity.class);
        intentToDateTimePicker.putExtra("newOrder", gson.toJson(newOrder));
        startActivity(intentToDateTimePicker);
    }

    @Override
    public ArrayList<AddressModel> getAddressMethod(long userId) throws Exception {
        AddressDAO dao = new AddressDAO();
        return dao.getAllDepositAddressByUser(userId);
    }
}
