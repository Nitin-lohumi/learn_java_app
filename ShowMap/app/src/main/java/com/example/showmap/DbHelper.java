package com.example.showmap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="DB";
    private static final String TABLE_NAME ="login_db";
    private static final int DATABASE_VERSION =1;
    private static final String USERNAME ="name";
    private static final String PASSWORD ="password";

    private static  final  String TABLE_CREATE ="CREATE TABLE "
            +TABLE_NAME +" ("+ USERNAME +"TEXT NOT NULL UNIQUE ,"+
                               PASSWORD +"TEXT NOT NULL); ";
    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
     onCreate(db);
    }
}
