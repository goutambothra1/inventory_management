package com.example.inventory_management;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;

public class activity_splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_splash);
        // Create a thread to wait for some time and then start the main activity
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // Open or create the database
                    SQLiteDatabase db = openOrCreateDatabase("RegisterDB", Context.MODE_PRIVATE, null);

                    // Create the 'users' table if it doesn't exist
                    db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,branch_name TEXT,dept_name TEXT, email TEXT,phone_number TEXT UNIQUE, password TEXT, cpassword TEXT);");
                    db.execSQL("CREATE TABLE IF NOT EXISTS admin (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT);");
                    db.execSQL("CREATE TABLE IF NOT EXISTS admin_stock  ( STOCK_ID INTEGER PRIMARY KEY AUTOINCREMENT, admin_id INTEGER, item_name TEXT, Quantity  INTEGER, FOREIGN KEY (admin_id) REFERENCES admin(id));"); // Assuming Admin table has admin_id as primary key")
                    db.execSQL("CREATE TABLE IF NOT EXISTS admin_history (admin_history_id INTEGER PRIMARY KEY AUTOINCREMENT,stock_id INTEGER ,item_id TEXT, item_name TEXT, department TEXT, quantity INTEGER,date TEXT,FOREIGN KEY (stock_id) REFERENCES admin_stock(stock_id));");
                    // Check if the admin already exists
                    Cursor cursor = db.rawQuery("SELECT * FROM admin", null);

                    if (!cursor.moveToFirst()) {
                        // If admin doesn't exist, insert it
                        db.execSQL("INSERT INTO admin (username, password) VALUES ('admin', 'mahe@123');");
                    }

                    // Close the database
                    db.close();
                    sleep(2000); // Wait for 2 seconds (you can adjust this)
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
