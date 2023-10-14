package com.example.inventory_management;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class sign_up_page extends AppCompatActivity {
    EditText branch_name, dept_name, email,password,confirm_password;
    Button SignUpButton;
    DBHelper dbhelper;
    TextView backsignup;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        backsignup=findViewById(R.id.backbutton);
        branch_name=findViewById(R.id.branch_name);
        dept_name=findViewById(R.id.dept_name);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.first_password);
        confirm_password   =    findViewById(R.id.cnfrm_password);
        SignUpButton=(Button) findViewById(R.id.signup_btn);
        dbhelper= new DBHelper(this) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

            }
        };
        backsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back);
            }
        });
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String branch, dept, emails, passwords, confirm_passwords;
                branch = branch_name.getText().toString();
                dept = dept_name.getText().toString();
                emails = email.getText().toString();
                passwords = password.getText().toString();
                confirm_passwords = confirm_password.getText().toString();

                // Email validation regex
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                // Password validation regex
                String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

                if (branch.equals("") || dept.equals("") || emails.equals("") || passwords.equals("") || confirm_passwords.equals("")) {
                    Toast.makeText(getApplicationContext(), "Input is required", Toast.LENGTH_SHORT).show();
                } else {
                    if (!emails.matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!passwords.matches(passwordPattern)) {
                        Toast.makeText(getApplicationContext(), "Invalid password format", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!passwords.equals(confirm_passwords)) {
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (dbhelper.checkusers(branch)) {
                        Toast.makeText(getApplicationContext(), "Branch Already Exists", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Input data
                    boolean result_insert = dbhelper.insertdata(branch, dept, emails, passwords, confirm_passwords);
                    if (!result_insert) {
                        Toast.makeText(getApplicationContext(), "Error while inserting data", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
