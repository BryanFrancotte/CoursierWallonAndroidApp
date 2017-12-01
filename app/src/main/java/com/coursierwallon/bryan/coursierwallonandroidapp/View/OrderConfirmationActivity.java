package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.OrderConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.OrderModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.google.gson.Gson;

/**
 * Created by franc on 01-12-17.
 */

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order);

        TextView parcelType = findViewById(R.id.confirmationOrder_layout_parcelType);
        TextView pickUpAddress = findViewById(R.id.confirmationOrder_layout_pickUpAddress);
        TextView destAddress = findViewById(R.id.confirmationOrder_layout_destAddress);
        TextView deliveryType = findViewById(R.id.confirmationOrder_layout_deliveryType);
        TextView estimatedPrice = findViewById(R.id.confirmationOrder_layout_estimatedPrice);

        Gson gson = new Gson();
        Bundle bundle = getIntent().getExtras();
        OrderModel newOrder = gson.fromJson(bundle.getString("newOrder"), OrderModel.class);

        parcelType.setText(getParcelType(newOrder.getParcel(0).getParcelType()));
        pickUpAddress.setText(newOrder.getPickUpAddressNavigation().toString());
        destAddress.setText(newOrder.getDepositAddressNavigation().toString());
        deliveryType.setText(getDeliveryType(newOrder.getDeliveryType()));
    }

    private String getParcelType(int type){
        String text;
        switch (type){
            case OrderConstant.TYPE_S:
                text = "parcel type S"; // TODO: faire ça avec @string
                return text;
            case OrderConstant.TYPE_M:
                text = "parcel type M"; // TODO: faire ça avec @string
                return text;
            case OrderConstant.TYPE_L:
                text = "parcel type L"; // TODO: faire ça avec @string
                return text;
            case OrderConstant.TYPE_XL:
                text = "parcel type XL"; // TODO: faire ça avec @string
                return text;
            default:
                text = "None specified";
                return text;
        }
    }
    private String getDeliveryType(int type) {
        String text;
        switch (type) {
            case OrderConstant.DELIVERY_NORMAL:
                text = "delivery type Normal"; // TODO: faire ça avec @string
                return text;
            case OrderConstant.DELIVERY_SEMI_EXPRESS:
                text = "delivery type Semi Express"; // TODO: faire ça avec @string
                return text;
            case OrderConstant.DELIVERY_EXPRESS:
                text = "delivery type Express"; // TODO: faire ça avec @string
                return text;
            default:
                text = "None specified";
                return text;
        }
    }
}
