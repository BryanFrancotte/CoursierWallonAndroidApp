package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

import java.util.Date;

/**
 * Created by franc on 11-11-17.
 */

public class UserModel {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private long codeRoleUser;
    private long addressIdUser;

    public UserModel(long userId, String firstName, String lastName, String email, String password, Date birthDate, long codeRoleUser, long addressIdUser){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.codeRoleUser = codeRoleUser;
        this.addressIdUser = addressIdUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString(){
        return "userId : " + userId +
                " firstName : " + firstName +
                " lastName : " + lastName +
                " email : " + email +
                " password : " + password +
                " birthDate : " + birthDate +
                " codeRoleUser : " + codeRoleUser +
                " addressIdUser : " + addressIdUser;
    }
}