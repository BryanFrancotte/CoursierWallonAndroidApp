package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 20-11-17.
 */

public class AddressModel {
    private Long addressId;
    private String street;
    private String houseNumber;
    private String boxNumber;
    private Long localityIdAddress;
    private LocalityModel localityIdAddressNavigation;

    public AddressModel(String street, String houseNumber, String boxNumber, String localityName, String postalCode) {
        this.addressId = null;
        this.street = street;
        this.houseNumber = houseNumber;
        this.setBoxNumber(boxNumber);
        this.localityIdAddress = null;
        this.localityIdAddressNavigation = new LocalityModel(localityName, postalCode);
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
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
        this.boxNumber = (boxNumber.isEmpty())? null : boxNumber;
    }

    public Long getLocalityIdAddress() {
        return localityIdAddress;
    }

    public void setLocalityIdAddress(Long localityIdAddress) {
        this.localityIdAddress = localityIdAddress;
    }

    public LocalityModel getLocalityIdAddressNavigation() {
        return localityIdAddressNavigation;
    }

    public void setLocalityIdAddressNavigation(LocalityModel localityIdAddressNavigation) {
        this.localityIdAddressNavigation = localityIdAddressNavigation;
    }

    public String toString(){
        return this.street + ", " + this.houseNumber + ((this.boxNumber != null)?(" (" +this.boxNumber + "), "):", ")
                + this.localityIdAddressNavigation.getName() + ", " + this.localityIdAddressNavigation.getPostalCode();
    }
}
