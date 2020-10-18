package com.example.csse_android_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DeliveryProcess6 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DataBaseHelper myDb;

    String[] status={"Pending","Approve"};

    EditText deliveryId,  orderId , deliverMethod, address, deliverCost, supplier, siteCode;
    Button submit,view1;
    String a, b, c, d ,e, f,g,h,i,j;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_process6);

        myDb = new DataBaseHelper(this);

        deliveryId = (EditText) findViewById(R.id.editTextTextPersonName2);
        orderId = (EditText) findViewById(R.id.editTextTextPersonName3);
        deliverMethod = (EditText) findViewById(R.id.editTextTextPersonName4);
        address = (EditText) findViewById(R.id.editTextTextPersonName5);
        deliverCost = (EditText) findViewById(R.id.editTextTextPersonName6);
        supplier = (EditText) findViewById(R.id.editTextTextPersonName7);
        siteCode = (EditText) findViewById(R.id.editTextTextPersonName8);

        spin = (Spinner) findViewById(R.id.spinner4);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        submit = (Button) findViewById(R.id.button4);
        view1 = (Button) findViewById(R.id.button20);


        AddDelivery();
        viewAllDeliveryDetails();
    }

    public void AddDelivery(){

        submit.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.button4) {

                            a = deliveryId.getText().toString().trim();
                            b = orderId.getText().toString().trim();
                            c = deliverMethod.getText().toString().trim();
                            d = address.getText().toString().trim();
                            e = deliverCost.getText().toString().trim();
                            f = supplier.getText().toString().trim();
                            g = siteCode.getText().toString().trim();

                            h = spin.getSelectedItem().toString().trim();

                            if (a.equals("") || b.equals("") || c.equals("") || d.equals("") || e.equals("") || f.equals("") || g.equals("")) {

                                Toast.makeText(DeliveryProcess6.this, "Data field can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {

                                boolean inserted = myDb.insertDataDelivery(deliveryId.getText().toString(),
                                        orderId.getText().toString(),
                                        deliverMethod.getText().toString(),
                                        spin.getSelectedItem().toString(),
                                        address.getText().toString(),
                                        deliverCost.getText().toString(),
                                        supplier.getText().toString(),
                                        siteCode.getText().toString());

                                if(inserted == true) {
                                    Toast.makeText(DeliveryProcess6.this, "Data inserted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(DeliveryProcess6.this,DeliveryProcess6.class);
                                    startActivity(intent);
                                }
                                else {

                                    Toast.makeText(DeliveryProcess6.this, "Data not inserted", Toast.LENGTH_LONG).show();

                                }
                            }
                        }

                    }
                }
        );





    }



    public void  viewAllDeliveryDetails(){

        view1.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = myDb.getAllDataDeliveryDetails();

                        if(res.getCount() == 0){

                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer =new StringBuffer();

                        while(res.moveToNext()){

                            buffer.append("OrderId :"+res.getString(0)+"\n");
                            buffer.append("DeliverMethod :"+res.getString(1)+"\n");
                            buffer.append("Address :"+res.getString(2)+"\n");
                            buffer.append("DeliveryCost :"+res.getString(3)+"\n");
                            buffer.append("Supplier :"+res.getString(4)+"\n");
                            buffer.append("SiteCode :"+res.getString(5)+"\n");
                            buffer.append("Status :"+res.getString(6)+"\n");


                        }
                        showMessage("Delivery Details",buffer.toString());

                    }
                }
        );
    }

    public  void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}