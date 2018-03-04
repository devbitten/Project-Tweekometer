package com.example.user.project_tweekometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by User on 3/3/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "SqlStuff.db";
    public static final String USER_TABLE_NAME = "User Table";
    public static final String COL_00 = "Name";
    public static final String COL_01 = "Age";
    public static final String COL_02 = "Weight";
    public static final String COL_03 = "Sensitivity";
    public static final String CONSUMPTION_TABLE_NAME = "Consumption Table";
    public static final String COL_10 = "UnixTime";
    public static final String COL_11 = "Caffeine";
    public static final String PRODUCT_TABLE_NAME = "Product Table";
    public static final String COL_20 = "ID";
    public static final String COL_21 = "Category";
    public static final String COL_22 = "Name";
    public static final String COL_23 = "Caffeine";
    public static final String COL_24 = "Picture directory";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabase = "CREATE DATABASE " + "";
        String createTable1 = "CREATE TABLE " + USER_TABLE_NAME + " (" +
                COL_00 + " VARCHAR(63) NOT NULL, " +
                COL_01 + " VARCHAR(63) NOT NULL, " +
                COL_02 + " VARCHAR(63) NOT NULL, " +
                COL_03 + " VARCHAR(63) NOT NULL);";
        String createTable2 = "CREATE TABLE " + CONSUMPTION_TABLE_NAME + " (" +
                COL_10 + " INT(32) NOT NULL, " +
                COL_11 + " INT(16,16) NOT NULL);";
        String createTable3 = "CREATE TABLE " + PRODUCT_TABLE_NAME + " (" +
                COL_20 + " INT(2) NOT NULL, " +
                COL_21 + " VARCHAR(63) NOT NULL, " +
                COL_22 + " VARCHAR(63) NOT NULL, " +
                COL_23 + " VARCHAR(63) NOT NULL, " +
                COL_24 + " VARCHAR(63) NOT NULL);";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
    }

    public boolean addUser(String itemN, String itemA, String itemW, int itemS){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_00, itemN);
        contentValues.put(COL_01, itemA);
        contentValues.put(COL_02, itemW);
        contentValues.put(COL_03, itemS);

        Log.d(TAG, "Added data: " + COL_00 + ", "  + COL_01 + ", "  + COL_02 + ", "  + COL_03 + " TO " + USER_TABLE_NAME);

        long result = db.insert(USER_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addConsumption(long timeInMillis, double caffeine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_10, timeInMillis);
        contentValues.put(COL_11, caffeine);

        Log.d(TAG, "Added data: " + COL_10 + ", "  + COL_11 + " TO " + CONSUMPTION_TABLE_NAME);

        long result = db.insert(CONSUMPTION_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
}