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
    public static final String COLUMN_CAGE_NO = "CAGE_NO";
    public static final String COLUMN_LAYING_DATE = "LAYING_DATE";
    public static final String COLUMN_HATCHING_DATE = "HATCHING_DATE";
    public static final String COLUMN_FATHER = "FATHER";
    public static final String COLUMN_MOTHER = "MOTHER";
    public static final String HEALTHCALENDER_TABLE = "HEALTHCALENDER_TABLE";
    public static final String COLUMN_HEALTH_ID = "HEALTH_ID";
    public static final String COLUMN_NOTE_DATE = "NOTE_DATE";
    public static final String COLUMN_NOTE_DESCRIPTION = "NOTE_DESCRIPTION";
    public static final String TRANSACTION_TABLE = "TRANSACTION_TABLE";
    public static final String COLUMN_TRANSACTION_ID = "TRANSACTION_ID";
    public static final String COLUMN_TRANSACTION_TYPE = "TRANSACTION_TYPE";
    public static final String COLUMN_TRANSACTION_DATE = "TRANSACTION_DATE";
    public static final String COLUMN_TRANSACTION_PARTNER = "TRANSACTION_PARTNER";
    public static final String COLUMN_TRANSACTION_AMOUNT = "TRANSACTION_AMOUNT";
    public static final String COLUMN_TRANSACTION_DETAILS = "TRANSACTION_DETAILS";
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COLUMN_PRODUCT_QUANTITY = "PRODUCT_QUANTITY";
    public static final String COLUMN_USE_PER_WEEK = "USE_PER_WEEK";
    public static final String COMMONDISEASELIBRARY_TABLE = "COMMONDISEASELIBRARY_TABLE";
    public static final String COLUMN_DISEASE_ID = "DISEASE_ID";
    public static final String COLUMN_DISEASE_NAME = "DISEASE_NAME";
    public static final String COLUMN_DISEASE_DESCRIPTION = "DISEASE_DESCRIPTION";
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
        String createEggTrackerTableStatement = "CREATE TABLE " + EGGMONITORING_TABLE + " (" + COLUMN_EGG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CAGE_NO + " INTEGER, NEST_NO INTEGER, " + COLUMN_LAYING_DATE + " DATE, " + COLUMN_HATCHING_DATE + " DATE, " + COLUMN_FATHER + " TEXT, " + COLUMN_MOTHER + " TEXT)";
        db.execSQL(createEggTrackerTableStatement);

        String creatHealthCalendarTableStatement = "CREATE TABLE " + HEALTHCALENDER_TABLE + " (" + COLUMN_HEALTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_DATE + " DATE, RING_ID , " + COLUMN_NOTE_DESCRIPTION + " TEXT)";
        db.execSQL(creatHealthCalendarTableStatement);

        //Create Table for Profiles
        String createTransactionTableStatement = "CREATE TABLE " + TRANSACTION_TABLE + " (" + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TRANSACTION_TYPE + " TEXT, " + COLUMN_TRANSACTION_DATE + " DATE, " + COLUMN_TRANSACTION_PARTNER + " TEXT, " + COLUMN_TRANSACTION_AMOUNT + " INTEGER, " + COLUMN_TRANSACTION_DETAILS + " TEXT)";
        db.execSQL(createTransactionTableStatement);

        //Create Table for Profiles
        String createProductTableStatement = "CREATE TABLE " + PRODUCT_TABLE + " (" + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_PRICE + " INTEGER, " + COLUMN_PRODUCT_QUANTITY + " TEXT, " + COLUMN_USE_PER_WEEK + " TEXT)";
        db.execSQL(createProductTableStatement);

        //Create Table for Profiles
        String createCommonDiseaseLibraryTableStatement = "CREATE TABLE " + COMMONDISEASELIBRARY_TABLE + " (" + COLUMN_DISEASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DISEASE_NAME + " TEXT, " + COLUMN_DISEASE_DESCRIPTION + " TEXT)";
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
