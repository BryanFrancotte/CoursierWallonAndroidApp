package com.coursierwallon.bryan.coursierwallonandroidapp.Model;


/**
 * Created by franc on 11-11-17.
 */

public class UserModel {

    private String userName;
    private String email;
    private String password;
    private String passwordConfirmation;

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserModel(String userName, String email, String password, String passwordConfirmation) {
        this(email, password);
        this.userName = userName;
        this.passwordConfirmation = passwordConfirmation;
    }

    public boolean isvalid(){
        boolean validExpression =
                !userName.isEmpty() && !email.isEmpty() && !password.isEmpty()
                && !passwordConfirmation.isEmpty() && password.compareTo(passwordConfirmation) == 0;
        return validExpression;
    }
}