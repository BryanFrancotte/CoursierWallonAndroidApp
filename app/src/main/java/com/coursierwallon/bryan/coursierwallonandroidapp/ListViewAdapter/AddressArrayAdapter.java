package com.coursierwallon.bryan.coursierwallonandroidapp.ListViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.GoogleMapsConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.coursierwallon.bryan.coursierwallonandroidapp.View.PickupParcelActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 20-11-17.
 */

public class AddressArrayAdapter extends ArrayAdapter<AddressModel> {

    private Context context;
    private PickupParcelActivity activity;
    private List<AddressModel> lstAddress;

    public AddressArrayAdapter (Context context, int resource, ArrayList<AddressModel> lstAddress){
        super(context, resource, lstAddress);
        this.context = context;
        this.activity = (PickupParcelActivity) context;
        this.lstAddress = lstAddress;
        this.lstAddress = lstAddress;
    }

    public View getView(int position, View convertView, final ViewGroup parent){

        AddressModel address = lstAddress.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.list_view_address, null);

        TextView addressLine1 = view.findViewById(R.id.address_line_1);
        TextView addressLine2 = view.findViewById(R.id.address_line_2);
        final CheckBox checkBox = view.findViewById(R.id.list_view_address_check_box);

        final String addressLine1String = address.getStreet()
                + ", " + address.getHouseNumber()
                + ((address.getBoxNumber() != null)?(" (" +address.getBoxNumber() + ")" ): "");
        final String addressLine2String = address.getLocalityIdAddressNavigation().getName()
                + ", " + address.getLocalityIdAddressNavigation().getPostalCode();

        addressLine1.setText(addressLine1String);
        addressLine2.setText(addressLine2String);
        checkBox.setClickable(false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < parent.getChildCount(); i++){
                    if(!view.equals(parent.getChildAt(i))) {
                        View child = parent.getChildAt(i);
                        CheckBox childCheckBox = child.findViewById(R.id.list_view_address_check_box);
                        if(childCheckBox.isChecked()){
                            childCheckBox.setChecked(false);
                        }
                    }
                }
                checkBox.setChecked(!checkBox.isChecked());
                activity.removeCurrentMarker();
                if(checkBox.isChecked()) {
                    String addressText = addressLine1String + " " + addressLine2String;
                    activity.goToLocationZoom(addressText);
                }else {
                    activity.initLocationZoom(GoogleMapsConstant.COURSIER_LAT, GoogleMapsConstant.COURSIER_LNG, GoogleMapsConstant.MAP_ZOOM);
                }
            }
        });

        return view;
    }

    @Override
    public AddressModel getItem(int position) {
        return super.getItem(position);
    }
}
