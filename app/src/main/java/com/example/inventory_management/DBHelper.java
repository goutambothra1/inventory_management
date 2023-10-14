package com.example.inventory_management;

import static android.app.ProgressDialog.show;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public abstract class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="RegisterDB";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table  if exists users");
    }
    public boolean insertdata(String Branch, String Dept, String Email, String pass, String cpass) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        // Check if the email already exists in the database
        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE email=?", new String[]{Email});
        if (cursor.getCount() > 0) {
            // Email already exists, show a toast message
            System.out.println("Email already exists");
           // Toast.makeText(DBHelper.this, "Email is already registered", Toast.LENGTH_SHORT).show();
            cursor.close();
            return false;
        }
        cursor.close();

        ContentValues contentValues = new ContentValues();
        contentValues.put("branch_name", Branch);
        contentValues.put("dept_name", Dept);
        contentValues.put("email", Email);
        contentValues.put("password", pass);
        contentValues.put("cpassword", cpass);

        long res = myDB.insert("users", null, contentValues);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean checkusers(String Branch){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor curson=myDB.rawQuery("SELECT * from users where branch_name=?",new String[]{Branch});
        if(curson.getCount()>0){
            return true;

        }
        else {
            return false;
        }

    }
}
