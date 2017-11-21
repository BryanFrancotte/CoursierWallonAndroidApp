package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.DevConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.GoogleMapsConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.AddressDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.ListViewAdapter.AddressArrayAdapter;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 21-11-17.
 */

public class DepositParcelActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private ListView addressList;
    private Marker currentMarker = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_picker);

        Toolbar myToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addressList = findViewById(R.id.list_address_pickup);
        new GetAllDepositAddressByUser().execute(DevConstant.USER_MJ);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        initLocationZoom(GoogleMapsConstant.COURSIER_LAT, GoogleMapsConstant.COURSIER_LNG, GoogleMapsConstant.MAP_ZOOM);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(GoogleMapsConstant.COURSIER_LAT, GoogleMapsConstant.COURSIER_LNG))
                .title(getString(R.string.coursier_map_marker_name))
                .snippet(getString(R.string.coursier_map_marker_snippet))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker_s)));
    }

    public void initLocationZoom(double lat, double lon, float zoom){
        LatLng place = new LatLng(lat,lon);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(place, zoom);
        map.moveCamera(cameraUpdate);
    }

    public void goToLocationZoom(String address){
        Geocoder geocoder = new Geocoder(DepositParcelActivity.this);
        List<Address> lstResult = null;
        try{
            lstResult = geocoder.getFromLocationName(address, GoogleMapsConstant.MAX_RESULT);
        }catch (IOException e){
            e.printStackTrace();
        }
        Address result = lstResult.get(0);
        LatLng resultLatLong = new LatLng(result.getLatitude(), result.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(resultLatLong, GoogleMapsConstant.MAP_ZOOM_S);
        map.moveCamera(cameraUpdate);
        currentMarker = map.addMarker(new MarkerOptions()
                .position(resultLatLong)
                .title("test")
                .snippet(address)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));
    }

    public void removeCurrentMarker(){
        if(currentMarker != null){
            currentMarker.remove();
            currentMarker = null;
        }
    }

    private class GetAllDepositAddressByUser extends AsyncTask<Long, Void, ArrayList<AddressModel>> {

        @Override
        protected ArrayList<AddressModel> doInBackground(Long... longs) {
            AddressDAO dao = new AddressDAO();
            ArrayList<AddressModel> addressList = null;
            try {
                addressList = dao.getAllDepositAddressByUser(longs[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            return addressList;
        }

        @Override
        protected void onPostExecute(ArrayList<AddressModel> addresses){
            ArrayAdapter<AddressModel> adapter = new AddressArrayAdapter(DepositParcelActivity.this,R.layout.list_view_address, addresses);
            addressList.setAdapter(adapter);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            addressList.setVisibility(View.VISIBLE);
        }
    }
}
