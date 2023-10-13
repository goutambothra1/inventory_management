package com.example.inventory_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText InputEmail,InputPassword;
    Button LoginButton, Admin_button;
    TextView Sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sign_up=findViewById(R.id.signup);
        Admin_button=findViewById(R.id.adminbtn);
        InputEmail=findViewById(R.id.InputEmail);
        InputPassword=findViewById(R.id.InputPassword);
        LoginButton=findViewById(R.id.loginButton);

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup=new Intent(getApplicationContext(),sign_up_page.class);
                startActivity(signup);
            }
        });
        Admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminintent=new Intent(getApplicationContext(),admin_login.class);
                startActivity(adminintent);
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = InputEmail.getText().toString();
                String password = InputPassword.getText().toString();

                // Open the database for reading
                SQLiteDatabase db = openOrCreateDatabase("RegisterDB", Context.MODE_PRIVATE, null);

                // Query the database to check if the provided email and password exist in the users table
                Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", new String[]{email, password});

                if (cursor.moveToFirst()) {
                    // If cursor is not empty, it means there is a matching record
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), home_page.class);
                    startActivity(intent);
                } else {
                    // No matching record found
                    Toast.makeText(MainActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                }

                // Close the cursor and the database
                cursor.close();
                db.close();
            }
        });


    }

}