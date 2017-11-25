package com.coursierwallon.bryan.coursierwallonandroidapp.ViewTemplates;

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
import android.widget.TextView;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.DevConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.GoogleMapsConstant;
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
 * Created by bryan on 22-11-17.
 */

public abstract class AddressPicker extends AppCompatActivity implements OnMapReadyCallback{

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

        addressList = findViewById(R.id.list_address);
        new GetAllPickupAddressByUser().execute(DevConstant.USER_MJ);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem nextItem = menu.findItem(R.id.order_menu_next);
        TextView rootView = (TextView) nextItem.getActionView();
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(nextItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.order_menu_next:
                setActionOnNextButton();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        Geocoder geocoder = new Geocoder(AddressPicker.this);
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
                .snippet(address)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));
    }

    public void removeCurrentMarker(){
        if(currentMarker != null){
            currentMarker.remove();
            currentMarker = null;
        }
    }

    public abstract void setActionOnNextButton();

    public abstract ArrayList<AddressModel> getAddressMethod(long userId) throws Exception;

    private class GetAllPickupAddressByUser extends AsyncTask<Long, Void, ArrayList<AddressModel>> {

        @Override
        protected ArrayList<AddressModel> doInBackground(Long... longs) {
            ArrayList<AddressModel> addressList = null;
            try {
                addressList = getAddressMethod(longs[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            return addressList;
        }

        @Override
        protected void onPostExecute(ArrayList<AddressModel> addresses){
            ArrayAdapter<AddressModel> adapter = new AddressArrayAdapter(AddressPicker.this,R.layout.list_view_address, addresses);
            addressList.setAdapter(adapter);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            addressList.setVisibility(View.VISIBLE);
        }
    }
}
