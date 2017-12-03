package com.coursierwallon.bryan.coursierwallonandroidapp.Exceptions;

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
        if(statusCode >= HttpURLConnection.HTTP_BAD_REQUEST){
            if(statusCode == HttpURLConnection.HTTP_BAD_REQUEST || statusCode == HttpURLConnection.HTTP_UNAUTHORIZED){
                return "E-mail ou mot de passe incorrect";
            }
            return "Erreur de connexion de l'appareil";
        }
        if(statusCode >= HttpURLConnection.HTTP_INTERNAL_ERROR){
            return "Le Serveur rencontre actuellement un problème";
        }
        return super.getMessage();
    }
}
