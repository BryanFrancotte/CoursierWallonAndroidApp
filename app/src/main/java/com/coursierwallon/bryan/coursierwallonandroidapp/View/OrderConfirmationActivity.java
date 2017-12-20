package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.ApiConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.OrderConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.OrderDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.OrderModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.google.android.gms.gcm.Task;
import com.google.gson.Gson;

import java.net.HttpURLConnection;

/**
 * Created by franc on 01-12-17.
 */

public class OrderConfirmationActivity extends AppCompatActivity {

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order);

        Toolbar myToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView parcelType = findViewById(R.id.confirmationOrder_layout_parcelType);
        TextView pickUpAddress = findViewById(R.id.confirmationOrder_layout_pickUpAddress);
        TextView destAddress = findViewById(R.id.confirmationOrder_layout_destAddress);
        TextView deliveryType = findViewById(R.id.confirmationOrder_layout_deliveryType);
        TextView estimatedPrice = findViewById(R.id.confirmationOrder_layout_estimatedPrice);

        Button confirm = findViewById(R.id.confirmationOrder_layout_confirm_button);
        Button cancel = findViewById(R.id.confirmationOrder_layout_cancel_button);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        token = preferences.getString(ApiConstant.TOKEN, null);

        Gson gson = new Gson();
        final Bundle bundle = getIntent().getExtras();
        final OrderModel newOrder = gson.fromJson(bundle.getString("newOrder"), OrderModel.class);

        parcelType.setText(getParcelType(newOrder.getParcel(0).getParcelType()));
        pickUpAddress.setText(newOrder.getPickUpAddressNavigation().toString());
        destAddress.setText(newOrder.getDepositAddressNavigation().toString());
        deliveryType.setText(getDeliveryType(newOrder.getDeliveryType()));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddOrder().execute(newOrder);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToHome = new Intent(OrderConfirmationActivity.this, HomeActivity.class);
                startActivity(intentToHome);
            }
        });
    }

    private String getParcelType(int type){
        String text;
        switch (type){
            case OrderConstant.TYPE_S:
                text = getString(R.string.OrderConfirmation_TypeS);
                return text;
            case OrderConstant.TYPE_M:
                text = getString(R.string.OrderConfirmation_TypeM);
                return text;
            case OrderConstant.TYPE_L:
                text = getString(R.string.OrderConfirmation_TypeL);
                return text;
            case OrderConstant.TYPE_XL:
                text = getString(R.string.OrderConfirmation_TypeXL);
                return text;
            default:
                text = getString(R.string.OrderConfirmation_NoSpec);
                return text;
        }
    }
    private String getDeliveryType(int type) {
        String text;
        switch (type) {
            case OrderConstant.DELIVERY_NORMAL:
                text = getString(R.string.OrderConfirmation_DelTypeNormal); // TODO: faire ça avec @string
                return text;
            case OrderConstant.DELIVERY_SEMI_EXPRESS:
                text = getString(R.string.OrderConfirmation_DelTypeSemiExpress); // TODO: faire ça avec @string
                return text;
            case OrderConstant.DELIVERY_EXPRESS:
                text = getString(R.string.OrderConfirmation_DelTypeExpress); // TODO: faire ça avec @string
                return text;
            default:
                text = getString(R.string.OrderConfirmation_DelTypeNoSpec); // TODO: faire ça avec @string
                return text;
        }
    }

    private class AddOrder extends AsyncTask<OrderModel, Void, Integer>{

        @Override
        protected Integer doInBackground(OrderModel... newOrders) {
            OrderDAO dao = new OrderDAO();
            Integer result = null;
            try{
                result = dao.addOrder(token, newOrders[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(result == HttpURLConnection.HTTP_OK){
                Toast.makeText(OrderConfirmationActivity.this, R.string.OrderConfirmation_Added, Toast.LENGTH_SHORT).show(); // TODO: faire ça avec @string
                Intent intentToHome = new Intent(OrderConfirmationActivity.this, HomeActivity.class);
                startActivity(intentToHome);
            }else{
                Toast.makeText(OrderConfirmationActivity.this, R.string.OrderConfirmation_Error, Toast.LENGTH_SHORT).show(); // TODO: faire ça avec @string
            }
        }
    }
}
