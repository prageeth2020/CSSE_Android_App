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

public class PurchaseProcess2 extends AppCompatActivity {
    DataBaseHelper myDb;

    EditText orderReferenceNo,  deliverBefore , supplierCompany, itemNo;

    Button submit,viewInquire,next,textView7,textView6,textView8,view2,view1,update,delete;

    String a, b, c, d ,e, f,g,h,i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_process2);

        myDb = new DataBaseHelper(this);

        orderReferenceNo = (EditText) findViewById(R.id.editTextTextPersonName2);
        deliverBefore = (EditText) findViewById(R.id.editTextTextPersonName3);
        supplierCompany = (EditText) findViewById(R.id.editTextTextPersonName4);
        itemNo = (EditText) findViewById(R.id.editTextTextPersonName5);

        submit = (Button) findViewById(R.id.button5);

        view1 = (Button) findViewById(R.id.button15);
        view2 = (Button) findViewById(R.id.button28);

        AddOrder();

        viewAll();

        viewOrder();
    }

    public void AddOrder() {

        submit.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.button5) {

                            a = orderReferenceNo.getText().toString().trim();
                            b = deliverBefore.getText().toString().trim();
                            c = supplierCompany.getText().toString().trim();
                            d = itemNo.getText().toString().trim();

                            if (a.equals("") || b.equals("") || c.equals("") || d.equals("")) {

                                Toast.makeText(PurchaseProcess2.this, "Data field can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {
                                boolean inserted = myDb.insertDataPurchaseOrder(orderReferenceNo.getText().toString(),
                                        deliverBefore.getText().toString(),
                                        supplierCompany.getText().toString(),
                                        itemNo.getText().toString()
                                );

                                if (inserted == true) {
                                    Toast.makeText(PurchaseProcess2.this, "Data inserted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(PurchaseProcess2.this, PurchaseProcess2.class);
                                    startActivity(intent);
                                } else {

                                    Toast.makeText(PurchaseProcess2.this, "Data not inserted", Toast.LENGTH_LONG).show();


                                }
                            }
                        }

                    }
                }
        );
    }


    public void  viewAll(){

        view1.setOnClickListener(

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
                        showMessage("Purchase Details",buffer.toString());

                    }
                }
        );
    }


    public void  viewOrder(){

        view2.setOnClickListener(

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
                            buffer.append("OrderReferenceNo :"+res.getString(0)+"\n");
                            buffer.append("DeliverBefore :"+res.getString(1)+"\n");
                            buffer.append("SupplierCompany :"+res.getString(2)+"\n");
                            buffer.append("ItemNo :"+res.getString(3)+"\n");


                        }
                        showMessage("Order Details",buffer.toString());

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