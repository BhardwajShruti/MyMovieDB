package com.example.android.mymoviedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shruti on 04-11-2017.
 */

public class db extends SQLiteOpenHelper {
    private static db instance;


    public static db getInstance(Context context) {
        if(instance == null){
            instance = new db(context);
        }
        return instance;
    }

    private db(Context context) {
        super(context, "expense_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + Contract.FAV_TABLE + " ( " +
                Contract.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.MOVIE_TITLE + " TEXT, " +
                Contract.MOVIE_URL + " TEXT, " +

                Contract.MOVIE_ID + " INTEGER  )";


        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
