package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.coursierwallon.bryan.coursierwallonandroidapp.DialogFragment.*;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;

/**
 * Created by franc on 25-11-17.
 */

public class DateTimePicker extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_date_time_picker);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "TimePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
