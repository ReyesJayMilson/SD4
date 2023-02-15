package com.example.pigeonbreedermanagementapplication;

import android.annotation.SuppressLint;
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
import com.example.pigeonbreedermanagementapplication.Product.ProductFragment;
import com.example.pigeonbreedermanagementapplication.Product.ProductGetSet;
import com.example.pigeonbreedermanagementapplication.Profile.ProfilesGetSet;
import com.example.pigeonbreedermanagementapplication.Transaction.TransactionFragment;
import com.example.pigeonbreedermanagementapplication.Transaction.TransactionGetSet;

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

    public static final String TABLE_SYMPTOMS = "SYMPTOMS_TABLE";

    public static final String COLUMN_SYMPTOM_DISEASE_ID = "DISEASE_ID";

    public static final String COLUMN_SYMPTOM_ID = "SYMPTOM_ID";

    public static final String COLUMN_SYMPTOM_NAME = "SYMPTOM_NAME";

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


        String insertDiseaseStatement = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('PIGEON CANKER', 'Trichomoniasis (pigeon canker) is the most common disease of pigeons. Approximately 80 percent of pigeons are infected with this organism. The organism is a microscopic flagellate classified as a protozoan. Different strains, Trichomonas gallinae or Trichomonas columbae, vary greatly in their ability to cause disease. The disease occurs worldwide in warm climates or during warm weather. It may occur at any time of the year in commercial squab operations. Adult pigeons frequently carry the trichomonads without showing signs of disease. When the adult pigeon is stressed, however, the organisms may multiply profusely. A mild infection can then turn into a serious condition. Stresses include other diseases, parasitic infestations, or overbreeding.', 'Birds can be treated with Ronidazole (Ronnivet-S) in the water for seven days. It has a wide safety margin. Regular re-treatments are advised.\n Pigeons can be treated in a single dose of Carnidazole (Spartrix) and Metronidazole (Flagyl), has been also used in the past orally for 2-10 days. All of these drugs are prescription only, not for sale over the counter.')";
        db.execSQL(insertDiseaseStatement);
        String insertDiseaseStatement2 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('PIGEON WORM', 'There are 3 common worm infestation in pigeons which are: \n1. Roundworms(Ascaris) - Roundworms form the most common worm infestation in pigeons. They are present in the small intestine of the pigeon and in serious cases they can be present in such large quantities that the intestine is almost completely blocked. There are few external symptoms in case of a relatively light infection. Only the racing results will be disappointing in such cases due to the weakened condition. \n\n2. Hairworms(Capillaria) - The hairworms are the smallest (not even visible to the eye), but most annoying type of worms. Just like the roundworm, they reside in the small intestine, but they bore their way into the intestinal wall and the blood vessels in the intestinal wall. This causes an inflammation of the intestines and the pigeons lose weight fast and get sick. \n\n3. Tapeworm - Usually, we see a pigeon with something like a grain of rice hanging from its hindquarters. This is a link of a tapeworm and in many cases, when you pull it very carefully, you can pull out a tapeworm of 30 to 50 cm in length. However, there are usually several tapeworms present in the body and treatment is recommended.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement2);
        String insertDiseaseStatement3 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('PIGEON COCCIDIA', 'Coccidia are a group of parasitic organisms that have the amazing ability to reproduce themselves both sexually and asexually in various organs throughout the body. There are lots of different types. Some reproduce in the kidney, others in the liver, some are carried throughout the body in red blood cells, but the common one that infects racing pigeons affects the bowel.\n\nBasically what happens is that the organism releases eggs that come out in the droppings. These have to sit in the environment for at least a couple of days to become infective. They do however become infective quicker in damp conditions. Once infective, if a pigeon accidentally swallows one of these eggs, they move down into the bowel and hatch. In the common type of coccidia in pigeons four “larvae” come out of each egg. These then burrow into the bowel wall where initially they reproduce asexually –essentially they just keep dividing so that two become four become eight etc. After a while these larvae differentiate into males and females. These then reproduce sexually resulting in the formation of eggs. These eggs then rupture back into the bowel before passing out of the body in the droppings. In this way the lifecycle is completed.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement3);
        String insertDiseaseStatement4 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('PIGEON HEXAMITA', 'Hexamita meleagridis (pigeons H. columbae) is a protozoan parasite of turkeys, pheasants, pigeons, and some game birds. It is transmitted by faeces, fomites, carriers. Inter-species transmission may occur. In commercial ducks a related parasite Tetratrichomonas can cause poor growth and drops in egg production.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement4);
        String insertDiseaseStatement5 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('PIGEON MYCOPLASMA', 'Mycoplasma gallisepticum causes respiratory infections in chickens, turkeys, and other avian species. Morbidity is typically high and mortality low in affected flocks, and signs are generally more severe in turkeys. Real-time PCR is becoming the most common test used for diagnosis. Antibiotics may reduce clinical signs and transmission through eggs, but they do not eliminate infection. Control is achieved by good biosecurity and sourcing stock from M gallisepticum-free breeder flocks.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement5);
        String insertDiseaseStatement6 = "INSERT INTO DISEASES_TABLE (DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('PIGEON RESPIRATORY INFECTION', 'Clinical respiratory infection in pigeons is the end result of the interplay of a number of factors, but the type of infective organisms involved and the vulnerability of the birds to infection are particularly important. \n\nThe usual organisms involved are Mycoplasma, Chlamydia and a range of bacteria (most commonly, E. coli). Whether or not these organisms actually cause disease in a pigeon, if it is exposed, essentially depends on how well the pigeon is at the time of exposure and also its age and level of immunity. Any factors that cause physiological stress can weaken the bird and make it more vulnerable to developing a respiratory infection. As a general rule, younger pigeons are more susceptible. However, in a bird that is otherwise healthy exposure to the agents that cause respiratory infection does not necessarily make it sick. Exposure to these organisms as the young pigeons grow and develop, rather than cause disease, stimulates immunity to form in a bird that is otherwise healthy.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
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
            String insertSymptomStatement7 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Weight Loss', 2)";
            db.execSQL(insertSymptomStatement7);
            String insertSymptomStatement8 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Droopiness', 2)";
            db.execSQL(insertSymptomStatement8);
            String insertSymptomStatement9 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Diarrhea', 2)";
            db.execSQL(insertSymptomStatement9);
            String insertSymptomStatement10 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Huddling', 3)";
            db.execSQL(insertSymptomStatement10);
            String insertSymptomStatement11 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Difficulty Eating', 3)";
            db.execSQL(insertSymptomStatement11);
            String insertSymptomStatement12 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Diarrhea', 3)";
            db.execSQL(insertSymptomStatement12);
            String insertSymptomStatement13 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Bloody Diarrhea', 3)";
            db.execSQL(insertSymptomStatement13);
            String insertSymptomStatement14 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Watery', 3)";
            db.execSQL(insertSymptomStatement14);
            String insertSymptomStatement15 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Weakness', 3)";
            db.execSQL(insertSymptomStatement15);
            String insertSymptomStatement16 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Weight Loss', 3)";
            db.execSQL(insertSymptomStatement16);
            String insertSymptomStatement17 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Depression', 3)";
            db.execSQL(insertSymptomStatement17);
        String insertSymptomStatement18 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Watery Feces', 4)";
        db.execSQL(insertSymptomStatement18);
        String insertSymptomStatement19 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Mucus on Feces', 4)";
        db.execSQL(insertSymptomStatement19);
        String insertSymptomStatement20 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Greenish Feces', 4)";
        db.execSQL(insertSymptomStatement20);
        String insertSymptomStatement21 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Vomiting', 4)";
        db.execSQL(insertSymptomStatement21);
        String insertSymptomStatement22 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Diarrhea', 4)";
        db.execSQL(insertSymptomStatement22);
        String insertSymptomStatement23 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Dehydration', 4)";
        db.execSQL(insertSymptomStatement23);
        String insertSymptomStatement24 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Weight Loss', 4)";
        db.execSQL(insertSymptomStatement24);
        String insertSymptomStatement25 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Weakness', 4)";
        db.execSQL(insertSymptomStatement25);
        String insertSymptomStatement26 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Runny Nose', 5)";
        db.execSQL(insertSymptomStatement26);
        String insertSymptomStatement27 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Coughing', 5)";
        db.execSQL(insertSymptomStatement27);
        String insertSymptomStatement28 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Unuusual Breathing Sounds', 5)";
        db.execSQL(insertSymptomStatement28);
        String insertSymptomStatement29 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Swollen Eye', 5)";
        db.execSQL(insertSymptomStatement29);
        String insertSymptomStatement30 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Swollen Face', 5)";
        db.execSQL(insertSymptomStatement30);
        String insertSymptomStatement31 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Sneezing', 6)";
        db.execSQL(insertSymptomStatement31);
        String insertSymptomStatement32 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Eye Discharge', 6)";
        db.execSQL(insertSymptomStatement32);
        String insertSymptomStatement33 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Runny Nose', 6)";
        db.execSQL(insertSymptomStatement33);
        String insertSymptomStatement34 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Swollen Sinus', 6)";
        db.execSQL(insertSymptomStatement34);
        String insertSymptomStatement35 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Yawning', 6)";
        db.execSQL(insertSymptomStatement35);
        String insertSymptomStatement36 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Swallowing', 6)";
        db.execSQL(insertSymptomStatement36);
        String insertSymptomStatement37 = "INSERT INTO SYMPTOMS_TABLE (SYMPTOM_NAME, DISEASE_ID) VALUES ('Stretching of Neck', 6)";
        db.execSQL(insertSymptomStatement37);




            //Table for Cage
            String createCageTableStatement = "CREATE TABLE CAGE_TABLE ( CAGE_NO INTEGER PRIMARY KEY AUTOINCREMENT)";
            db.execSQL(createCageTableStatement);
            String initializeCageTableStatement = "INSERT INTO CAGE_TABLE (CAGE_NO) VALUES (1)";
            db.execSQL(initializeCageTableStatement);

            String createNestTableStatement = "CREATE TABLE NEST_TABLE ( NEST_NO INTEGER PRIMARY KEY AUTOINCREMENT)";
            db.execSQL(createNestTableStatement);
            String initializeNestTableStatement = "INSERT INTO NEST_TABLE (NEST_NO) VALUES (1)";
            db.execSQL(initializeNestTableStatement);
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

//    List<Symptom> getSymptomsForDisease(int diseaseId) {
//        List<Symptom> symptomList = new ArrayList<>();
//
//        // Connect to the database and fetch the data
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selectQuery = "SELECT * FROM " + TABLE_SYMPTOMS + " WHERE " + COLUMN_SYMPTOM_DISEASE_ID + " = " + diseaseId;
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // Iterate through the cursor and add each symptom to the list
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") int symptomId = cursor.getInt(cursor.getColumnIndex(COLUMN_SYMPTOM_ID));
//                @SuppressLint("Range") String symptomName = cursor.getString(cursor.getColumnIndex(COLUMN_SYMPTOM_NAME));
//
//                Symptom symptom = new Symptom(symptomId, symptomName);
//                symptomList.add(symptom);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return symptomList;
//    }


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

    ///////////////////////EGGS//////////////////////////////
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

    //////////////////////TRANSACTIONS/////////////////////////

    public boolean addTransactions(TransactionGetSet transactions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TRANSACTION_TYPE, transactions.getTransaction_type());
        cv.put(COLUMN_TRANSACTION_DATE, transactions.getTransaction_date());
        cv.put(COLUMN_TRANSACTION_PARTNER, transactions.getTransaction_partner());
        cv.put(COLUMN_TRANSACTION_AMOUNT, transactions.getTransaction_amount());
        cv.put(COLUMN_TRANSACTION_DETAILS, transactions.getTransaction_details());



        long insert = db.insert(TRANSACTION_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {
            ArrayList<TransactionGetSet> updatedList = getEveryTransaction();
            TransactionFragment.transactionadapter.notifyDataSetChanged();
            TransactionFragment.transactionadapter.setTransactions(updatedList);
            return true;
        }
    }

    public boolean deleteTransaction(TransactionGetSet transactions) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_TRANSACTION_ID + " = ?";
        String[] whereArgs = {String.valueOf(transactions.getTransaction_id())};
        int rowsDeleted = db.delete(TRANSACTION_TABLE, whereClause, whereArgs);

        if (rowsDeleted > 0) {
            // update the RecyclerView
            ArrayList<TransactionGetSet> updatedList = getEveryTransaction();
            TransactionFragment.transactionadapter.notifyDataSetChanged();
            TransactionFragment.transactionadapter.setTransactions(updatedList);
            return true;
        } else {
            return false;
        }
    }

    public boolean editTransaction(TransactionGetSet transactions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TRANSACTION_ID, transactions.getTransaction_id());
        cv.put(COLUMN_TRANSACTION_TYPE, transactions.getTransaction_type());
        cv.put(COLUMN_TRANSACTION_DATE, transactions.getTransaction_date());
        cv.put(COLUMN_TRANSACTION_PARTNER, transactions.getTransaction_partner());
        cv.put(COLUMN_TRANSACTION_AMOUNT, transactions.getTransaction_amount());
        cv.put(COLUMN_TRANSACTION_DETAILS, transactions.getTransaction_details());
        String whereClause = COLUMN_TRANSACTION_ID + " = ?";
        String[] whereArgs = {String.valueOf(transactions.getTransaction_id())};
        int update = db.update(TRANSACTION_TABLE, cv, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
            ArrayList<TransactionGetSet> updatedList = getEveryTransaction();
            TransactionFragment.transactionadapter.notifyDataSetChanged();
            TransactionFragment.transactionadapter.setTransactions(updatedList);
            return true;
        }
    }


    public ArrayList<TransactionGetSet> getEveryTransaction() {

        ArrayList<TransactionGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + TRANSACTION_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int transactionID = cursor.getInt(0);
                String transactionType = cursor.getString(1);
                String transactionDate = cursor.getString(2);
                String transactionPartner = cursor.getString(3);
                int transactionAmount = cursor.getInt(4);
                String transactionDetails = cursor.getString(5);


                TransactionGetSet newTransactions = new TransactionGetSet(transactionID, transactionType, transactionDate, transactionPartner, transactionAmount, transactionDetails);
                returnList.add(newTransactions);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        return returnList;
    }
    /////////////////////////////////////////////////////////////////
    //////////////////////PRODUCTS/////////////////////////

    public boolean addProduct(ProductGetSet products) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT_NAME, products.getProduct_name());
        cv.put(COLUMN_PRODUCT_PRICE, products.getProduct_price());
        cv.put(COLUMN_PRODUCT_QUANTITY, products.getProduct_quantity());
        cv.put(COLUMN_USE_PER_WEEK, products.getUse_per_week());



        long insert = db.insert(PRODUCT_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {
            ArrayList<ProductGetSet> updatedList = getEveryProduct();
            ProductFragment.productadapter.notifyDataSetChanged();
            ProductFragment.productadapter.setProducts(updatedList);
            return true;
        }
    }

    public boolean deleteProduct(ProductGetSet products) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_PRODUCT_ID + " = ?";
        String[] whereArgs = {String.valueOf(products.getProduct_id())};
        int rowsDeleted = db.delete(PRODUCT_TABLE, whereClause, whereArgs);

        if (rowsDeleted > 0) {
            // update the RecyclerView
            ArrayList<ProductGetSet> updatedList = getEveryProduct();
            ProductFragment.productadapter.notifyDataSetChanged();
            ProductFragment.productadapter.setProducts(updatedList);
            return true;
        } else {
            return false;
        }
    }

    public boolean editProduct(ProductGetSet products) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_ID, products.getProduct_id());
        cv.put(COLUMN_PRODUCT_NAME, products.getProduct_name());
        cv.put(COLUMN_PRODUCT_PRICE, products.getProduct_price());
        cv.put(COLUMN_PRODUCT_QUANTITY, products.getProduct_quantity());
        cv.put(COLUMN_USE_PER_WEEK, products.getUse_per_week());
        String whereClause = PRODUCT_TABLE + " = ?";
        String[] whereArgs = {String.valueOf(products.getProduct_id())};
        int update = db.update(PRODUCT_TABLE, cv, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
            ArrayList<ProductGetSet> updatedList = getEveryProduct();
            ProductFragment.productadapter.notifyDataSetChanged();
            ProductFragment.productadapter.setProducts(updatedList);
            return true;
        }
    }


    public ArrayList<ProductGetSet> getEveryProduct() {

        ArrayList<ProductGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int productID = cursor.getInt(0);
                String productName = cursor.getString(1);
                int productPrice = cursor.getInt(2);
                String productQuantity = cursor.getString(3);
                String usePerWeek = cursor.getString(4);


                ProductGetSet newProducts = new ProductGetSet(productID, productName, productPrice, productQuantity, usePerWeek);
                returnList.add(newProducts);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
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
    public boolean addNestNumber() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NEST_NO", (Integer) null);
        long insert = db.insert("NEST_TABLE", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Integer> getAllNestNumbers() {
        List<Integer> nestNumbers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NEST_TABLE", null);

        if (cursor.moveToFirst()) {
            do {
                nestNumbers.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nestNumbers;

    }


}
