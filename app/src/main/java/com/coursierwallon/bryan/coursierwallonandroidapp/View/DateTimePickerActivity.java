package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.DialogFragment.DatePickerFragment;
import com.coursierwallon.bryan.coursierwallonandroidapp.DialogFragment.TimePickerFragment;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.OrderModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.google.gson.Gson;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by franc on 25-11-17.
 */

public class DateTimePickerActivity extends AppCompatActivity {
    private int clickedButtonId;
    private TextView pickUpDateText, pickUpStarTimeText, pickUpEndTimeText;
    private TextView depositDateText, depositStartTimeText, depositEndTimeText;
    private Date pickUpDate, depositDate;
    private Time pickUpStartTime, pickUpEndTime, depositStartTime, depositEndTime;

    public int getClickedButtonId() {
        return clickedButtonId;
    }

    public TextView getPickUpDateText() {
        return pickUpDateText;
    }

    public TextView getPickUpStarTimeText() {
        return pickUpStarTimeText;
    }

    public TextView getPickUpEndTimeText() {
        return pickUpEndTimeText;
    }

    public TextView getDepositDateText() {
        return depositDateText;
    }

    public TextView getDepositStartTimeText() {
        return depositStartTimeText;
    }

    public TextView getDepositEndTimeText() {
        return depositEndTimeText;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public Time getPickUpStartTime() {
        return pickUpStartTime;
    }

    public void setPickUpStartTime(Time pickUpStartTime) {
        this.pickUpStartTime = pickUpStartTime;
    }

    public Time getPickUpEndTime() {
        return pickUpEndTime;
    }

    public void setPickUpEndTime(Time pickUpEndTime) {
        this.pickUpEndTime = pickUpEndTime;
    }

    public Time getDepositStartTime() {
        return depositStartTime;
    }

    public void setDepositStartTime(Time depositStartTime) {
        this.depositStartTime = depositStartTime;
    }

    public Time getDepositEndTime() {
        return depositEndTime;
    }

    public void setDepositEndTime(Time depositEndTime) {
        this.depositEndTime = depositEndTime;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_date_time_picker);

        Toolbar myToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pickUpDateText = findViewById(R.id.date_time_layout_pickupdate);
        pickUpStarTimeText = findViewById(R.id.date_time_layout_pickupTimeStart);
        pickUpEndTimeText = findViewById(R.id.date_time_layout_pickupTimeEnd);
        depositDateText = findViewById(R.id.date_time_layout_depositdate);
        depositStartTimeText = findViewById(R.id.date_time_layout_depositTimeStart);
        depositEndTimeText = findViewById(R.id.date_time_layout_depositTimeEnd);

        initDateAndTime();
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
                Gson gson = new Gson();
                Bundle bundle = getIntent().getExtras();
                OrderModel newOrder = gson.fromJson(bundle.getString("newOrder"), OrderModel.class);
                newOrder.setPickUpDate(pickUpDate);
                newOrder.setDepositDate(depositDate);
                newOrder.setPickUpStartTime(pickUpStartTime);
                newOrder.setPickUpEndTime(pickUpEndTime);
                newOrder.setDepositeStartTime(depositStartTime);
                newOrder.setDepositeEndTime(depositEndTime);
                Intent intentToParcelPicker = new Intent(this, ParcelTypePicker.class);
                intentToParcelPicker.putExtra("newOrder", gson.toJson(newOrder));
                startActivity(intentToParcelPicker);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initDateAndTime(){
        final Calendar calendar = Calendar.getInstance();
        initDate(calendar);
        initTime();
    }

    public void initDate(Calendar c){
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH) + 1;
        String text = day + "/" + month + "/" + year;
        pickUpDateText.setText(text);
        depositDateText.setText(text);
        pickUpDate = convertStringToDate(text);
        depositDate = convertStringToDate(text);
    }

    public void initTime(){
        int hour = 10;
        int minute = 00;
        String text = hour + "h" +((minute < 10)? "0" + minute : minute);
        pickUpStarTimeText.setText(text);
        pickUpStartTime = convertStringToTime(text);
        hour += 3;
        text = hour + "h" +((minute < 10)? "0" + minute : minute);
        pickUpEndTimeText.setText(text);
        depositEndTimeText.setText(text);
        pickUpEndTime = convertStringToTime(text);
        depositStartTime = convertStringToTime(text);
        hour += 2;
        text = hour + "h" +((minute < 10)? "0" + minute : minute);
        depositEndTimeText.setText(text);
        depositEndTime = convertStringToTime(text);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "TimePicker");
        clickedButtonId = v.getId();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
        clickedButtonId = v.getId();
    }

    public Date convertStringToDate(String text){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = Calendar.getInstance().getTime();
        try {
            date = sdf.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Time convertStringToTime(String text){
        SimpleDateFormat sdf = new SimpleDateFormat("HH'h'mm");
        Time time = new Time(Calendar.getInstance().getTimeInMillis());
        try{
            time.setTime(sdf.parse(text).getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return time;
    }
}
