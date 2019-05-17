package com.example.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText username,password;
    Button btnsavedta;
    ListviewSearch listviewSearch;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        listItem=new ArrayList<>();


        username=findViewById(R.id.edittextusername);
        password=findViewById(R.id.edittextpassword);
        userlist=findViewById(R.id.user_listview);
        btnsavedta=findViewById(R.id.bntregister);


        addData();

    }



    public  void addData()
    {
        btnsavedta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=username.getText().toString();
                String passWord=password.getText().toString();
                if (userName.equals("")&& passWord.equals(""))
                {
                    Toast.makeText(MainActivity.this, "empty fielad", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean isInserted= mydb.insertdata(userName,passWord);
                    if (isInserted==true)
                    {
                        Intent intent=new Intent(MainActivity.this,login.class);
                        startActivity(intent);
                        username.setText("");
                        password.setText("");
                        listItem.clear();
                    }
                    else {

                        Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}
