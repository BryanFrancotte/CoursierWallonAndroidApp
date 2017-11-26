package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by franc on 21-11-17.
 */

public class OrderModel {

    private long orderNumber;
    private String state;
    private Date pickUpDate;
    private String pickUpStartTime;
    private String pickUpEndTime;
    private Date depositDate;
    private String depositeStartTime;
    private String depositeEndTime;
    private String deliveryType;
    private long userIdOrder;
    private long pickUpAddress;
    private long depositAddress;
    private long billingAddress;
    private AddressModel billingAddressNavigation;
    private AddressModel depositAddressNavigation;
    private AddressModel pickUpAddressNavigation;
    private UserModel userIdOrderNavigation;

    public OrderModel(String state, long userIdOrder) {
        this.state = state;
        this.userIdOrder = userIdOrder;
    }

    public OrderModel(long orderNumber, String state, Date pickUpDate, String pickUpStartTime, String pickUpEndTime, Date depositDate, String depositeStartTime, String depositeEndTime, String deliveryType, long userIdOrder, long pickUpAddress, long depositAddress, long billingAddress, AddressModel billingAddressNavigation, AddressModel depositAddressNavigation, AddressModel pickUpAddressNavigation, UserModel userIdOrderNavigation) {
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

    public String getDepositeStartTime() {
        return depositeStartTime;
    }

    public void setDepositeStartTime(String depositeStartTime) {
        this.depositeStartTime = depositeStartTime;
    }

    public String getDepositeEndTime() {
        return depositeEndTime;
    }

    public void setDepositeEndTime(String depositeEndTime) {
        this.depositeEndTime = depositeEndTime;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
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
}
