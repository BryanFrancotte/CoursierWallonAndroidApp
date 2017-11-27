package com.coursierwallon.bryan.coursierwallonandroidapp.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.coursierwallon.bryan.coursierwallonandroidapp.View.DateTimePickerActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by franc on 25-11-17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH) + 1;
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day++);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        month++;
        String text = day + "/" + month + "/" + year;
        DateTimePickerActivity activity = (DateTimePickerActivity) this.getActivity();
        Date selectedDate = activity.convertStringToDate(text);
        setAction(text, selectedDate, activity);
    }

    public void setAction(String text, Date selectedDate, DateTimePickerActivity activity){
        switch (activity.getClickedButtonId()){
            case R.id.date_time_layout_pickupdate_button:
                if(selectedDate.after(Calendar.getInstance().getTime())) {
                    activity.getPickUpDateText().setText(text);
                    activity.setPickUpDate(selectedDate);
                }else {
                    Toast.makeText(activity, "erreur",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.date_time_layout_depositdate_button:
                if(selectedDate.getTime() >= activity.getPickUpDate().getTime()) {
                    activity.getDepositDateText().setText(text);
                    activity.setDepositDate(selectedDate);
                }else {
                    Toast.makeText(activity, "erreur",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
