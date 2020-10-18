package com.example.csse_android_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnquiryHandlingProcess3 extends AppCompatActivity {
    DataBaseHelper myDb;

    EditText itemDescription,  reply;
    Button submit,viewReply,view1;
    String n, c, h, d ,r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_handling_process3);

        myDb = new DataBaseHelper(this);

        itemDescription = (EditText) findViewById(R.id.editTextTextPersonName11);
        reply = (EditText) findViewById(R.id.editTextTextMultiLine4);

        submit = (Button) findViewById(R.id.button4);

        view1 = (Button) findViewById(R.id.button21);
        viewReply = (Button) findViewById(R.id.button22);


        AddReply();

        viewAllEnquiryDetails();
        viewAllReplyDetails();

    }

    public void AddReply() {

        submit.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.button4) {

                            n = itemDescription.getText().toString().trim();
                            h = reply.getText().toString().trim();

                            if (n.equals("") || h.equals("")) {

                                Toast.makeText(EnquiryHandlingProcess3.this, "Data field can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {
                                boolean inserted = myDb.insertDataReply(itemDescription.getText().toString(), reply.getText().toString());

                                if (inserted == true) {
                                    Toast.makeText(EnquiryHandlingProcess3.this, "Data inserted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(EnquiryHandlingProcess3.this, EnquiryHandlingProcess3.class);
                                    startActivity(intent);
                                } else {

                                    Toast.makeText(EnquiryHandlingProcess3.this, "Data not inserted", Toast.LENGTH_LONG).show();


                                }
                            }
                        }

                    }
                }
        );
    }


    public void  viewAllEnquiryDetails(){

        view1.setOnClickListener(

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


    public void  viewAllReplyDetails(){

        viewReply.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = myDb.getAllDataReplyDetails();

                        if(res.getCount() == 0){

                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer =new StringBuffer();

                        while(res.moveToNext()){
                            buffer.append("ItemDescription :"+res.getString(0)+"\n");
                            buffer.append("Reply :"+res.getString(1)+"\n");
                        }
                        showMessage("Reply Details",buffer.toString());

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


}