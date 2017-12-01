package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.OrderConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.OrderModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.ParcelModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.google.gson.Gson;

/**
 * Created by bryan on 27-11-17.
 */

public class ParcelTypePicker extends AppCompatActivity {
    private int parcelType = OrderConstant.TYPE_S;
    private int deliveryType = OrderConstant.DELIVERY_NORMAL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_type_picker);

        Toolbar myToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RadioButton buttonParcelType = findViewById(R.id.type_S);
        RadioButton buttonDeliveryType = findViewById(R.id.normal_delivery);
        buttonParcelType.setChecked(true);
        buttonDeliveryType.setChecked(true);
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
                newOrder.setDeliveryType(deliveryType);
                newOrder.addPacel(new ParcelModel(parcelType));
                Toast.makeText(this, gson.toJson(newOrder), Toast.LENGTH_LONG).show();
                Intent intentToConfirmationOrder = new Intent(this, OrderConfirmationActivity.class);
                intentToConfirmationOrder.putExtra("newOrder", gson.toJson(newOrder));
                //startActivity(intentToConfirmationOrder);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.type_S:
                if(checked) {
                    parcelType = OrderConstant.TYPE_S;
                }
                break;
            case R.id.type_M:
                if(checked) {
                    parcelType = OrderConstant.TYPE_M;
                }
                break;
            case R.id.type_L:
                if(checked) {
                    parcelType = OrderConstant.TYPE_L;
                }
                break;
            case  R.id.type_XL:
                if(checked) {
                    parcelType = OrderConstant.TYPE_XL;
                }
                break;
            case R.id.normal_delivery:
                if(checked){
                    deliveryType = OrderConstant.DELIVERY_NORMAL;
                }
                break;
            case R.id.semi_express_delivery:
                if (checked){
                    deliveryType = OrderConstant.DELIVERY_SEMI_EXPRESS;
                }
                break;
            case R.id.express_delivery:
                if(checked){
                    deliveryType = OrderConstant.DELIVERY_EXPRESS;
                }
                break;
        }
    }
}
