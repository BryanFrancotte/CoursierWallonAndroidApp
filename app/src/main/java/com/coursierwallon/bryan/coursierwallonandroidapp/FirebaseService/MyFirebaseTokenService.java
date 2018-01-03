package com.coursierwallon.bryan.coursierwallonandroidapp.FirebaseService;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.auth0.android.jwt.JWT;
import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.ApiConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.FirebaseDAO;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by bryan on 03-01-18.
 */

public class MyFirebaseTokenService extends FirebaseInstanceIdService {

    private Context applicationContext;

    public MyFirebaseTokenService(){}

    public MyFirebaseTokenService(Context context){
        this.applicationContext = context;
    }

    @Override
    public void onTokenRefresh() {
        FirebaseDAO dao = new FirebaseDAO();
        String firebaseToken = FirebaseInstanceId.getInstance().getToken();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.applicationContext);
        String userToken = preferences.getString(ApiConstant.TOKEN,null);
        JWT jwt = new JWT(userToken);
        String userId = jwt.getSubject();
        try {
            dao.sendRegistrationToServer(userId, firebaseToken, userToken);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
