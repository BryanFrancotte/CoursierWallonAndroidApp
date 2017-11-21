package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 21-11-17.
 */

public class ParcelModel {

    private long parcelId;
    private String parcelType;
    private long orderNumberParcel;
    private OrderModel orderNumberParcelNavigation;

    public ParcelModel(long parcelId, String parcelType, long orderNumberParcel, OrderModel orderNumberParcelNavigation) {
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

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
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
