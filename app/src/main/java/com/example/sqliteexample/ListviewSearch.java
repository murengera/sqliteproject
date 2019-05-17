package com.example.sqliteexample;

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

public class ListviewSearch extends AppCompatActivity {

    DatabaseHelper mydb;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_search);
        mydb=new DatabaseHelper(this);


        userlist=findViewById(R.id.user_listview);

        listItem=new ArrayList<>();
        
        viewdata();


    }

    private void viewdata() {

        Cursor cursor=mydb.viewdata();
        if (cursor.getCount()==0)
        {

            Toast.makeText(this, "no data to show", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {

                listItem.add(cursor.getString(1));
            }
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            userlist.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchitem=menu.findItem(R.id.itemsearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchitem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<String> userslist=new ArrayList<>();
                for (String user:listItem)
                {

                    if (user.toLowerCase().contains(newText.toLowerCase()))
                    {
                        userslist.add(user);
                    }

                }
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(ListviewSearch.this,android.R.layout.simple_list_item_1,userslist);
                userlist.setAdapter(adapter);

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
