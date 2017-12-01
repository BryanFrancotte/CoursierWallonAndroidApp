package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.DevConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.OrderConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.AddressDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.OrderModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.ViewTemplates.AddressPicker;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by franc on 19-11-17.
 */
public class PickupParcelActivity extends AddressPicker{

    @Override
    public void actionOnNextButton() {
        Gson gson = new Gson();
        OrderModel newOrder = new OrderModel(OrderConstant.STATE, DevConstant.USER_BRYAN);
        AddressModel PickUpAddress = (AddressModel) getAddressList().getItemAtPosition(getCurrentSelectedItem());
        newOrder.setPickUpDate(OrderConstant.getDate());
        newOrder.setBillingAddress(PickUpAddress.getAddressId());
        newOrder.setPickUpAddress(PickUpAddress.getAddressId());
        String newOrderString = gson.toJson(newOrder);
        Intent intentToDeposit = new Intent(this, DepositParcelActivity.class);
        intentToDeposit.putExtra("newOrder", newOrderString);
        startActivity(intentToDeposit);
    }

    @Override
    public ArrayList<AddressModel> getAddressMethod(String userId) throws Exception {
        AddressDAO dao = new AddressDAO();
        return dao.getAllPickUpAddressByUser(userId);
    }
}
