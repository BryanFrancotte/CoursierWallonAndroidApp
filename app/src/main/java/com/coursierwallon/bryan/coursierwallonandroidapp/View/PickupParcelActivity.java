package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.*;
import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.AddressDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.ListViewAdapter.AddressArrayAdapter;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.coursierwallon.bryan.coursierwallonandroidapp.ViewTemplates.AddressPicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 19-11-17.
 */
public class PickupParcelActivity extends AddressPicker{

    @Override
    public void setActionOnNextButton() {
        Intent intentToDeposit = new Intent(this, DepositParcelActivity.class);
        startActivity(intentToDeposit);
    }

    @Override
    public ArrayList<AddressModel> getAddressMethod(long userId) throws Exception {
        AddressDAO dao = new AddressDAO();
        return dao.getAllPickUpAddressByUser(userId);
    }
}
