package com.example.breedermanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;


import androidx.annotation.Nullable;
import androidx.core.database.sqlite.SQLiteDatabaseKt;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String USERPROFILE_TABLE = "USERPROFILE_TABLE";
    public static final String COLUMN_USERPROFILE_NAME = "USERPROFILE_NAME";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "breedermanagement.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USERPROFILE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERPROFILE_NAME + " TEXT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Profiles profiles) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERPROFILE_NAME, profiles.getName());

        long insert = db.insert(USERPROFILE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean isNameTaken(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USERPROFILE_TABLE, new String[] {COLUMN_USERPROFILE_NAME},
                COLUMN_USERPROFILE_NAME + "=?", new String[] {name},
                null,null,null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    public List<Profiles> getEveryone() {

        List<Profiles> returnList = new ArrayList<>();
        
        // get data from the database
        
        String queryString = "SELECT * FROM " + USERPROFILE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int userProfileID = cursor.getInt(0);
                String userProfileName = cursor.getString(1);

                Profiles newProfile = new Profiles(userProfileID, userProfileName);
                returnList.add(newProfile);

            } while (cursor.moveToNext());

        }
        else {

        }
        return returnList;
    }
}
