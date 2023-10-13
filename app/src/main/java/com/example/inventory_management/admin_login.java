package com.example.inventory_management;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity {
    Button buttonLogin;
    EditText username,password;
    TextView backtbtn;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        buttonLogin=findViewById(R.id.admin_login_button);
        backtbtn=findViewById(R.id.back);
        username=findViewById(R.id.admin_id_text);
        password=findViewById(R.id.admin_password_text);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                try {
                    SQLiteDatabase db = openOrCreateDatabase("RegisterDB", Context.MODE_PRIVATE, null);


                    Cursor cursor = db.rawQuery("SELECT * FROM admin WHERE username=? AND password=?", new String[]{user, pass});
                    if (cursor.moveToFirst()) {
                        Toast.makeText(admin_login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        // Add your code to navigate to the next activity here
                    } else {
                        Toast.makeText(admin_login.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    db.close();
                } catch (Exception e) {
                    Toast.makeText(admin_login.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
