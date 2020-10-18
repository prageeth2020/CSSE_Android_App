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

public class PurchasingProcess1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DataBaseHelper myDb;

    String[] status={"Pending","Approve"};


    EditText itemNo,  description , quantity, cost, itemTotalCost, requesitionNo, supplier;
    Button submit,view,next,textView7,textView6,textView8,textView9,view1,update,delete;
    String a, b, c, d ,e, f,g,h,i,j;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasing_process1);

        myDb = new DataBaseHelper(this);

        itemNo = (EditText) findViewById(R.id.editTextTextPersonName);
        description = (EditText) findViewById(R.id.editTextTextMultiLine);
        quantity = (EditText) findViewById(R.id.editTextTextPersonName10);
        cost = (EditText) findViewById(R.id.editTextTextPersonName3);
        itemTotalCost = (EditText) findViewById(R.id.editTextTextPersonName4);
        requesitionNo = (EditText) findViewById(R.id.editTextTextPersonName5);
        supplier = (EditText) findViewById(R.id.editTextTextPersonName6);

        spin = (Spinner) findViewById(R.id.spinner2);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        submit = (Button) findViewById(R.id.button2);
        view = (Button) findViewById(R.id.button23);

        AddPurchase();
        ViewAllPurchases();
    }


    public void AddPurchase(){

        submit.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.button2) {

                            a = itemNo.getText().toString().trim();
                            b = description.getText().toString().trim();
                            c = quantity.getText().toString().trim();
                            d = cost.getText().toString().trim();
                            e = itemTotalCost.getText().toString().trim();
                            f = requesitionNo.getText().toString().trim();
                            g = supplier.getText().toString().trim();

                            h = spin.getSelectedItem().toString().trim();

                            if (a.equals("") || b.equals("") || c.equals("") || d.equals("") || e.equals("") || f.equals("") || g.equals("")) {

                                Toast.makeText(PurchasingProcess1.this, "Data field can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {

                                boolean inserted = myDb.insertDataPurchase(itemNo.getText().toString(),
                                                                           description.getText().toString(),
                                                                           quantity.getText().toString(),
                                                                           spin.getSelectedItem().toString(),
                                                                           cost.getText().toString(),
                                                                           itemTotalCost.getText().toString(),
                                                                           requesitionNo.getText().toString(),
                                                                           supplier.getText().toString());

                                if(inserted == true) {
                                    Toast.makeText(PurchasingProcess1.this, "Data inserted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(PurchasingProcess1.this,PurchasingProcess1.class);
                                    startActivity(intent);
                                }
                                else {

                                    Toast.makeText(PurchasingProcess1.this, "Data not inserted", Toast.LENGTH_LONG).show();

                                }
                            }
                        }

                    }
                }
        );





    }



    public void  ViewAllPurchases(){

        view.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = myDb.getAllData();

                        if(res.getCount() == 0){

                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer =new StringBuffer();

                        while(res.moveToNext()){
                            buffer.append("ItemNo :"+res.getString(0)+"\n");
                            buffer.append("Description :"+res.getString(1)+"\n");
                            buffer.append("Quantity :"+res.getString(2)+"\n");
                            buffer.append("Status :"+res.getString(3)+"\n");
                            buffer.append("Cost :"+res.getString(4)+"\n");
                            buffer.append("ItemTotalCost :"+res.getString(5)+"\n");
                            buffer.append("RequesitionNo :"+res.getString(6)+"\n");
                            buffer.append("Supplier :"+res.getString(7)+"\n");
                        }
                        showMessage("Item Details",buffer.toString());

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