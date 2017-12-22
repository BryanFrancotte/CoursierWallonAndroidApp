package com.coursierwallon.bryan.coursierwallonandroidapp.Exceptions;

import android.content.res.Resources;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;

import java.net.HttpURLConnection;

/**
 * Created by bryan on 02-12-17.
 */

public class HttpResultException extends Exception {

    private int statusCode;

    public HttpResultException(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        if(statusCode >= HttpURLConnection.HTTP_INTERNAL_ERROR){
            return Resources.getSystem().getString(R.string.HttpUrlConnection_InternalError);
        }
        if(statusCode >= HttpURLConnection.HTTP_BAD_REQUEST){
            if(statusCode == HttpURLConnection.HTTP_BAD_REQUEST || statusCode == HttpURLConnection.HTTP_UNAUTHORIZED){
                return "email error or password error";//Resources.getSystem().getString(R.string.HttpConnection_Unauthorized);
            }
            return Resources.getSystem().getString(R.string.HttpUrlConnection_ClientError);
        }
        return super.getMessage();
    }
}
