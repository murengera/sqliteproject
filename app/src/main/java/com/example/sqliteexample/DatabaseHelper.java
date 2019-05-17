package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final  String DATABASE_NAME="students.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="student";
    public  static final String  COL_1="ID";
    public  static final String  COL_2="NAME";
    public  static final String  COL_3="PASSWORD";


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //create meathod to view data


    public  Cursor  viewdata()
    {
        SQLiteDatabase db=this.getReadableDatabase();
                String query="select*from "+TABLE_NAME;
                Cursor cursor=db.rawQuery(query,null);
                return cursor;
    }





    public  boolean insertdata(String name,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,password);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }








    public Cursor getalldata( String username,String secret)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select *from "+TABLE_NAME+ " where " +COL_2 +"=? and "+COL_3 + "=?",new String[]{username,secret});
        return res;

    }
    public  Cursor  Viewdata()
    {

        SQLiteDatabase db=this.getReadableDatabase();
        String query="select*from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
}
