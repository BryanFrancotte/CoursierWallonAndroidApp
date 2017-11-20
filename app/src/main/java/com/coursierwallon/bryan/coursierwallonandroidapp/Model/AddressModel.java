package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 20-11-17.
 */

public class AddressModel {
    private long addressId;
    private String street;
    private String houseNumber;
    private String boxNumber;
    private long localityIdAddress;
    private LocalityModel localityIdAddressNavigation;

    public AddressModel(long addressId, String street, String houseNumber, String boxNumber, long localityIdAddress, LocalityModel localityIdAddressNavigation) {
        this.addressId = addressId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.boxNumber = boxNumber;
        this.localityIdAddress = localityIdAddress;
        this.localityIdAddressNavigation = localityIdAddressNavigation;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public long getLocalityIdAddress() {
        return localityIdAddress;
    }

    public void setLocalityIdAddress(long localityIdAddress) {
        this.localityIdAddress = localityIdAddress;
    }

    public LocalityModel getLocalityIdAddressNavigation() {
        return localityIdAddressNavigation;
    }

    public void setLocalityIdAddressNavigation(LocalityModel localityIdAddressNavigation) {
        this.localityIdAddressNavigation = localityIdAddressNavigation;
    }

    public String toString(){
        return this.street + ", " + this.houseNumber + ((this.boxNumber != null)?(" (" +this.boxNumber + ")\n"):"\n")
                + this.localityIdAddressNavigation.getName() + ", " + this.localityIdAddressNavigation.getPostalCode();
    }
}
