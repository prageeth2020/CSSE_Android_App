
package com.example.csse_android_app;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class EnquiryHandlingProcess1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DataBaseHelper myDb;

    String[] category={"Heavy Timber","Metal","Plastic"};


    EditText itemNo,  notes;
    Button submit,viewInquire,update,delete;
    String n, c, h, d ,r;
    Animation fromtopbottom;
    Spinner spin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_handling_process1);
        myDb = new DataBaseHelper(this);

        itemNo = (EditText) findViewById(R.id.editTextTextPersonName2);

        spin = (Spinner) findViewById(R.id.spinner4);
        //   String text=spin.getSelectedItem().toString();
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        notes = (EditText) findViewById(R.id.editTextTextPersonName2);

        submit = (Button) findViewById(R.id.button6);
        viewInquire = (Button) findViewById(R.id.button7);

        AddData();

        viewAllEnquiryDetails();
    }


    public void AddData(){

        submit.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.button6) {

                            n = itemNo.getText().toString().trim();
                            c = spin.getSelectedItem().toString().trim();
                            h = notes.getText().toString().trim();

                            if (n.equals("") || c.equals("") || h.equals("")) {

                                Toast.makeText(EnquiryHandlingProcess1.this, "Data field can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {
                                boolean inserted = myDb.insertDataInquire(itemNo.getText().toString(), spin.getSelectedItem().toString(),notes.getText().toString());

                                if(inserted == true) {
                                    Toast.makeText(EnquiryHandlingProcess1.this, "Data inserted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(EnquiryHandlingProcess1.this,EnquiryHandlingProcess1.class);
                                    startActivity(intent);
                                }
                                else {

                                    Toast.makeText(EnquiryHandlingProcess1.this, "Data not inserted", Toast.LENGTH_LONG).show();


                                }
                            }
                        }

                    }
                }
        );

    }

    public void  viewAllEnquiryDetails(){

        viewInquire.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = myDb.getAllDataEnquiryDetails();

                        if(res.getCount() == 0){

                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer =new StringBuffer();

                        while(res.moveToNext()){
                            buffer.append("ItemNo :"+res.getString(0)+"\n");
                            buffer.append("Category :"+res.getString(1)+"\n");
                            buffer.append("Notes :"+res.getString(2)+"\n");
                        }
                        showMessage("Enquiry Details",buffer.toString());

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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}