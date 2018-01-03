package com.coursierwallon.bryan.coursierwallonandroidapp.DTO;

/**
 * Created by bryan on 03-01-18.
 */

public class FirebaseTokenDTO {
    private String userId;
    private String androidToken;

    public FirebaseTokenDTO(String id, String androidToken){
        this.userId = id;
        this.androidToken = androidToken;
    }
}
