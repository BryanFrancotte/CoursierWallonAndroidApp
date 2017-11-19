package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;
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
import java.util.List;

/**
 * Created by franc on 19-11-17.
 */

public class pickupParcelActivity extends AppCompatActivity implements OnMapReadyCallback/*, GoogleMap.OnMarkerClickListener*/{
    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_parcel);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        goToLocationZoom(50.469424, 4.86253, 14);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(50.469424, 4.86253))
                .title("Gare de namur")
                .snippet("Belle gare")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //googleMap.setOnMarkerClickListener(this);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                Geocoder geocoder = new Geocoder(pickupParcelActivity.this);
                List<Address> listAddress = null;
                try {
                    listAddress = geocoder.getFromLocation(point.latitude, point.longitude,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address addressInterestPoint = listAddress.get(0);
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

    /*@Override
    public boolean onMarkerClick(final Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
        return true;
    }*/
}
