package com.example.inventory_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="RegisterDB";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY, branch_name TEXT,dept_name TEXT,email TEXT,password TEXT,cpassword TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table  if exists users");
    }
    public boolean insertdata(String Branch,String Dept, String Email, String pass, String cpass)
    {
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("branch_name",Branch);
        contentValues.put("dept_name",Dept);
        contentValues.put("email",Email);
        contentValues.put("password",pass);
        contentValues.put("cpassword",cpass);
        long res=myDB.insert("users",null,contentValues);
        if(res==-1){
            return false;

        }
        else {
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
