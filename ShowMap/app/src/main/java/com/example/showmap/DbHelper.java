package com.example.showmap;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="DB";
    private static final String TABLE_NAME ="login_db";
    private static final int DATABASE_VERSION =1;
    private static final String USERNAME ="name";
    private static final String PASSWORD ="password";
    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE ="CREATE TABLE "
                +TABLE_NAME +" ("+ USERNAME +" TEXT NOT NULL UNIQUE ,"+
                                      PASSWORD +" TEXT NOT NULL); ";
     db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
     onCreate(db);
    }
    public boolean insert(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content  = new ContentValues();
        content.put(USERNAME,username);
        content.put(PASSWORD,password);
        long res = db.insert(TABLE_NAME,null,content);
        db.close();
        return res!=-1;
    }
    public boolean delete(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        int res =db.delete(TABLE_NAME,"name=?",new String[]{String.valueOf(name)});
        db.close();
        return res!=0;
    }
    public boolean update(String name,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content  = new ContentValues();
        content.put("password",password);
        int res = db.update(TABLE_NAME,content,"name=?",new String[]{String.valueOf(name)});
        db.close();
        return res!=0;
    }
    public Cursor FetchData(){
         SQLiteDatabase db = this.getReadableDatabase();
         return db.rawQuery(" SELECT * FROM login_db",null);
    }
}
