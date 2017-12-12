package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by franc on 21-11-17.
 */

public class OrderModel {

    private Long orderNumber;
    private String state;
    private Date pickUpDate;
    private String pickUpStartTime;
    private String pickUpEndTime;
    private Date depositDate;
    private String depositStartTime;
    private String depositEndTime;
    private int deliveryType;
    private String userIdOrder;
    private Long billingAddress;
    private Long pickUpAddress;
    private Long depositAddress;
    private AddressModel billingAddressNavigation;
    private AddressModel depositAddressNavigation;
    private AddressModel pickUpAddressNavigation;
    private ArrayList<ParcelModel> parcel = new ArrayList<>();
    private String androidToken;

    public OrderModel(String state, String userIdOrder) {
        this.orderNumber = null;
        this.state = state;
        this.userIdOrder = userIdOrder;
        this.billingAddress = null;
        this.pickUpAddress = null;
        this.depositAddress = null;
        this.androidToken = FirebaseInstanceId.getInstance().getToken();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getPickUpStartTime() {
        return pickUpStartTime;
    }

    public void setPickUpStartTime(String pickUpStartTime) {
        this.pickUpStartTime = pickUpStartTime;
    }

    public String getPickUpEndTime() {
        return pickUpEndTime;
    }

    public void setPickUpEndTime(String pickUpEndTime) {
        this.pickUpEndTime = pickUpEndTime;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositStartTime() {
        return depositStartTime;
    }

    public void setDepositStartTime(String depositStartTime) {
        this.depositStartTime = depositStartTime;
    }

    public String getDepositEndTime() {
        return depositEndTime;
    }

    public void setDepositEndTime(String depositEndTime) {
        this.depositEndTime = depositEndTime;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getUserIdOrder() {
        return userIdOrder;
    }

    public void setUserIdOrder(String userIdOrder) {
        this.userIdOrder = userIdOrder;
    }

    public Long getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Long billingAddress) {
        this.billingAddress = billingAddress;
    }

    public AddressModel getBillingAddressNavigation() {
        return billingAddressNavigation;
    }

    public void setBillingAddressNavigation(AddressModel billingAddressNavigation) {
        this.billingAddressNavigation = billingAddressNavigation;
    }

    public Long getDepositAddress() {
        return depositAddress;
    }

    public void setDepositAddress(Long depositAddress) {
        this.depositAddress = depositAddress;
    }

    public AddressModel getDepositAddressNavigation() {
        return depositAddressNavigation;
    }

    public void setDepositAddressNavigation(AddressModel depositAddressNavigation) {
        this.depositAddressNavigation = depositAddressNavigation;
    }

    public Long getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(Long pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public AddressModel getPickUpAddressNavigation() {
        return pickUpAddressNavigation;
    }

    public void setPickUpAddressNavigation(AddressModel pickUpAddressNavigation) {
        this.pickUpAddressNavigation = pickUpAddressNavigation;
    }

    public ArrayList<ParcelModel> getParcel() {
        return parcel;
    }

    public ParcelModel getParcel(int index){
        return parcel.get(index);
    }

    public void addPacel(ParcelModel newParcel) {
        this.parcel.add(newParcel);
    }
}