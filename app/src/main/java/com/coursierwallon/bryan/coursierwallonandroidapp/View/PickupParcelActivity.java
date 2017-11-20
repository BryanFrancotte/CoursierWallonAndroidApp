package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.*;
import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.AddressDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 19-11-17.
 */

public class PickupParcelActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap map;
    private ListView addressList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_parcel);

        Toolbar myToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addressList = findViewById(R.id.list_address_pickup);
        new GetAllPickupAddressByUser().execute(DevConstant.USER_MJ);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        goToLocationZoom(GoogleMapsConstant.COURSIER_LAT, GoogleMapsConstant.COURSIER_LNG, GoogleMapsConstant.MAP_ZOOM);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(GoogleMapsConstant.COURSIER_LAT, GoogleMapsConstant.COURSIER_LNG))
                .title(getString(R.string.coursier_map_marker_name))
                .snippet(getString(R.string.coursier_map_marker_snippet))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                Geocoder geocoder = new Geocoder(PickupParcelActivity.this);
                List<Address> listNewAddress = null;
                try {
                    listNewAddress = geocoder.getFromLocation(point.latitude, point.longitude,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address addressInterestPoint = listNewAddress.get(0);
                String stringAddress = addressInterestPoint.getAddressLine(0);


                Toast.makeText(getApplicationContext(), stringAddress, Toast.LENGTH_LONG).show();



            }
        });
    }

    private void goToLocationZoom(double lat, double lon, float zoom){
        LatLng place = new LatLng(lat,lon);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(place, zoom);
        map.moveCamera(cameraUpdate);
    }

    private class GetAllPickupAddressByUser extends AsyncTask<Long, Void, ArrayList<AddressModel>>{

        @Override
        protected ArrayList<AddressModel> doInBackground(Long... longs) {
            AddressDAO dao = new AddressDAO();
            ArrayList<AddressModel> addressList;
            try {
                addressList = dao.getAllPickUpAddressByUser(longs[0]);
            }catch (Exception e){
                e.printStackTrace();
                addressList = null;
            }
            return addressList;
        }

        @Override
        protected void onPostExecute(ArrayList<AddressModel> addresses){
            ArrayAdapter<AddressModel> adapter = new ArrayAdapter<AddressModel>(PickupParcelActivity.this,R.layout.list_view_address, addresses);
            addressList.setAdapter(adapter);
        }
    }
}
