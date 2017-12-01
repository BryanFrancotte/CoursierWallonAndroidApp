package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by franc on 21-11-17.
 */

public class OrderModel {

    private long orderNumber;
    private String state;
    private Date pickUpDate;
    private Time pickUpStartTime;
    private Time pickUpEndTime;
    private Date depositDate;
    private Time depositeStartTime;
    private Time depositeEndTime;
    private int deliveryType;
    private long userIdOrder;
    private long pickUpAddress;
    private long depositAddress;
    private long billingAddress;
    private AddressModel billingAddressNavigation;
    private AddressModel depositAddressNavigation;
    private AddressModel pickUpAddressNavigation;
    private UserModel userIdOrderNavigation;
    private ArrayList<ParcelModel> parcel;

    public OrderModel(String state, long userIdOrder) {
        this.state = state;
        this.userIdOrder = userIdOrder;
    }

    public OrderModel(long orderNumber, String state, Date pickUpDate, Time pickUpStartTime, Time pickUpEndTime, Date depositDate, Time depositeStartTime, Time depositeEndTime, int deliveryType, long userIdOrder, long pickUpAddress, long depositAddress, long billingAddress, AddressModel billingAddressNavigation, AddressModel depositAddressNavigation, AddressModel pickUpAddressNavigation, UserModel userIdOrderNavigation) {
        this.orderNumber = orderNumber;
        this.state = state;
        this.pickUpDate = pickUpDate;
        this.pickUpStartTime = pickUpStartTime;
        this.pickUpEndTime = pickUpEndTime;
        this.depositDate = depositDate;
        this.depositeStartTime = depositeStartTime;
        this.depositeEndTime = depositeEndTime;
        this.deliveryType = deliveryType;
        this.userIdOrder = userIdOrder;
        this.pickUpAddress = pickUpAddress;
        this.depositAddress = depositAddress;
        this.billingAddress = billingAddress;
        this.billingAddressNavigation = billingAddressNavigation;
        this.depositAddressNavigation = depositAddressNavigation;
        this.pickUpAddressNavigation = pickUpAddressNavigation;
        this.userIdOrderNavigation = userIdOrderNavigation;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
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

    public Time getPickUpStartTime() {
        return pickUpStartTime;
    }

    public void setPickUpStartTime(Time pickUpStartTime) {
        this.pickUpStartTime = pickUpStartTime;
    }

    public Time getPickUpEndTime() {
        return pickUpEndTime;
    }

    public void setPickUpEndTime(Time pickUpEndTime) {
        this.pickUpEndTime = pickUpEndTime;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public Time getDepositeStartTime() {
        return depositeStartTime;
    }

    public void setDepositeStartTime(Time depositeStartTime) {
        this.depositeStartTime = depositeStartTime;
    }

    public Time getDepositeEndTime() {
        return depositeEndTime;
    }

    public void setDepositeEndTime(Time depositeEndTime) {
        this.depositeEndTime = depositeEndTime;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public long getUserIdOrder() {
        return userIdOrder;
    }

    public void setUserIdOrder(long userIdOrder) {
        this.userIdOrder = userIdOrder;
    }

    public long getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(long pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public long getDepositAddress() {
        return depositAddress;
    }

    public void setDepositAddress(long depositAddress) {
        this.depositAddress = depositAddress;
    }

    public long getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(long billingAddress) {
        this.billingAddress = billingAddress;
    }

    public AddressModel getBillingAddressNavigation() {
        return billingAddressNavigation;
    }

    public void setBillingAddressNavigation(AddressModel billingAddressNavigation) {
        this.billingAddressNavigation = billingAddressNavigation;
    }

    public AddressModel getDepositAddressNavigation() {
        return depositAddressNavigation;
    }

    public void setDepositAddressNavigation(AddressModel depositAddressNavigation) {
        this.depositAddressNavigation = depositAddressNavigation;
    }

    public AddressModel getPickUpAddressNavigation() {
        return pickUpAddressNavigation;
    }

    public void setPickUpAddressNavigation(AddressModel pickUpAddressNavigation) {
        this.pickUpAddressNavigation = pickUpAddressNavigation;
    }

    public UserModel getUserIdOrderNavigation() {
        return userIdOrderNavigation;
    }

    public void setUserIdOrderNavigation(UserModel userIdOrderNavigation) {
        this.userIdOrderNavigation = userIdOrderNavigation;
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