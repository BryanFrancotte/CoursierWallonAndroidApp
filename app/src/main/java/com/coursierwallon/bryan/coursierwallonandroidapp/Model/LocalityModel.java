package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 20-11-17.
 */

public class LocalityModel {

    private Long localityId;
    private String name;
    private int postalCode;

    public LocalityModel(String name, String postalCode) {
        this.localityId = null;
        this.name = name;
        this.postalCode = Integer.parseInt(postalCode);
    }

    public Long getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
