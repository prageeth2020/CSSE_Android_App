package com.example.csse_android_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Constructions5.db";
    private static final String TABLE_NAME = "Inquire";
    private static final String COL_1 = "ItemNo";
    private static final String COL_2 = "Category";
    private static final String COL_3 = "Notes";

    private static final String TABLE_NAME1 = "Purchase1";
    private static final String COL_p1 = "ItemNo";
    private static final String COL_p2 = "Description";
    private static final String COL_p3 = "Quantity";
    private static final String COL_p4 = "Status";
    private static final String COL_p5 = "Cost";
    private static final String COL_p6 = "ItemTotalCost";
    private static final String COL_p7 = "RequesitionNo";
    private static final String COL_p8 = "Supplier";

    private static final String TABLE_NAME3 = "Reply1";
    private static final String COL_r1 = "ItemDescription";
    private static final String COL_r2 = "Reply";

    private static final String TABLE_NAME4 = "Invoice";
    private static final String COL_i1 = "InvoiceNo";
    private static final String COL_i2 = "ReceiptNo";
    private static final String COL_i3 = "BillTo";
    private static final String COL_i4 = "ItemNo";

    private static final String TABLE_NAME5 = "DeliverDetails";
    private static final String COL_d1 = "DeliveryId";
    private static final String COL_d2 = "OrderId";
    private static final String COL_d3 = "DeliverMethod";
    private static final String COL_d4 = "Address";
    private static final String COL_d5 = "DeliveryCost";
    private static final String COL_d6 = "Supplier";
    private static final String COL_d7 = "SiteCode";
    private static final String COL_d8 = "Status";

    private static final String TABLE_NAME6 = "PurchaseOrder";
    private static final String COL_o1 = "OrderReferenceNo";
    private static final String COL_o2 = "DeliverBefore";
    private static final String COL_o3 = "SupplierCompany";
    private static final String COL_o4 = "ItemNo";

    private static final String TABLE_NAME7 = "Receipt";
    private static final String COL_re1 = "ReceiptNo";
    private static final String COL_re2 = "OrderReferenceNo";
    private static final String COL_re3 = "Supplier";
    private static final String COL_re4 = "DeliveryAddress";
    private static final String COL_re5 = "ItemNo";





    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" create table " + TABLE_NAME + " (ItemNo TEXT , Category TEXT ,Notes TEXT ) ");
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME1 + " (ItemNo TEXT , Description TEXT ,Quantity TEXT ,Status TEXT ,Cost TEXT ,ItemTotalCost TEXT ,RequesitionNo TEXT ,Supplier TEXT ) ");
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME3 + " (ItemDescription TEXT , Reply TEXT) ");
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME4 + " (InvoiceNo TEXT , ReceiptNo TEXT , BillTo TEXT , ItemNo TEXT) ");
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME5 + " (DeliveryId TEXT , OrderId TEXT , DeliverMethod TEXT , Address TEXT , DeliveryCost TEXT , Supplier TEXT , SiteCode TEXT , Status TEXT) ");
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME6 + " (OrderReferenceNo TEXT , DeliverBefore TEXT , SupplierCompany TEXT , ItemNo TEXT) ");
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME7 + " (ReceiptNo TEXT , OrderReferenceNo TEXT , Supplier TEXT , DeliveryAddress TEXT , ItemNo TEXT) ");

        sqLiteDatabase.execSQL("CREATE TABLE user(email text  primary key,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME6);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME7);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");

        onCreate(sqLiteDatabase);

    }

    //Add Enquiry Details
    public boolean insertDataInquire(String itemNo ,String category,String notes) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, itemNo);
        contentValues.put(COL_2, category);
        contentValues.put(COL_3, notes);


        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Add Purchase Details
    public boolean insertDataPurchase(String itemNo ,
                                      String description,
                                      String quantity ,
                                      String status ,
                                      String cost,
                                      String itemTotalCost,
                                      String requesitionNo,
                                      String supplier) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_p1, itemNo);
        contentValues.put(COL_p2, description);
        contentValues.put(COL_p3, quantity);
        contentValues.put(COL_p4, status);
        contentValues.put(COL_p5, cost);
        contentValues.put(COL_p6, itemTotalCost);
        contentValues.put(COL_p7, requesitionNo);
        contentValues.put(COL_p8, supplier);


        long result = sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    //Add Reply Details
    public boolean insertDataReply(String itemDescription ,String reply) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_r1, itemDescription);
        contentValues.put(COL_r2, reply);

        long result = sqLiteDatabase.insert(TABLE_NAME3, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Add Invoice Details (delivery process 5)
    public boolean insertDataInvoice(String invoiceNo ,String receiptNo ,String billTo ,String itemNo) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_i1, invoiceNo);
        contentValues.put(COL_i2, receiptNo);
        contentValues.put(COL_i3, billTo);
        contentValues.put(COL_i4, itemNo);

        long result = sqLiteDatabase.insert(TABLE_NAME4, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Add Deliver Details - Deliver Process 6
    public boolean insertDataDelivery(String deliveryId ,String orderId ,String deliveryMethod ,String address ,String deliverCost ,String supplier ,String siteCode ,String status) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_d1, deliveryId);;
        contentValues.put(COL_d2, orderId);;
        contentValues.put(COL_d3, deliveryMethod);;
        contentValues.put(COL_d4, address);;
        contentValues.put(COL_d5, deliverCost);;
        contentValues.put(COL_d6, supplier);;
        contentValues.put(COL_d7, siteCode);;
        contentValues.put(COL_d8, status);;

        long result = sqLiteDatabase.insert(TABLE_NAME5, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Add Purchase Order (Purchase Process 2)
    public boolean insertDataPurchaseOrder(String orderReferenceNo ,String deliverBefore ,String supplierCompany ,String itemNo) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_o1, orderReferenceNo);
        contentValues.put(COL_o2, deliverBefore);
        contentValues.put(COL_o3, supplierCompany);
        contentValues.put(COL_o4, itemNo);

        long result = sqLiteDatabase.insert(TABLE_NAME6, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Create Receipt (Delivery Process 4)
    public boolean insertDataCreateReceipt(String ReceiptNo ,String OrderReferenceNo ,String Supplier ,String DeliveryAddress ,String ItemNo) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_re1, ReceiptNo);
        contentValues.put(COL_re2, OrderReferenceNo);
        contentValues.put(COL_re3, Supplier);
        contentValues.put(COL_re4, DeliveryAddress);
        contentValues.put(COL_re5, ItemNo);

        long result = sqLiteDatabase.insert(TABLE_NAME7, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //get all item details
    public Cursor getAllData() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME1, null);
        return res;
    }

    //get all receipt details
    public Cursor getAllDataReceipt() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME7, null);
        return res;
    }

    //get all delivery details
    public Cursor getAllDataDeliveryDetails() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME5, null);
        return res;
    }

    //get all enquiry details
    public Cursor getAllDataEnquiryDetails() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //get all reply details
    public Cursor getAllDataReplyDetails() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME3, null);
        return res;
    }

    //get all Invoice details
    public Cursor getAllDataInvoiceDetails() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME4, null);
        return res;
    }

    //get all Order details
    public Cursor getAllDataOrderDetails() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME6, null);
        return res;
    }


    //user login
    public boolean insert(String email, String password) {

        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1)
            return false;
        else
            return true;


    }

    //checking email if exists;
    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;

    }

    //checking the email and password ;
    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? and password=?", new String[]{email, password});

        if (cursor.getCount() > 0) return true;
        else return false;


    }

}
