package com.coursierwallon.bryan.coursierwallonandroidapp.ListViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 20-11-17.
 */

public class AddressArrayAdapter extends ArrayAdapter<AddressModel> {

    private Context context;
    private List<AddressModel> lstAddress;

    public AddressArrayAdapter (Context context, int resource, ArrayList<AddressModel> lstAddress){
        super(context, resource, lstAddress);
        this.context = context;
        this.lstAddress = lstAddress;
        this.lstAddress = lstAddress;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        AddressModel address = lstAddress.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.list_view_address, null);

        TextView addressLine1 = view.findViewById(R.id.address_line_1);
        TextView addressLine2 = view.findViewById(R.id.address_line_2);
        final CheckBox checkBox = view.findViewById(R.id.list_view_address_check_box);

        String addressLine1String = address.getStreet()
                + ", " + address.getHouseNumber()
                + ((address.getBoxNumber() != null)?(" (" +address.getBoxNumber() + ")" ): "");
        String addressLine2String = address.getLocalityIdAddressNavigation().getName()
                + ", " + address.getLocalityIdAddressNavigation().getPostalCode();

        addressLine1.setText(addressLine1String);
        addressLine2.setText(addressLine2String);
        checkBox.setClickable(false);

        return view;
    }

    @Override
    public AddressModel getItem(int position) {
        return super.getItem(position);
    }
}