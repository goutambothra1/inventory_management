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
                    db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,branch_name TEXT,dept_name TEXT, email TEXT, password TEXT, cpassword TEXT);");
                    db.execSQL("CREATE TABLE IF NOT EXISTS admin (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT);");
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
