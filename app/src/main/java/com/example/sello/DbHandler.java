package com.example.sello;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context, "sello1.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table user(name text,uid text,gender text,contact number, email text, password text)");
        db.execSQL("Create table product(name text,productname text,newprice text,oldprice text,discription text,image BLOB )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists rentee");
    }
    public boolean insert(String name,String Uid,String Gender,String Contact, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("uid",Uid);
        contentValues.put("gender",Gender);
        contentValues.put("contact",Contact);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean emailpass(String email, String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email = ? and password = ? ", new String[]{email,pass});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?",new String[]{email});

        if(cursor.getCount() > 0){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean insert(String productname,String newprice,String oldprice,String discription, byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productname",productname);
        contentValues.put("newprice",newprice);
        contentValues.put("oldprice",oldprice);
        contentValues.put("discription",discription);
        contentValues.put("image",image);

        long ins = db.insert("product",null,contentValues);
        if(ins == -1){
            return false;
        }
        else {
            return true;
        }
    }


   /* public ArrayList<sello_model> getDetails()
    {
        ArrayList<sello_model> sello_model_List = new ArrayList<sello_model>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from product",null);
        if(cursor.moveToFirst())
        {
            do
            {
                String productname = cursor.getString(0);
                String newprice = cursor.getString(1);
                String oldprice = cursor.getString(2);
                String description = cursor.getString(3);
                byte[] image = cursor.getBlob(4);

                //sello_model sello_model = new sello_model(productname,newprice,oldprice,description,image);
                sello_model sello_model = new sello_model(productname,newprice,oldprice,description,image);
                sello_model_List.add((sello_model));
            }
            while (cursor.moveToNext());
        }
        return sello_model_List;
    }*/

   public ArrayList<sello_model> getDetails()
    {
        ArrayList<sello_model> sello_model_List = new ArrayList<sello_model>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from product",null);
        //ArrayList<String> str=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do
            {
                String productname = cursor.getString(0);
                String newprice = cursor.getString(1);
                String oldprice = cursor.getString(2);
                String description = cursor.getString(3);
                byte[] image = cursor.getBlob(4);

                Log.d("Data",image.toString());

                //sello_model sello_model = new sello_model(productname,newprice,oldprice,description,image);
                sello_model sello_model = new sello_model(productname,newprice,oldprice,description,image);
               // sello_model.getProduct_name(productname);
                //str.add(productname);
                sello_model_List.add((sello_model));
            }
            while (cursor.moveToNext());
        }
        return sello_model_List;
    }
   /* public ArrayList<String > getDetails()
    {
        ArrayList<sello_model> sello_model_List = new ArrayList<sello_model>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from product",null);
        ArrayList<String> str=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do
            {
                String productname = cursor.getString(0);
                String newprice = cursor.getString(1);
                String oldprice = cursor.getString(2);
                String description = cursor.getString(3);
                byte[] image = cursor.getBlob(4);

                //sello_model sello_model = new sello_model(productname,newprice,oldprice,description,image);
                sello_model sello_model = new sello_model(productname,newprice,oldprice,description,image);
                // sello_model.getProduct_name(productname);
                str.add(productname);
                sello_model_List.add((sello_model));
            }
            while (cursor.moveToNext());
        }
        return str;
    }*/
}
