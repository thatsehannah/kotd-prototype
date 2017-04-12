package com.example.elliothannahiii.kotd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by elliothannahiii on 4/7/17.
 */

public class KOTDDBHandler extends SQLiteOpenHelper {

    private static KOTDDBHandler kotddbHandler;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kotdshoes.db";
    public static final String TABLE_NAME = "usershoes";
    public static final String COLUMN_ID = "shoeID";
    public static final String COLUMN_SHOENAME = "shoeName";
    public static final String COLUMN_SHOEWEATHER = "shoeWeather";
    public static final String COLUMN_SHOEACTIVITY = "shoeActivity";
    public static final String COLUMN_SHOEFAVORITE = "shoeFavorite";

    public KOTDDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SHOENAME + " TEXT, " +
                COLUMN_SHOEWEATHER + " TEXT, " +
                COLUMN_SHOEACTIVITY + " TEXT, " +
                COLUMN_SHOEFAVORITE + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    //singleton of database
    public static KOTDDBHandler getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        if (null==kotddbHandler){
            kotddbHandler = new KOTDDBHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        return kotddbHandler;
    }


    //add a new shoe to the database
    public void addShoe(Shoes shoes){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SHOENAME, shoes.getshoeName());
        values.put(COLUMN_SHOEWEATHER, shoes.getshoeWeather());
        values.put(COLUMN_SHOEACTIVITY, shoes.getshoeActivity());
        values.put(COLUMN_SHOEFAVORITE, shoes.getshoeFavorite());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //retrieve all rows from database
    public Cursor countRows(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        Cursor cr = db.rawQuery(query, null);
        return cr;
    }

    //delete a shoe from database
    public void deleteShoe(String shoeNm){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_SHOENAME + "=\'" + shoeNm + "\';\"");
    }

    //delete all shoes from database
    public void deleteAllShoes(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + ";\"");
    }

    //Print out the database as a string
    public Cursor shoeNameToString(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_SHOENAME + " FROM " + TABLE_NAME + ";\"";

        //Cursor point to a location in results
        Cursor sn = db.rawQuery(query, null);
        return sn;
    }

    //Retrieve shoe weather
    public Cursor shoeWeatherToString(String shoeNm){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_SHOEWEATHER + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_SHOENAME + "=\"" + shoeNm + ";\"";
        Cursor sw = db.rawQuery(query,null);
        return sw;
    }

    //Retrieve shoe activity
    public Cursor shoeActivityToString(String shoeNm){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_SHOEACTIVITY + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_SHOENAME + "=\"" + shoeNm +";\"";
        Cursor sa = db.rawQuery(query,null);
        return sa;
    }

    //Retrieve shoe favorite
    public Cursor shoeFavoriteToString(String shoeNm){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_SHOEFAVORITE + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_SHOENAME + "=\"" + shoeNm +";\"";
        Cursor sf = db.rawQuery(query,null);
        return sf;
    }

    //Retrieve all shoe data
    public Cursor shoeDataToString(String shoeNm){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                COLUMN_SHOENAME + "=\'" + shoeNm +"\';\"";
        Cursor sd = db.rawQuery(query,null);
        return sd;
    }

    //Retrieve shoeID
    public Cursor shoeID(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_SHOENAME + " FROM " + TABLE_NAME + " WHERE " +
                COLUMN_ID + "=\"" + id +";\"";
        Cursor si = db.rawQuery(query, null);
        return si;
    }

    public Cursor getShoeByWeather(String weather){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_SHOENAME + " FROM " + TABLE_NAME + " WHERE " +
                COLUMN_SHOEWEATHER + " LIKE \'%" + weather + "%\' ORDER BY RANDOM() LIMIT 1;";
        Cursor rsw = db.rawQuery(query, null);
        return rsw;
    }

}
