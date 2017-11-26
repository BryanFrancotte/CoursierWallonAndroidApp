package com.coursierwallon.bryan.coursierwallonandroidapp.DialogFragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.coursierwallon.bryan.coursierwallonandroidapp.View.DateTimePickerActivity;

import java.util.Calendar;

/**
 * Created by franc on 25-11-17.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        String text = hourOfDay + "h" +((minute < 10)? "0" + minute : minute);
        DateTimePickerActivity activity = (DateTimePickerActivity) this.getActivity();
        setAction(text, activity);
    }

    public void setAction(String text, DateTimePickerActivity activity){
        switch (activity.getClickedButtonId()){
            case R.id.date_time_layout_pickupTimeStart_button:
                activity.getPickUpStarTimeText().setText(text);
                activity.setPickUpStartTime(text);
                break;
            case R.id.date_time_layout_pickupTimeEnd_button:
                activity.getPickUpEndTimeText().setText(text);
                activity.setPickUpEndTime(text);
                break;
            case R.id.date_time_layout_depositTimeStart_button:
                activity.getDepositStartTimeText().setText(text);
                activity.setDepositStartTime(text);
                break;
            case R.id.date_time_layout_depositTimeEnd_button:
                activity.getDepositEndTimeText().setText(text);
                activity.setDepositEndTime(text);
                break;
        }
    }
}
