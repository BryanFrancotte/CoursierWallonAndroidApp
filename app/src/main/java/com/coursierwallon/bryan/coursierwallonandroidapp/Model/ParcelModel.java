package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 21-11-17.
 */

public class ParcelModel {

    private long parcelId;
    private int parcelType;
    private long orderNumberParcel;
    private OrderModel orderNumberParcelNavigation;

    public ParcelModel(int parcelType, OrderModel order) {
        this.parcelType = parcelType;
        orderNumberParcelNavigation = order;
    }

    public ParcelModel(long parcelId, int parcelType, long orderNumberParcel, OrderModel orderNumberParcelNavigation) {
        this.parcelId = parcelId;
        this.parcelType = parcelType;
        this.orderNumberParcel = orderNumberParcel;
        this.orderNumberParcelNavigation = orderNumberParcelNavigation;
    }

    public long getParcelId() {
        return parcelId;
    }

    public void setParcelId(long parcelId) {
        this.parcelId = parcelId;
    }

    public int getParcelType() {
        return parcelType;
    }

    public void setParcelType(int parcelType) {
        this.parcelType = parcelType;
    }

    public long getOrderNumberParcel() {
        return orderNumberParcel;
    }

    public void setOrderNumberParcel(long orderNumberParcel) {
        this.orderNumberParcel = orderNumberParcel;
    }

    public OrderModel getOrderNumberParcelNavigation() {
        return orderNumberParcelNavigation;
    }

    public void setOrderNumberParcelNavigation(OrderModel orderNumberParcelNavigation) {
        this.orderNumberParcelNavigation = orderNumberParcelNavigation;
    }
}
