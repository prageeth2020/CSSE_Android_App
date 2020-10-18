package com.example.csse_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardSupplier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_supplier);
    }

    public void createReceipt(View v){
        Intent i = new Intent(this,DeliveryProcess4.class);
        startActivity(i);
    }

    public void saveDeliverDetails(View v){
        Intent i = new Intent(this,DeliveryProcess6.class);
        startActivity(i);
    }

    public void saveReply(View v){
        Intent i = new Intent(this,EnquiryHandlingProcess3.class);
        startActivity(i);
    }

    public void saveOrder(View v){
        Intent i = new Intent(this,PurchaseProcess2.class);
        startActivity(i);
    }
}