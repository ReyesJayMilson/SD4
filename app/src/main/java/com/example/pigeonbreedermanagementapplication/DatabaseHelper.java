package com.example.pigeonbreedermanagementapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pigeonbreedermanagementapplication.Egg.EggTrackerFragment;
import com.example.pigeonbreedermanagementapplication.Egg.EggsGetSet;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsFragment;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.Profile.ProfilesGetSet;

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

    public static final String COLUMN_NEST_NO = "NEST_NO";

    public static final String COLUMN_DISEASE_NAME = "DISEASE_NAME";

    public static final String COLUMN_DISEASE_ID = "DISEASE_ID";

    public static final String COLUMN_DISEASE_DESC = "DISEASE_DESC";

    public static final String TABLE_DISEASES = "DISEASES_TABLE";
    public static final String COLUMN_PIGEON_IMAGE = "PIGEON_IMAGE";
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, "breedermanagement.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table for Profiles
        String createProfileTableStatement = "CREATE TABLE " + PROFILE_TABLE + " (" + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROFILE_NAME + " TEXT)";
        db.execSQL(createProfileTableStatement);

        //Create Table for Pigeons
        String createPigeonTableStatement = "CREATE TABLE " + PIGEON_TABLE + " (" + COLUMN_RING_ID + " TEXT PRIMARY KEY, " + COLUMN_PIGEON_NAME + " TEXT, " + COLUMN_CAGE_NO + " TEXT, " + COLUMN_PIGEON_BIRTH_YEAR + " INTEGER, " + COLUMN_PIGEON_BREED + " TEXT, " + COLUMN_PIGEON_GENDER + " TEXT, " + COLUMN_PIGEON_COLOR + " TEXT," + COLUMN_PIGEON_STATUS + " TEXT, " + COLUMN_PIGEON_NOTES + " TEXT, " + COLUMN_PIGEON_IMAGE + " TEXT, FOREIGN KEY (" + COLUMN_CAGE_NO + ") REFERENCES CAGE_TABLE(" + COLUMN_CAGE_NO + "))";
        db.execSQL(createPigeonTableStatement);

        //Create Table for Profiles
        String createEggTrackerTableStatement = "CREATE TABLE " + EGGMONITORING_TABLE + " (" + COLUMN_EGG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CAGE_NO + " INTEGER, " + COLUMN_NEST_NO + " INTEGER, " + COLUMN_LAYING_DATE + " TEXT, " + COLUMN_HATCHING_DATE + " TEXT, " + COLUMN_FATHER + " TEXT, " + COLUMN_MOTHER + " TEXT)";
        db.execSQL(createEggTrackerTableStatement);

        String creatHealthCalendarTableStatement = "CREATE TABLE " + HEALTHCALENDER_TABLE + " (" + COLUMN_HEALTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_DATE + " TEXT, RING_ID , " + COLUMN_NOTE_DESCRIPTION + " TEXT)";
        db.execSQL(creatHealthCalendarTableStatement);

        //Create Table for Profiles
        String createTransactionTableStatement = "CREATE TABLE " + TRANSACTION_TABLE + " (" + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TRANSACTION_TYPE + " TEXT, " + COLUMN_TRANSACTION_DATE + " TEXT, " + COLUMN_TRANSACTION_PARTNER + " TEXT, " + COLUMN_TRANSACTION_AMOUNT + " INTEGER, " + COLUMN_TRANSACTION_DETAILS + " TEXT)";
        db.execSQL(createTransactionTableStatement);

        //Create Table for Profiles
        String createProductTableStatement = "CREATE TABLE " + PRODUCT_TABLE + " (" + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_PRICE + " INTEGER, " + COLUMN_PRODUCT_QUANTITY + " TEXT, " + COLUMN_USE_PER_WEEK + " TEXT)";
        db.execSQL(createProductTableStatement);


        //Create Table for Images

//                                          "CREATE TABLE " + PIGEON_TABLE + " (" + COLUMN_RING_ID + " TEXT PRIMARY KEY, " + COLUMN_PIGEON_NAME + " TEXT, " + COLUMN_PIGEON_BIRTH_YEAR + " INTEGER, " + COLUMN_PIGEON_BREED + " TEXT, " + COLUMN_PIGEON_GENDER + " TEXT, " + COLUMN_PIGEON_COLOR  + " TEXT,"+COLUMN_PIGEON_STATUS + " TEXT, " + COLUMN_PIGEON_NOTES + " TEXT)";


        //Create table for disease
        String createDiseasesTableStatement = "CREATE TABLE DISEASES_TABLE ( DISEASE_ID INTEGER PRIMARY KEY AUTOINCREMENT, DISEASE_NAME TEXT NOT NULL, DISEASE_DESCRIPTION TEXT NOT NULL, DISEASE_RECOMMENDATION TEXT NOT NULL)";
        db.execSQL(createDiseasesTableStatement);

        //Create table for SYMPTOMS
        String createSymptomsTableStatement = "CREATE TABLE SYMPTOMS_TABLE ( SYMPTOM_ID INTEGER PRIMARY KEY AUTOINCREMENT, SYMPTOM_NAME TEXT NOT NULL, DISEASE_ID INTEGER NOT NULL, FOREIGN KEY (DISEASE_ID) REFERENCES DISEASES_TABLE(DISEASE_ID))";
        db.execSQL(createSymptomsTableStatement);


        String insertDiseaseStatement = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('PIGEON CANKER', 'TEST DESC LINE 1 \n\t TEST DESC LINE 2 \n\t TEST DESC LINE 3', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement);
        String insertDiseaseStatement2 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('Lagnat', 'TEST DESC LINE 1 \n\t TEST DESC LINE 2 \n\t TEST DESC LINE 3', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement2);
        String insertDiseaseStatement3 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('Schizophrenia', 'TEST DESC LINE 1 \n\t TEST DESC LINE 2 \n\t TEST DESC LINE 3', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement3);
        String insertDiseaseStatement4 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('Dementia', 'TEST DESC LINE 1 \n\t TEST DESC LINE 2 \n\t TEST DESC LINE 3', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement4);
        String insertDiseaseStatement5 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('Cerebral Palsy', 'TEST DESC LINE 1 \n\t TEST DESC LINE 2 \n\t TEST DESC LINE 3', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement5);
        String insertDiseaseStatement6 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('Erectile Dysfunction', 'TEST DESC LINE 1 \n\t TEST DESC LINE 2 \n\t TEST DESC LINE 3', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement6);
        String insertSymptomStatement1 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Difficulty Swallowing', 1)";
        db.execSQL(insertSymptomStatement1);
        String insertSymptomStatement2 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Vomiting', 1)";
        db.execSQL(insertSymptomStatement2);
        String insertSymptomStatement3 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Yellow or Whitish Cheesy Growths in Mouth or Throat', 1)";
        db.execSQL(insertSymptomStatement3);
        String insertSymptomStatement4 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Weight Loss', 1)";
        db.execSQL(insertSymptomStatement4);
        String insertSymptomStatement5 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Puffed Feathers', 1)";
        db.execSQL(insertSymptomStatement5);
        String insertSymptomStatement6 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Mucus in Throat', 1)";
        db.execSQL(insertSymptomStatement6);





        //Table for Cage
        String createCageTableStatement = "CREATE TABLE CAGE_TABLE ( CAGE_NO INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(createCageTableStatement);
        String initializeCageTableStatement = "INSERT INTO CAGE_TABLE (CAGE_NO) VALUES (1)";
        db.execSQL(initializeCageTableStatement);

        String createNestTableStatement = "CREATE TABLE NEST_TABLE ( NEST_NO INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(createNestTableStatement);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PIGEON_TABLE);

        onCreate(db);
    }


    //////////////////////DISEASE LIBRARY/////////////////////////

    public void insertDisease(Disease disease){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DISEASE_NAME, disease.getName());
        values.put(COLUMN_DISEASE_DESC, disease.getDesc());
        db.insert(TABLE_DISEASES, null, values);
        db.close();
    }

    public ArrayList<Disease> getAllDisease(){
        ArrayList<Disease> diseaseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_DISEASES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{
                Disease disease = new Disease();
                disease.setId(cursor.getInt(0));
                disease.setName(cursor.getString(1));
                disease.setDesc(cursor.getString(2));
                diseaseList.add(disease);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return diseaseList;
    }


    ///////////////////////PROFILES//////////////////////////////
    public boolean addProfile(ProfilesGetSet profiles) {
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

    public boolean DeleteProfile(ProfilesGetSet profiles) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PROFILE_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profiles.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isNameTaken(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PROFILE_TABLE, new String[]{COLUMN_PROFILE_NAME},
                COLUMN_PROFILE_NAME + "=?", new String[]{name},
                null, null, null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    public List<ProfilesGetSet> getEveryProfile() {

        List<ProfilesGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + PROFILE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int userProfileID = cursor.getInt(0);
                String userProfileName = cursor.getString(1);

                ProfilesGetSet newProfile = new ProfilesGetSet(userProfileID, userProfileName);
                returnList.add(newProfile);

            } while (cursor.moveToNext());

        } else {

        }
        return returnList;
    }

    ///////////////////////PIGEONS//////////////////////////////
    public boolean addPigeon(PigeonsGetSet pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RING_ID, pigeons.getRing_id());
        cv.put(COLUMN_PIGEON_NAME, pigeons.getName());
        cv.put(COLUMN_CAGE_NO, pigeons.getCage_no());
        cv.put(COLUMN_PIGEON_BIRTH_YEAR, pigeons.getBirth_year());
        cv.put(COLUMN_PIGEON_BREED, pigeons.getBreed());
        cv.put(COLUMN_PIGEON_GENDER, pigeons.getGender());
        cv.put(COLUMN_PIGEON_COLOR, pigeons.getColor());
        cv.put(COLUMN_PIGEON_STATUS, pigeons.getStatus());
        cv.put(COLUMN_PIGEON_NOTES, pigeons.getNotes());
        cv.put(COLUMN_PIGEON_IMAGE, pigeons.getImage());


        long insert = db.insert(PIGEON_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {
            ArrayList<PigeonsGetSet> updatedList = getEveryPigeon();
            PigeonsFragment.pigeonadapter.notifyDataSetChanged();
            PigeonsFragment.pigeonadapter.setPigeons(updatedList);
            return true;
        }
    }

    public boolean deletePigeon(PigeonsGetSet pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_RING_ID + " = ?";
        String[] whereArgs = {pigeons.getRing_id()};
        int rowsDeleted = db.delete(PIGEON_TABLE, whereClause, whereArgs);

        if (rowsDeleted > 0) {
            // update the RecyclerView
            ArrayList<PigeonsGetSet> updatedList = getEveryPigeon();
            PigeonsFragment.pigeonadapter.setPigeons(updatedList);
            PigeonsFragment.pigeonadapter.notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    public boolean editPigeon(PigeonsGetSet pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RING_ID, pigeons.getRing_id());
        contentValues.put(COLUMN_PIGEON_NAME, pigeons.getName());
        contentValues.put(COLUMN_CAGE_NO, pigeons.getCage_no());
        contentValues.put(COLUMN_PIGEON_BIRTH_YEAR, pigeons.getBirth_year());
        contentValues.put(COLUMN_PIGEON_BREED, pigeons.getBreed());
        contentValues.put(COLUMN_PIGEON_GENDER, pigeons.getGender());
        contentValues.put(COLUMN_PIGEON_COLOR, pigeons.getColor());
        contentValues.put(COLUMN_PIGEON_STATUS, pigeons.getStatus());
        contentValues.put(COLUMN_PIGEON_NOTES, pigeons.getNotes());
        contentValues.put(COLUMN_PIGEON_IMAGE, pigeons.getImage());
        String whereClause = COLUMN_RING_ID + " = ?";
        String[] whereArgs = {pigeons.getRing_id()};
        int update = db.update(PIGEON_TABLE, contentValues, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
            ArrayList<PigeonsGetSet> updatedList = getEveryPigeon();
            PigeonsFragment.pigeonadapter.setPigeons(updatedList);
            PigeonsFragment.pigeonadapter.notifyDataSetChanged();
            return true;
        }
    }


    public ArrayList<PigeonsGetSet> getEveryPigeon() {

        ArrayList<PigeonsGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + PIGEON_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                String ringID = cursor.getString(0);
                String pigeonName = cursor.getString(1);
                int cageNumber = cursor.getInt(2);
                int pigeonBirthYear = cursor.getInt(3);
                String pigeonBreed = cursor.getString(4);
                String pigeonGender = cursor.getString(5);
                String pigeonColor = cursor.getString(6);
                String pigeonStatus = cursor.getString(7);
                String pigeonNotes = cursor.getString(8);
                String pigeonImage = cursor.getString(9);


                PigeonsGetSet newPigeons = new PigeonsGetSet(ringID, pigeonName, cageNumber, pigeonBirthYear, pigeonBreed, pigeonGender, pigeonColor, pigeonStatus, pigeonNotes, pigeonImage);
                returnList.add(newPigeons);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        Log.d("TAG", "returnlist" + returnList);
        return returnList;
    }

    public List<String> getAllRingIds() {
        List<String> ringIds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PIGEON_TABLE, new String[] { COLUMN_RING_ID }, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ringIds.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ringIds;
    }

    ///////////////////////PROFILES//////////////////////////////
    public boolean addEgg(EggsGetSet eggs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CAGE_NO, eggs.getCage_number());
        cv.put(COLUMN_NEST_NO, eggs.getNest_number());
        cv.put(COLUMN_LAYING_DATE, eggs.getLaying_date());
        cv.put(COLUMN_HATCHING_DATE, eggs.getHatching_date());
        cv.put(COLUMN_FATHER, eggs.getFather());
        cv.put(COLUMN_MOTHER, eggs.getMother());


        long insert = db.insert(EGGMONITORING_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {
            ArrayList<EggsGetSet> updatedList = getEveryEgg();
            EggTrackerFragment.eggadapter.notifyDataSetChanged();
            EggTrackerFragment.eggadapter.setEggs(updatedList);
            return true;
        }

    }

    public ArrayList<EggsGetSet> getEveryEgg() {

        ArrayList<EggsGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + EGGMONITORING_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int eggID = cursor.getInt(0);
                int cageNumber = cursor.getInt(1);
                int nestNumber = cursor.getInt(2);
                String layDate = cursor.getString(3);
                String hatchDate = cursor.getString(4);
                String father = cursor.getString(5);
                String mother = cursor.getString(6);


                EggsGetSet newEggs = new EggsGetSet(eggID, cageNumber, nestNumber, layDate, hatchDate, father, mother);
                returnList.add(newEggs);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addCageNumber() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CAGE_NO", (Integer) null);
        long insert = db.insert("CAGE_TABLE", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Integer> getAllCageNumbers() {
        List<Integer> cageNumbers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CAGE_TABLE", null);

        if (cursor.moveToFirst()) {
            do {
                cageNumbers.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cageNumbers;

    }

}
