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
        AddressModel pickUpAddress = (AddressModel) getAddressList().getItemAtPosition(getCurrentSelectedItem());
        OrderModel newOrder = setUpNewOrder(pickUpAddress);
        newOrder.setBillingAddress(pickUpAddress.getAddressId());
        newOrder.setPickUpAddress(pickUpAddress.getAddressId());
        goToIntent(newOrder);
    }

    @Override
    public void actionOnDialog(AddressModel newAddress) {
        OrderModel newOrder = setUpNewOrder(newAddress);
        goToIntent(newOrder);
    }

    @Override
    public ArrayList<AddressModel> getAddressMethod(String userId) throws Exception {
        AddressDAO dao = new AddressDAO();
        return dao.getAllPickUpAddressByUser(userId);
    }

    public OrderModel setUpNewOrder(AddressModel newAddress){
        OrderModel newOrder = new OrderModel(OrderConstant.STATE, DevConstant.USER_BRYAN);
        newOrder.setBillingAddressNavigation(newAddress);
        newOrder.setPickUpAddressNavigation(newAddress);
        return newOrder;
    }

    public void goToIntent(OrderModel newOrder){
        Gson gson = new Gson();
        String newOrderString = gson.toJson(newOrder);
        Intent intentToDeposit = new Intent(this, DepositParcelActivity.class);
        intentToDeposit.putExtra("newOrder", newOrderString);
        startActivity(intentToDeposit);
    }
}
