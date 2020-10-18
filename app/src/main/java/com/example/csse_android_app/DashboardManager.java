package com.example.csse_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_manager);
    }

    public void saveInvoice(View v){
        Intent i = new Intent(this,DeliveryProcess5.class);
        startActivity(i);
    }

    public void saveRequest(View v){
        Intent i = new Intent(this,PurchasingProcess1.class);
        startActivity(i);
    }

    public void saveEnquiry(View v){
        Intent i = new Intent(this,EnquiryHandlingProcess1.class);
        startActivity(i);
    }
}