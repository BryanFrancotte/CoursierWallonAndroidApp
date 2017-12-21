package com.coursierwallon.bryan.coursierwallonandroidapp.Model;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        boolean validExpression =
                !userName.isEmpty() && !email.isEmpty() && !password.isEmpty()
                && !passwordConfirmation.isEmpty() && password.compareTo(passwordConfirmation) == 0
                && matcher.find();
        return validExpression;
    }
}