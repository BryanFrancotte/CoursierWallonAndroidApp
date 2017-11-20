package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 20-11-17.
 */

public class LocalityModel {

    private long localityId;
    private String name;
    private int postalCode;

    public LocalityModel(long localityId, String name, int postalCode) {
        this.localityId = localityId;
        this.name = name;
        this.postalCode = postalCode;
    }

    public long getLocalityId() {
        return localityId;
    }

    public void setLocalityId(long localityId) {
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
