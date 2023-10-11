package com.example.inventory_management;

import android.content.Intent;
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
        dbhelper=new DBHelper(this);
        backsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back);
            }
        });
        SignUpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String branch,dept,emails,passwords,confirm_passwords;
                branch=branch_name.getText().toString();
                dept=dept_name.getText().toString();
                emails=email.getText().toString();
                passwords=password.getText().toString();
                confirm_passwords=confirm_password.getText().toString();

                if(branch.equals("")||dept.equals("")||emails.equals("")||passwords.equals("")||confirm_passwords.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"input is required",Toast.LENGTH_SHORT).show();
                }
                else {
                   if(passwords.equals(confirm_passwords))
                   {
                       if(dbhelper.checkusers(branch))
                       {
                           Toast.makeText(getApplicationContext(),"Branch Already Exist",Toast.LENGTH_SHORT).show();
                            return;

                       }
                       //input data
                       boolean result_insert=dbhelper.insertdata(branch, dept,emails, passwords,confirm_passwords);
                       if(result_insert==false)
                       {
                           Toast.makeText(getApplicationContext(),"error while Insert data",Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Toast.makeText(getApplicationContext(),"data Insert",Toast.LENGTH_SHORT).show();
                       }
                   }
                   else {
                       Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
                   }

                }
            }
        });


    }
}
