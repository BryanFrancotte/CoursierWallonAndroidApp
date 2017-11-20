package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.GoogleMapsConstant;
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
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        goToLocationZoom(GoogleMapsConstant.COURSIER_LAT, GoogleMapsConstant.COURSIER_LNG, GoogleMapsConstant.MAP_ZOOM);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(GoogleMapsConstant.COURSIER_LAT, GoogleMapsConstant.COURSIER_LNG))
                .title(getString(R.string.coursier_map_marker_name))
                .snippet(getString(R.string.coursier_map_marker_snippet))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))); // conseiller du 18px pour les png
        /*googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(PickupParcelActivity.this, marker.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });*/
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                Geocoder geocoder = new Geocoder(MapsActivity.this);
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
}
