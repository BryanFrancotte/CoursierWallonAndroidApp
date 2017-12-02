package com.coursierwallon.bryan.coursierwallonandroidapp.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AddressModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.coursierwallon.bryan.coursierwallonandroidapp.ViewTemplates.AddressPicker;

/**
 * Created by franc on 01-12-17.
 */

public class AddressPickerFragment extends DialogFragment {

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. *//*
    public interface AddressPickerListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    AddressPickerListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddressPickerListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }*/

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_add_address_form, null))
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AddressPickerFragment.this.action();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddressPickerFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public void action(){
        AddressPicker activity = (AddressPicker) getActivity();
        EditText street = getDialog().findViewById(R.id.dialog_add_address_street);
        EditText houseNumber = getDialog().findViewById(R.id.dialog_add_address_houseNumber);
        EditText boxNumber = getDialog().findViewById(R.id.dialog_add_address_boxNumber);
        EditText locality = getDialog().findViewById(R.id.dialog_add_address_locality);
        EditText postalCode = getDialog().findViewById(R.id.dialog_add_address_postalCode);

        if(!street.getText().toString().isEmpty() && !houseNumber.getText().toString().isEmpty() &&
                !locality.getText().toString().isEmpty() && !postalCode.getText().toString().isEmpty()) {
            AddressModel newAddress = new AddressModel(
                    street.getText().toString(),
                    houseNumber.getText().toString(),
                    boxNumber.getText().toString(),
                    locality.getText().toString(),
                    postalCode.getText().toString()
            );
            activity.actionOnDialog(newAddress);
        }else{
            Toast.makeText(activity, "Invalid address", Toast.LENGTH_SHORT).show();
        }
    }
}
