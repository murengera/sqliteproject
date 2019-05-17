package com.example.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    DatabaseHelper mydb;
    Button btnlogin,registerbtn;
    EditText  username,loginpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydb=new DatabaseHelper(this);



        username=findViewById(R.id.username);

        loginpassword=findViewById(R.id.pass);
        btnlogin=findViewById(R.id.signin);


        registerbtn=findViewById(R.id.register);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,MainActivity.class));
            }
        });
        login();
    }



    public void  login()

    {

      btnlogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String surename=username.getText().toString();
              String passWords=loginpassword.getText().toString();
               Cursor cursor=mydb.getalldata(surename,passWords);
               if (cursor!=null)
               {
                   if (cursor.getCount()>0)
                   {
                       cursor.moveToNext();
                  Intent intent=new Intent(login.this,MainActivity.class);
                  startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
                   }

               }
          }
      });
    }
}
