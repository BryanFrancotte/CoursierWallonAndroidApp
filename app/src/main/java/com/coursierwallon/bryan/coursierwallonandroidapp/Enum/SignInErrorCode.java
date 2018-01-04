package com.coursierwallon.bryan.coursierwallonandroidapp.Enum;

/**
 * Created by bryan on 04-01-18.
 */

public enum SignInErrorCode {
    OK (2000),
    BADUSERNAME (4001),
    BADEMAIL (4002),
    BADPASSWORD (4003),
    PASSWORDMISSMATCH (4004)
    ;

    public final int errorCode;

    SignInErrorCode(int errorCode){
        this.errorCode = errorCode;
    }
}
