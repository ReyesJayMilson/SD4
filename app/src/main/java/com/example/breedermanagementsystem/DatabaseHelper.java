package com.example.breedermanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String PROFILE_TABLE = "PROFILE_TABLE";
    public static final String COLUMN_PROFILE_NAME = "PROFILE_NAME";
    public static final String COLUMN_PROFILE_ID = "PROFILE_ID";
    public static final String PIGEON_TABLE = "PIGEON_TABLE";
    public static final String COLUMN_PIGEON_NAME = "PIGEON_NAME";
    public static final String COLUMN_RING_ID = "RING_ID";
    public static final String COLUMN_PIGEON_BIRTH_YEAR = "PIGEON_BIRTH_YEAR";
    public static final String COLUMN_PIGEON_BREED = "PIGEON_BREED";
    public static final String COLUMN_PIGEON_GENDER = "PIGEON_GENDER";
    public static final String COLUMN_PIGEON_COLOR = "PIGEON_COLOR";
    public static final String COLUMN_PIGEON_STATUS = "PIGEON_STATUS";
    public static final String COLUMN_PIGEON_NOTES = "PIGEON_NOTES";
    public static final String EGGMONITORING_TABLE = "EGGMONITORING_TABLE";
    public static final String COLUMN_EGG_ID = "EGG_ID";
    private Context context;


    public DatabaseHelper( Context context) {
        super(context, "breedermanagement.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table for Profiles
        String createProfileTableStatement = "CREATE TABLE " + PROFILE_TABLE + " (" + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROFILE_NAME + " TEXT)";
        db.execSQL(createProfileTableStatement);

        //Create Table for Pigeons
        String createPigeonTableStatement = "CREATE TABLE " + PIGEON_TABLE + " (" + COLUMN_RING_ID + " TEXT PRIMARY KEY, " + COLUMN_PIGEON_NAME + " TEXT, " + COLUMN_PIGEON_BIRTH_YEAR + " INTEGER, " + COLUMN_PIGEON_BREED + " TEXT, " + COLUMN_PIGEON_GENDER + " TEXT, " + COLUMN_PIGEON_COLOR + " TEXT," + COLUMN_PIGEON_STATUS + " TEXT, " + COLUMN_PIGEON_NOTES + " TEXT, " + COLUMN_PROFILE_ID + "TEXT)";
        db.execSQL(createPigeonTableStatement);

        //Create Table for Profiles
        String createEggTrackerTableStatement = "CREATE TABLE " + EGGMONITORING_TABLE + " (" + COLUMN_EGG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, CAGE_NO INTEGER, NEST_NO INTEGER, LAYING_DATE DATE, HATCHING_DATE DATE, FATHER TEXT, MOTHER TEXT)";
        db.execSQL(createEggTrackerTableStatement);

        String creatHealthCalendarTableStatement = "CREATE TABLE HEALTHCALENDER_TABLE (HEALTH_ID INTEGER PRIMARY KEY AUTOINCREMENT, NOTE_DATE DATE, RING_ID, NOTE_DESCRIPTION TEXT)";
        db.execSQL(creatHealthCalendarTableStatement);

        //Create Table for Profiles
        String createTransactionTableStatement = "CREATE TABLE TRANSACTION_TABLE (TRANSACTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, TRANSACTION_TYPE TEXT, TRANSACTION_DATE DATE, TRANSACTION_PARTNER, TRANSACTION_AMOUNT INTEGER, TRANSACTION_DETAILS TEXT)";
        db.execSQL(createTransactionTableStatement);

        //Create Table for Profiles
        String createProductTableStatement = "CREATE TABLE PRODUCT_TABLE (PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCT_NAME TEXT, PRODUCT_PRICE INTEGER, PRODUCT_QUANTITY TEXT, USE_PER_WEEK TEXT)";
        db.execSQL(createProductTableStatement);

        //Create Table for Profiles
        String createCommonDiseaseLibraryTableStatement = "CREATE TABLE COMMONDISEASELIBRARY_TABLE (DISEASE_ID INTEGER PRIMARY KEY AUTOINCREMENT, DISEASE_NAME TEXT, DISEASE_DESCRIPTION TEXT)";
        db.execSQL(createCommonDiseaseLibraryTableStatement);



//                                          "CREATE TABLE " + PIGEON_TABLE + " (" + COLUMN_RING_ID + " TEXT PRIMARY KEY, " + COLUMN_PIGEON_NAME + " TEXT, " + COLUMN_PIGEON_BIRTH_YEAR + " INTEGER, " + COLUMN_PIGEON_BREED + " TEXT, " + COLUMN_PIGEON_GENDER + " TEXT, " + COLUMN_PIGEON_COLOR  + " TEXT,"+COLUMN_PIGEON_STATUS + " TEXT, " + COLUMN_PIGEON_NOTES + " TEXT)";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PIGEON_TABLE);
        onCreate(db);
    }

    ///////////////////////PROFILES//////////////////////////////
    public boolean addProfile(GetSetProfiles profiles) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PROFILE_NAME, profiles.getName());

        long insert = db.insert(PROFILE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean DeleteProfile(GetSetProfiles profiles) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PROFILE_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profiles.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }


    public boolean isNameTaken(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PROFILE_TABLE, new String[] {COLUMN_PROFILE_NAME},
                COLUMN_PROFILE_NAME + "=?", new String[] {name},
                null,null,null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    public List<GetSetProfiles> getEveryProfile() {

        List<GetSetProfiles> returnList = new ArrayList<>();
        
        // get data from the database
        
        String queryString = "SELECT * FROM " + PROFILE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int userProfileID = cursor.getInt(0);
                String userProfileName = cursor.getString(1);

                GetSetProfiles newProfile = new GetSetProfiles(userProfileID, userProfileName);
                returnList.add(newProfile);

            } while (cursor.moveToNext());

        }
        else {

        }
        return returnList;
    }

    ///////////////////////PIGEONS//////////////////////////////
    public boolean addPigeon(GetSetPigeons pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RING_ID, pigeons.getRing_id());
        cv.put(COLUMN_PIGEON_NAME, pigeons.getName());
        cv.put(COLUMN_PIGEON_BIRTH_YEAR, pigeons.getBirth_year());
        cv.put(COLUMN_PIGEON_BREED, pigeons.getBreed());
        cv.put(COLUMN_PIGEON_GENDER, pigeons.getGender());
        cv.put(COLUMN_PIGEON_COLOR, pigeons.getColor());
        cv.put(COLUMN_PIGEON_STATUS, pigeons.getStatus());
        cv.put(COLUMN_PIGEON_NOTES, pigeons.getNotes());


        long insert = db.insert(PIGEON_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {
            ArrayList<GetSetPigeons> updatedList = getEveryPigeon();
            MyPigeonsFragment.adapter.notifyDataSetChanged();
            MyPigeonsFragment.adapter.setPigeons(updatedList);
            return true;
        }


    }

    public boolean deletePigeon(GetSetPigeons pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_RING_ID + " = ?";
        String[] whereArgs = { pigeons.getRing_id() };
        int rowsDeleted = db.delete(PIGEON_TABLE, whereClause, whereArgs);

        if (rowsDeleted > 0) {
            // update the RecyclerView
            ArrayList<GetSetPigeons> updatedList = getEveryPigeon();
            MyPigeonsFragment.adapter.setPigeons(updatedList);
            MyPigeonsFragment.adapter.notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }
    public boolean editPigeon(GetSetPigeons pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RING_ID, pigeons.getRing_id());
        contentValues.put(COLUMN_PIGEON_NAME, pigeons.getName());
        contentValues.put(COLUMN_PIGEON_BIRTH_YEAR, pigeons.getBirth_year());
        contentValues.put(COLUMN_PIGEON_BREED, pigeons.getBreed());
        contentValues.put(COLUMN_PIGEON_GENDER, pigeons.getGender());
        contentValues.put(COLUMN_PIGEON_COLOR, pigeons.getColor());
        contentValues.put(COLUMN_PIGEON_STATUS, pigeons.getStatus());
        contentValues.put(COLUMN_PIGEON_NOTES, pigeons.getNotes());
        String whereClause = COLUMN_RING_ID + " = ?";
        String[] whereArgs = { pigeons.getRing_id() };
        int update = db.update(PIGEON_TABLE, contentValues, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
            ArrayList<GetSetPigeons> updatedList = getEveryPigeon();
            MyPigeonsFragment.adapter.setPigeons(updatedList);
            MyPigeonsFragment.adapter.notifyDataSetChanged();
            return true;
        }
    }


    public ArrayList<GetSetPigeons> getEveryPigeon() {

        ArrayList<GetSetPigeons> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + PIGEON_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                String ringID = cursor.getString(0);
                String pigeonName = cursor.getString(1);
                int pigeonBirthYear = cursor.getInt(2);
                String pigeonBreed = cursor.getString(3);
                String pigeonGender = cursor.getString(4);
                String pigeonColor = cursor.getString(5);
                String pigeonStatus = cursor.getString(6);
                String pigeonNotes = cursor.getString(7);


                GetSetPigeons newPigeons = new GetSetPigeons(ringID, pigeonName, pigeonBirthYear, pigeonBreed, pigeonGender, pigeonColor, pigeonStatus, pigeonNotes );
                returnList.add(newPigeons);

            } while (cursor.moveToNext());

        }
        else {

        }
        return returnList;
    }

}
