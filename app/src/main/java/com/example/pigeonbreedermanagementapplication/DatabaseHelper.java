package com.example.pigeonbreedermanagementapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pigeonbreedermanagementapplication.Egg.EggsGetSet;
import com.example.pigeonbreedermanagementapplication.HealthCalendar.HCalendarGetSet;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.Product.ProductGetSet;
import com.example.pigeonbreedermanagementapplication.Profile.ProfilesGetSet;
import com.example.pigeonbreedermanagementapplication.Transaction.TransactionGetSet;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String PROFILE_TABLE = "PROFILE_TABLE";
    public static final String COLUMN_PROFILE_NAME = "PROFILE_NAME";
    public static final String COLUMN_PROFILE_ID = "PROFILE_ID";
    public static final String COLUMN_PROFILE_IMAGE = "PROFILE_IMAGE";
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
    public static final String HEALTHCALENDAR_TABLE = "HEALTHCALENDAR_TABLE";
    public static final String COLUMN_HEALTH_ID = "HEALTH_ID";
    public static final String COLUMN_NOTE_DATE = "NOTE_DATE";
    public static final String COLUMN_NOTE_DESCRIPTION = "NOTE_DESCRIPTION";
    public static final String COLUMN_HEALTH_STATUS = "HEALTH_STATUS";
    public static final String COLUMN_NOTE_MEDICATION = "NOTE_MEDICATION";
    public static final String COLUMN_SYMPTOMS_LIST = "SYMPTOMS_LIST";
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

    public static final String DISEASES_TABLE = "DISEASES_TABLE";

    public static final String SYMPTOMS_TABLE = "SYMPTOMS_TABLE";

    public static final String COLUMN_SYMPTOM_DISEASE_ID = "DISEASE_ID";

    public static final String COLUMN_SYMPTOM_ID = "SYMPTOM_ID";

    public static final String COLUMN_SYMPTOM_NAME = "SYMPTOM_NAME";


    public static final String COLUMN_PIGEON_IMAGE = "PIGEON_IMAGE";
    public static final String CAGE_TABLE = "CAGE_TABLE";
    public static final String NEST_TABLE = "NEST_TABLE";
    public static final String COLUMN_EGG_STATUS = "EGG_STATUS";

    private Context context;


    public DatabaseHelper(Context context) {
        super(context, "breedermanagement.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            //Create Table for Profiles
            String createProfileTableStatement = "CREATE TABLE " + PROFILE_TABLE + " (" + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROFILE_NAME + " TEXT, " + COLUMN_PROFILE_IMAGE + " TEXT)";
            db.execSQL(createProfileTableStatement);

            //Create Table for Pigeons
        String createPigeonTableStatement = "CREATE TABLE " + PIGEON_TABLE + " (" +
                COLUMN_RING_ID + " TEXT PRIMARY KEY, " +
                COLUMN_PIGEON_NAME + " TEXT, " +
                COLUMN_CAGE_NO + " TEXT, " +
                COLUMN_PIGEON_BIRTH_YEAR + " INTEGER, " +
                COLUMN_PIGEON_BREED + " TEXT, " +
                COLUMN_PIGEON_GENDER + " TEXT, " +
                COLUMN_PIGEON_COLOR + " TEXT," +
                COLUMN_PIGEON_STATUS + " TEXT, " +
                COLUMN_PIGEON_NOTES + " TEXT, " +
                COLUMN_PIGEON_IMAGE + " TEXT, " +
                COLUMN_PROFILE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_CAGE_NO + ") REFERENCES " + CAGE_TABLE + "(" + COLUMN_CAGE_NO + "), " +
                "FOREIGN KEY (" + COLUMN_PROFILE_ID + ") REFERENCES " + PROFILE_TABLE + "(" + COLUMN_PROFILE_ID + "))";
        db.execSQL(createPigeonTableStatement);

//Create Table for Egg Monitoring
        String createEggTrackerTableStatement = "CREATE TABLE " + EGGMONITORING_TABLE + " (" +
                COLUMN_EGG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CAGE_NO + " INTEGER, " +
                COLUMN_NEST_NO + " INTEGER, " +
                COLUMN_LAYING_DATE + " TEXT, " +
                COLUMN_HATCHING_DATE + " TEXT, " +
                COLUMN_EGG_STATUS + " TEXT, " +
                COLUMN_FATHER + " TEXT, " +
                COLUMN_MOTHER + " TEXT, " +
                COLUMN_PROFILE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_CAGE_NO + ") REFERENCES " + CAGE_TABLE + "(" + COLUMN_CAGE_NO + "), " +
                "FOREIGN KEY (" + COLUMN_NEST_NO + ") REFERENCES " + NEST_TABLE + "(" + COLUMN_NEST_NO + "), " +
                "FOREIGN KEY (" + COLUMN_PROFILE_ID + ") REFERENCES " + PROFILE_TABLE + "(" + COLUMN_PROFILE_ID + "))";
        db.execSQL(createEggTrackerTableStatement);

//Create Table for Health Calendar
        String createHealthCalendarTableStatement = "CREATE TABLE " + HEALTHCALENDAR_TABLE + " (" +
                COLUMN_HEALTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTE_DATE + " TEXT, " +
                COLUMN_RING_ID + " TEXT, " +
                COLUMN_NOTE_DESCRIPTION + " TEXT, " +
                COLUMN_HEALTH_STATUS + " TEXT, " +
                COLUMN_NOTE_MEDICATION + " TEXT, " +
                COLUMN_SYMPTOMS_LIST + " TEXT, " +
                COLUMN_DISEASE_ID + " INTEGER, " +
                COLUMN_PROFILE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_PROFILE_ID + ") REFERENCES " + PROFILE_TABLE + "(" + COLUMN_PROFILE_ID + "), " +
                "FOREIGN KEY (" + COLUMN_DISEASE_ID + ") REFERENCES " + DISEASES_TABLE + "(" + COLUMN_DISEASE_ID + "))";
        db.execSQL(createHealthCalendarTableStatement);

//Create Table for Transactions
        String createTransactionTableStatement = "CREATE TABLE " + TRANSACTION_TABLE + " (" +
                COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TRANSACTION_TYPE + " TEXT, " +
                COLUMN_TRANSACTION_DATE + " TEXT, " +
                COLUMN_TRANSACTION_PARTNER + " TEXT, " +
                COLUMN_TRANSACTION_AMOUNT + " INTEGER, " +
                COLUMN_TRANSACTION_DETAILS + " TEXT, " +
                COLUMN_PROFILE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_PROFILE_ID + ") REFERENCES " + PROFILE_TABLE + "(" + COLUMN_PROFILE_ID + "))";
        db.execSQL(createTransactionTableStatement);

//Create Table for Products
        String createProductTableStatement = "CREATE TABLE " + PRODUCT_TABLE + " (" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_PRODUCT_PRICE + " INTEGER, " +
                COLUMN_PRODUCT_QUANTITY + " TEXT, " +
                COLUMN_USE_PER_WEEK + " TEXT, " +
                COLUMN_PROFILE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_PROFILE_ID + ") REFERENCES " + PROFILE_TABLE + "(" + COLUMN_PROFILE_ID + "))";
        db.execSQL(createProductTableStatement);

//Create Table for Diseases
        String createDiseasesTableStatement = "CREATE TABLE " + DISEASES_TABLE + " (" +
                "DISEASE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DISEASE_IMAGE TEXT NOT NULL, " +
                "DISEASE_NAME TEXT NOT NULL, " +
                "DISEASE_DESCRIPTION TEXT NOT NULL, " +
                "DISEASE_RECOMMENDATION TEXT NOT NULL " + ")";
        db.execSQL(createDiseasesTableStatement);

//Create Table for Symptoms
        String createSymptomsTableStatement = "CREATE TABLE " + SYMPTOMS_TABLE + " (" +
                "SYMPTOM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "SYMPTOM_NAME TEXT NOT NULL, " +
                "DISEASE_ID INTEGER NOT NULL, " +
                "FOREIGN KEY (DISEASE_ID) REFERENCES DISEASES_TABLE(DISEASE_ID)" + ")";
        db.execSQL(createSymptomsTableStatement);

        //Table for Cage
        String createCageTableStatement = "CREATE TABLE " + CAGE_TABLE + " ( " +
                COLUMN_CAGE_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROFILE_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_PROFILE_ID + ") REFERENCES " + PROFILE_TABLE + "(" + COLUMN_PROFILE_ID + "))";
        db.execSQL(createCageTableStatement);


        //Table for Nest
        String createNestTableStatement = "CREATE TABLE " + NEST_TABLE + " ( " +
                COLUMN_NEST_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROFILE_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_PROFILE_ID + ") REFERENCES " + PROFILE_TABLE + "(" + COLUMN_PROFILE_ID + "))";
        db.execSQL(createNestTableStatement);
//        String initializeNestTableStatement = "INSERT INTO " + NEST_TABLE + " (" +
//                COLUMN_NEST_NO + ") VALUES (1)";
//
//        db.execSQL(initializeNestTableStatement);

        String insertDiseaseStatement = "INSERT INTO DISEASES_TABLE (DISEASE_IMAGE, DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('image_pigeon_canker', 'PIGEON CANKER', 'Trichomoniasis (pigeon canker) is the most common disease of pigeons. Approximately 80 percent of pigeons are infected with this organism. The organism is a microscopic flagellate classified as a protozoan. Different strains, Trichomonas gallinae or Trichomonas columbae, vary greatly in their ability to cause disease. The disease occurs worldwide in warm climates or during warm weather. It may occur at any time of the year in commercial squab operations. Adult pigeons frequently carry the trichomonads without showing signs of disease. When the adult pigeon is stressed, however, the organisms may multiply profusely. A mild infection can then turn into a serious condition. Stresses include other diseases, parasitic infestations, or overbreeding.', 'Birds can be treated with Ronidazole (Ronnivet-S) in the water for seven days. It has a wide safety margin. Regular re-treatments are advised.\n Pigeons can be treated in a single dose of Carnidazole (Spartrix) and Metronidazole (Flagyl), has been also used in the past orally for 2-10 days. All of these drugs are prescription only, not for sale over the counter.')";
        db.execSQL(insertDiseaseStatement);

        String insertDiseaseStatement2 = "INSERT INTO DISEASES_TABLE (DISEASE_IMAGE, DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('image_pigeon_worm','PIGEON WORM', 'There are 3 common worm infestation in pigeons which are: \n\n1. Roundworms (Ascaris) - Roundworms form the most common worm infestation in pigeons. They are present in the small intestine of the pigeon and in serious cases they can be present in such large quantities that the intestine is almost completely blocked. There are few external symptoms in case of a relatively light infection. Only the racing results will be disappointing in such cases due to the weakened condition. \n\n2. Hairworms(Capillaria) - The hairworms are the smallest (not even visible to the eye), but most annoying type of worms. Just like the roundworm, they reside in the small intestine, but they bore their way into the intestinal wall and the blood vessels in the intestinal wall. This causes an inflammation of the intestines and the pigeons lose weight fast and get sick. \n\n3. Tapeworm - Usually, we see a pigeon with something like a grain of rice hanging from its hindquarters. This is a link of a tapeworm and in many cases, when you pull it very carefully, you can pull out a tapeworm of 30 to 50 cm in length. However, there are usually several tapeworms present in the body and treatment is recommended.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement2);

        String insertDiseaseStatement3 = "INSERT INTO DISEASES_TABLE (DISEASE_IMAGE, DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('image_pigeon_coccidia','PIGEON COCCIDIA', 'Coccidia are a group of parasitic organisms that have the amazing ability to reproduce themselves both sexually and asexually in various organs throughout the body. There are lots of different types. Some reproduce in the kidney, others in the liver, some are carried throughout the body in red blood cells, but the common one that infects racing pigeons affects the bowel.\n\nBasically what happens is that the organism releases eggs that come out in the droppings. These have to sit in the environment for at least a couple of days to become infective. They do however become infective quicker in damp conditions. Once infective, if a pigeon accidentally swallows one of these eggs, they move down into the bowel and hatch. In the common type of coccidia in pigeons four “larvae” come out of each egg. These then burrow into the bowel wall where initially they reproduce asexually –essentially they just keep dividing so that two become four become eight etc. After a while these larvae differentiate into males and females. These then reproduce sexually resulting in the formation of eggs. These eggs then rupture back into the bowel before passing out of the body in the droppings. In this way the lifecycle is completed.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement3);

        String insertDiseaseStatement4 = "INSERT INTO DISEASES_TABLE (DISEASE_IMAGE, DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('image_pigeon_hexamita','PIGEON HEXAMITA', 'Hexamita meleagridis (pigeons H. columbae) is a protozoan parasite of turkeys, pheasants, pigeons, and some game birds. It is transmitted by faeces, fomites, carriers. Inter-species transmission may occur. In commercial ducks a related parasite Tetratrichomonas can cause poor growth and drops in egg production.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement4);

        String insertDiseaseStatement5 = "INSERT INTO DISEASES_TABLE (DISEASE_IMAGE, DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('image_pigeon_mycoplasma','PIGEON MYCOPLASMA', 'Mycoplasma gallisepticum causes respiratory infections in chickens, turkeys, and other avian species. Morbidity is typically high and mortality low in affected flocks, and signs are generally more severe in turkeys. Real-time PCR is becoming the most common test used for diagnosis. Antibiotics may reduce clinical signs and transmission through eggs, but they do not eliminate infection. Control is achieved by good biosecurity and sourcing stock from M gallisepticum-free breeder flocks.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
        db.execSQL(insertDiseaseStatement5);

        String insertDiseaseStatement6 = "INSERT INTO DISEASES_TABLE (DISEASE_IMAGE, DISEASE_NAME, DISEASE_DESCRIPTION, DISEASE_RECOMMENDATION) VALUES ('image_pigeon_resp','PIGEON RESPIRATORY INFECTION', 'Clinical respiratory infection in pigeons is the end result of the interplay of a number of factors, but the type of infective organisms involved and the vulnerability of the birds to infection are particularly important. \n\nThe usual organisms involved are Mycoplasma, Chlamydia and a range of bacteria (most commonly, E. coli). Whether or not these organisms actually cause disease in a pigeon, if it is exposed, essentially depends on how well the pigeon is at the time of exposure and also its age and level of immunity. Any factors that cause physiological stress can weaken the bird and make it more vulnerable to developing a respiratory infection. As a general rule, younger pigeons are more susceptible. However, in a bird that is otherwise healthy exposure to the agents that cause respiratory infection does not necessarily make it sick. Exposure to these organisms as the young pigeons grow and develop, rather than cause disease, stimulates immunity to form in a bird that is otherwise healthy.', 'TEST RECOM LINE 1 \n\t TEST RECOM LINE 2')";
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
        db.insert(DISEASES_TABLE, null, values);
        db.close();
    }

    public ArrayList<Disease> getAllDisease(){
        ArrayList<Disease> diseaseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DISEASES_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{
                Disease disease = new Disease();
                disease.setId(cursor.getInt(0));
                disease.setImage(cursor.getString(1));
                disease.setName(cursor.getString(2));
                disease.setDesc(cursor.getString(3));
                diseaseList.add(disease);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return diseaseList;
    }

    public ArrayList<Symptom> getAllSymptoms(){
        ArrayList<Symptom> symptomsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + SYMPTOMS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{

                int sympid = cursor.getInt(0);
                String sympname = cursor.getString(1);

                Symptom symptom = new Symptom(sympid, sympname);
                symptomsList.add(symptom);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return symptomsList;
    }

    public List<Symptom> getDiseaseSymptoms(int diseaseId) {
        List<Symptom> symptoms = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_SYMPTOM_ID + ", " + COLUMN_SYMPTOM_NAME +
                " FROM " + SYMPTOMS_TABLE +
                " WHERE " + COLUMN_SYMPTOM_DISEASE_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(diseaseId)});

        if (cursor != null && cursor.moveToFirst()) {
            int symptomIdIndex = cursor.getColumnIndexOrThrow(COLUMN_SYMPTOM_ID);
            int symptomNameIndex = cursor.getColumnIndexOrThrow(COLUMN_SYMPTOM_NAME);

            do {
                int symptomId = cursor.getInt(symptomIdIndex);
                String symptomName = cursor.getString(symptomNameIndex);

                Symptom symptom = new Symptom(symptomId, symptomName);
                symptoms.add(symptom);
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            Log.d("DatabaseHelper", "No symptoms found for diseaseId: " + diseaseId);
        }

        // Debug statement to log the diseaseId
        Log.d("DatabaseHelper", "Disease ID: " + diseaseId);

        return symptoms;
    }



    ///////////////////////PROFILES//////////////////////////////
    public boolean isProfileInitialized(int profileId, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " WHERE " + COLUMN_PROFILE_ID + " = " + profileId;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {  // if cursor moves to first, that means record exists
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


    public boolean addProfile(ProfilesGetSet profiles) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PROFILE_NAME, profiles.getName());
        cv.put(COLUMN_PROFILE_IMAGE, profiles.getImage());

        long insert = db.insert(PROFILE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }


        return true;
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

    public ArrayList<ProfilesGetSet> getEveryProfile() {

        ArrayList<ProfilesGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + PROFILE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int userProfileID = cursor.getInt(0);
                String userProfileName = cursor.getString(1);
                String userProfileImage = cursor.getString(2);

                ProfilesGetSet newProfile = new ProfilesGetSet(userProfileID, userProfileName, userProfileImage);
                returnList.add(newProfile);

            } while (cursor.moveToNext());

        } else {

        }
        return returnList;
    }

    ///////////////////////PIGEONS//////////////////////////////
    public int addPigeon(PigeonsGetSet pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String ringId = pigeons.getRing_id();

        if (ringId.equals("")) {
            return 3; // Error code for empty Ring ID
        }

        if (checkRingIdExists(ringId)) {
            return 2; // Error code for duplicate Ring ID
        }
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
        cv.put(COLUMN_PROFILE_ID, pigeons.getProfile_id()); // Save the profile ID


        long insert = db.insert(PIGEON_TABLE, null, cv);


        if (insert == -1) {
            return 0;
        } else {

            return 1;
        }
    }

    public boolean deletePigeon(PigeonsGetSet pigeons) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_RING_ID + " = ?";
        String[] whereArgs = {pigeons.getRing_id()};
        int rowsDeleted = db.delete(PIGEON_TABLE, whereClause, whereArgs);

        if (rowsDeleted > 0) {
            // update the RecyclerView
//            ArrayList<PigeonsGetSet> updatedList = getEveryPigeon();
//            PigeonsFragment.pigeonadapter.setPigeons(updatedList);
//            PigeonsFragment.pigeonadapter.notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    public boolean editPigeon(PigeonsGetSet pigeons) {
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
        cv.put(COLUMN_PROFILE_ID, pigeons.getProfile_id());
        String whereClause = COLUMN_RING_ID + " = ?";
        String[] whereArgs = {pigeons.getRing_id()};
        int update = db.update(PIGEON_TABLE, cv, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
//            ArrayList<PigeonsGetSet> updatedList = getEveryPigeon(pigeons.getProfile_id());
//            PigeonsFragment.pigeonadapter.setPigeons(updatedList);
//            PigeonsFragment.pigeonadapter.notifyDataSetChanged();
            return true;
        }
    }


    public ArrayList<PigeonsGetSet> getEveryPigeon(int profileid) {

        ArrayList<PigeonsGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + PIGEON_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileid;
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
                int profileId = cursor.getInt(10);

                PigeonsGetSet newPigeons = new PigeonsGetSet(ringID, pigeonName, cageNumber, pigeonBirthYear, pigeonBreed, pigeonGender, pigeonColor, pigeonStatus, pigeonNotes, pigeonImage, profileId);
                returnList.add(newPigeons);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        return returnList;
    }
    public int getTotalPigeons(int profileid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT COUNT(*) FROM " + PIGEON_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(profileid) };

        Cursor cursor = db.rawQuery(queryString, selectionArgs);

        int totalPigeons = 0;
        if (cursor.moveToFirst()) {
            totalPigeons = cursor.getInt(0);
        }

        cursor.close();
        return totalPigeons;
    }

    private boolean checkRingIdExists(String ringId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PIGEON_TABLE, new String[] {COLUMN_RING_ID}, COLUMN_RING_ID + "=?", new String[] {ringId}, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
    public List<String> getAllRingIds(int profileid) {
        List<String> ringIds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PIGEON_TABLE, new String[] { COLUMN_RING_ID }, COLUMN_PROFILE_ID + " = ?", new String[] { String.valueOf(profileid) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ringIds.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ringIds;
    }

    public List<String> getAllRingMale(int profileid) {
        List<String> ringIdsMale = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PIGEON_TABLE, new String[] { COLUMN_RING_ID }, COLUMN_PROFILE_ID + " = ? AND " + COLUMN_PIGEON_GENDER + " = ?", new String[] { String.valueOf(profileid), "Male" }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ringIdsMale.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ringIdsMale;
    }

    public List<String> getAllRingFemale(int profileid) {
        List<String> ringIdsFemale = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PIGEON_TABLE, new String[] { COLUMN_RING_ID }, COLUMN_PROFILE_ID + " = ? AND " + COLUMN_PIGEON_GENDER + " = ?", new String[] { String.valueOf(profileid), "Female" }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ringIdsFemale.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ringIdsFemale;
    }

    ///////////////////////EGGS//////////////////////////////
    public boolean addEgg(EggsGetSet eggs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CAGE_NO, eggs.getCage_number());
        cv.put(COLUMN_NEST_NO, eggs.getNest_number());
        cv.put(COLUMN_LAYING_DATE, eggs.getLaying_date());
        cv.put(COLUMN_HATCHING_DATE, eggs.getHatching_date());
        cv.put(COLUMN_EGG_STATUS, eggs.getEgg_status());
        cv.put(COLUMN_FATHER, eggs.getFather());
        cv.put(COLUMN_MOTHER, eggs.getMother());
        cv.put(COLUMN_PROFILE_ID, eggs.getProfile_id());


        long insert = db.insert(EGGMONITORING_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {
//            ArrayList<EggsGetSet> updatedList = getEveryEgg();
//            EggTrackerFragment.eggadapter.setEggs(updatedList);
            return true;
        }

    }

    public ArrayList<EggsGetSet> getEveryEgg(int profileid) {

        ArrayList<EggsGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileid;
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
                String eggStatus = cursor.getString(5);
                String father = cursor.getString(6);
                String mother = cursor.getString(7);
                int profileId = cursor.getInt(8);

                EggsGetSet newEggs = new EggsGetSet(eggID, cageNumber, nestNumber, layDate, hatchDate, eggStatus, father, mother, profileId);
                returnList.add(newEggs);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<EggsGetSet> getLaidEgg(int profileid) {

        ArrayList<EggsGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileid + " AND " + COLUMN_EGG_STATUS + " =?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, new String[]{"Laid"});

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int eggID = cursor.getInt(0);
                int cageNumber = cursor.getInt(1);
                int nestNumber = cursor.getInt(2);
                String layDate = cursor.getString(3);
                String hatchDate = cursor.getString(4);
                String eggStatus = cursor.getString(5);
                String father = cursor.getString(6);
                String mother = cursor.getString(7);
                int profileId = cursor.getInt(8);

                EggsGetSet newEggs = new EggsGetSet(eggID, cageNumber, nestNumber, layDate, hatchDate, eggStatus, father, mother, profileId);
                returnList.add(newEggs);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<EggsGetSet> getHatchedEgg(int profileid) {

        ArrayList<EggsGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileid + " AND " + COLUMN_EGG_STATUS + " =?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, new String[]{"Hatched"});

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int eggID = cursor.getInt(0);
                int cageNumber = cursor.getInt(1);
                int nestNumber = cursor.getInt(2);
                String layDate = cursor.getString(3);
                String hatchDate = cursor.getString(4);
                String eggStatus = cursor.getString(5);
                String father = cursor.getString(6);
                String mother = cursor.getString(7);
                int profileId = cursor.getInt(8);

                EggsGetSet newEggs = new EggsGetSet(eggID, cageNumber, nestNumber, layDate, hatchDate, eggStatus, father, mother, profileId);
                returnList.add(newEggs);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<EggsGetSet> getUnhatchedEgg(int profileid) {

        ArrayList<EggsGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileid + " AND " + COLUMN_EGG_STATUS + " =?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, new String[]{"Unhatched"});

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int eggID = cursor.getInt(0);
                int cageNumber = cursor.getInt(1);
                int nestNumber = cursor.getInt(2);
                String layDate = cursor.getString(3);
                String hatchDate = cursor.getString(4);
                String eggStatus = cursor.getString(5);
                String father = cursor.getString(6);
                String mother = cursor.getString(7);
                int profileId = cursor.getInt(8);

                EggsGetSet newEggs = new EggsGetSet(eggID, cageNumber, nestNumber, layDate, hatchDate, eggStatus, father, mother, profileId);
                returnList.add(newEggs);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean updateEggStatus(int eggId, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EGG_STATUS, newStatus);
        String whereClause = COLUMN_EGG_ID + " = ?";
        String[] whereArgs = { String.valueOf(eggId) };
        int rowsUpdated = db.update(EGGMONITORING_TABLE, values, whereClause, whereArgs);
        return rowsUpdated > 0;
    }

    public int getHatchedEggCount(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileId + " AND " + COLUMN_EGG_STATUS + " = 'Hatched'";
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getBotchedEggCount(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileId + " AND " + COLUMN_EGG_STATUS + " = 'Unhatched'";
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getLaidEggCount(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileId + " AND " + COLUMN_EGG_STATUS + " = 'Laid'";
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getEggCount(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + EGGMONITORING_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileId;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getBuyTotal(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT SUM(TRANSACTION_AMOUNT) FROM " + TRANSACTION_TABLE + " WHERE " +COLUMN_PROFILE_ID  + " = ? AND " + COLUMN_TRANSACTION_TYPE + "= ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(profileId), "Buy"});
        int totalAmount = 0;
        if (cursor.moveToFirst()) {
            totalAmount = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return totalAmount;
    }

    public int getSellTotal(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT SUM(TRANSACTION_AMOUNT) FROM " + TRANSACTION_TABLE + " WHERE " +COLUMN_PROFILE_ID  + " = ? AND " + COLUMN_TRANSACTION_TYPE + "= ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(profileId), "Sell"});
        int totalAmount = 0;
        if (cursor.moveToFirst()) {
            totalAmount = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return totalAmount;
    }

    public int getCageCount(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + CAGE_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileId;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getNestCount(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"COUNT(DISTINCT NEST_NO) AS count"};
        String selection = "PROFILE_ID=?";
        String[] selectionArgs = {String.valueOf(profileId)};
        String groupBy = "CAGE_NO";
        Cursor cursor = db.query("EGGMONITORING_TABLE", columns, selection, selectionArgs, groupBy, null, null);
        int totalNests = 0;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("count");
            if (columnIndex != -1) {
                do {
                    totalNests += cursor.getInt(columnIndex);
                } while (cursor.moveToNext());
            } else {
                // Handle error: column not found
            }
        }
        cursor.close();
        return totalNests;
    }

    public int getCageCurrent(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT COUNT(DISTINCT CAGE_NO) FROM " + PIGEON_TABLE + " WHERE "+ COLUMN_PROFILE_ID +" = ? AND "+ COLUMN_PIGEON_STATUS +" = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(profileId), "Alive"});
        int currentCage = 0;
        if (cursor.moveToFirst()) {
            currentCage = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return currentCage;
    }

    public int countNestsPerCage(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"COUNT(DISTINCT NEST_NO) AS count"};
        String selection = "PROFILE_ID=? AND EGG_STATUS IN (?, ?)";
        String[] selectionArgs = {String.valueOf(profileId), "Laid", "Hatched"};
        String groupBy = "CAGE_NO";
        Cursor cursor = db.query("EGGMONITORING_TABLE", columns, selection, selectionArgs, groupBy, null, null);
        int totalNests = 0;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("count");
            if (columnIndex != -1) {
                do {
                    totalNests += cursor.getInt(columnIndex);
                } while (cursor.moveToNext());
            } else {
                // Handle error: column not found
            }
        }
        cursor.close();
        return totalNests;
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
        cv.put(COLUMN_PROFILE_ID, transactions.getProfile_id());


        long insert = db.insert(TRANSACTION_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {

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
//            ArrayList<TransactionGetSet> updatedList = getEveryTransaction();
//            TransactionFragment.transactionadapter.notifyDataSetChanged();
//            TransactionFragment.transactionadapter.setTransactions(updatedList);
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
        cv.put(COLUMN_PROFILE_ID, transactions.getProfile_id());
        String whereClause = COLUMN_TRANSACTION_ID + " = ?";
        String[] whereArgs = {String.valueOf(transactions.getTransaction_id())};
        int update = db.update(TRANSACTION_TABLE, cv, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
//            ArrayList<TransactionGetSet> updatedList = getEveryTransaction();
//            TransactionFragment.transactionadapter.notifyDataSetChanged();
//            TransactionFragment.transactionadapter.setTransactions(updatedList);
            return true;
        }
    }


    public ArrayList<TransactionGetSet> getEveryTransaction(int profileid) {

        ArrayList<TransactionGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileid;
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
                int profileId = cursor.getInt(6);


                TransactionGetSet newTransactions = new TransactionGetSet(transactionID, transactionType, transactionDate, transactionPartner, transactionAmount, transactionDetails, profileId);
                returnList.add(newTransactions);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        return returnList;
    }
    //////////////////////PRODUCTS/////////////////////////

    public boolean addProduct(ProductGetSet products) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT_NAME, products.getProduct_name());
        cv.put(COLUMN_PRODUCT_PRICE, products.getProduct_price());
        cv.put(COLUMN_PRODUCT_QUANTITY, products.getProduct_quantity());
        cv.put(COLUMN_USE_PER_WEEK, products.getUse_per_week());
        cv.put(COLUMN_PROFILE_ID, products.getProfile_id());



        long insert = db.insert(PRODUCT_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {

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
//            ArrayList<ProductGetSet> updatedList = getEveryProduct();
//            ProductFragment.productadapter.notifyDataSetChanged();
//            ProductFragment.productadapter.setProducts(updatedList);
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
        cv.put(COLUMN_PROFILE_ID, products.getProfile_id());
        String whereClause = PRODUCT_TABLE + " = ?";
        String[] whereArgs = {String.valueOf(products.getProduct_id())};
        int update = db.update(PRODUCT_TABLE, cv, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
//            ArrayList<ProductGetSet> updatedList = getEveryProduct();
//            ProductFragment.productadapter.notifyDataSetChanged();
//            ProductFragment.productadapter.setProducts(updatedList);
            return true;
        }
    }


    public ArrayList<ProductGetSet> getEveryProduct(int profileid) {

        ArrayList<ProductGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + PRODUCT_TABLE + " WHERE " + COLUMN_PROFILE_ID + " = " + profileid;
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
                int profileId = cursor.getInt(5);


                ProductGetSet newProducts = new ProductGetSet(productID, productName, productPrice, productQuantity, usePerWeek, profileId);
                returnList.add(newProducts);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        return returnList;
    }

    public void initializeTable(int profileId, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFILE_ID, profileId);
        db.insert(tableName, null, values);
        db.close();
    }
    public boolean addCageNumber(int profileid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CAGE_NO, (Integer) null);
        cv.put(COLUMN_PROFILE_ID, profileid);
        long insert = db.insert(CAGE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Integer> getAllCageNumbers(int profileId) {
        List<Integer> cageNumbers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COLUMN_CAGE_NO };
        String selection = COLUMN_PROFILE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(profileId) };
        Cursor cursor = db.query(CAGE_TABLE, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                cageNumbers.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cageNumbers;
    }

    public boolean addNestNumber(int profileid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NEST_NO, (Integer) null);
        cv.put(COLUMN_PROFILE_ID, profileid);
        long insert = db.insert(NEST_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Integer> getAllNestNumbers(int profileid) {
        List<Integer> nestNumbers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_PROFILE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(profileid)};
        Cursor cursor = db.query(NEST_TABLE, new String[]{COLUMN_NEST_NO}, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                nestNumbers.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nestNumbers;

    }


    public ArrayList<String> getEverySymptomNames() {
        ArrayList<String> returnList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT SYMPTOM_NAME FROM " + SYMPTOMS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                String symptomName = cursor.getString(0);
                returnList.add(symptomName);
            } while (cursor.moveToNext());
        }

        return returnList;
    }

    public ArrayList<String> getEveryDiseaseNames() {
        ArrayList<String> returnList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT DISEASE_NAME FROM " + DISEASES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                String diseaseName = cursor.getString(0);
                returnList.add(diseaseName);
            } while (cursor.moveToNext());
        }

        return returnList;
    }

    ///////////////////////HEALTH CALENDAR//////////////////////////////
    public boolean addHealth(HCalendarGetSet hcalendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTE_DATE, hcalendar.getNote_date());
        cv.put(COLUMN_RING_ID, hcalendar.getRing_id());
        cv.put(COLUMN_NOTE_DESCRIPTION, hcalendar.getNote_description());
        cv.put(COLUMN_HEALTH_STATUS, hcalendar.getHealth_status());
        cv.put(COLUMN_NOTE_MEDICATION, hcalendar.getNote_medication());
        cv.put(COLUMN_SYMPTOMS_LIST, hcalendar.getSymptoms_list());
        cv.put(COLUMN_DISEASE_ID, hcalendar.getDisease_id());
        cv.put(COLUMN_PROFILE_ID, hcalendar.getProfile_id());


        long insert = db.insert(HEALTHCALENDAR_TABLE, null, cv);


        if (insert == -1) {
            return false;
        } else {

            return true;
        }
    }

    public boolean deleteHealth(HCalendarGetSet hcalendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_HEALTH_ID + " = ?";
        String[] whereArgs = {String.valueOf(hcalendar.getHealth_id())};
        int rowsDeleted = db.delete(HEALTHCALENDAR_TABLE, whereClause, whereArgs);

        if (rowsDeleted > 0) {

            return true;
        } else {
            return false;
        }
    }

    public boolean editHealth(HCalendarGetSet hcalendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_HEALTH_ID, hcalendar.getHealth_id());
        cv.put(COLUMN_NOTE_DATE, hcalendar.getNote_date());
        cv.put(COLUMN_RING_ID, hcalendar.getRing_id());
        cv.put(COLUMN_NOTE_DESCRIPTION, hcalendar.getNote_description());
        cv.put(COLUMN_HEALTH_STATUS, hcalendar.getHealth_status());
        cv.put(COLUMN_NOTE_MEDICATION, hcalendar.getNote_medication());
        cv.put(COLUMN_SYMPTOMS_LIST, hcalendar.getSymptoms_list());
        cv.put(COLUMN_DISEASE_ID, hcalendar.getDisease_id());
        cv.put(COLUMN_PROFILE_ID, hcalendar.getProfile_id());
        String whereClause = COLUMN_HEALTH_ID + " = ?";
        String[] whereArgs = {String.valueOf(hcalendar.getHealth_id())};
        int update = db.update(HEALTHCALENDAR_TABLE, cv, whereClause, whereArgs);
        if (update == 0) {
            return false;
        } else {
            return true;
        }
    }


    public ArrayList<HCalendarGetSet> getEveryHealth(int profileid) {

        ArrayList<HCalendarGetSet> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + HEALTHCALENDAR_TABLE + " WHERE " + COLUMN_HEALTH_ID + " = " + profileid;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor, put them in return list
            do {
                int healthID = cursor.getInt(0);
                String noteDate = cursor.getString(1);
                String ringID = cursor.getString(2);
                String noteDescription = cursor.getString(3);
                String healthStatus = cursor.getString(4);
                String noteMedication = cursor.getString(5);
                String symptomsList = cursor.getString(6);
                int diseaseID = cursor.getInt(7);
                int profileID = cursor.getInt(8);



                HCalendarGetSet newHealth = new HCalendarGetSet(healthID, noteDate, ringID, noteDescription, healthStatus, noteMedication, symptomsList,diseaseID,profileID);
                returnList.add(newHealth);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        return returnList;
    }

    public HCalendarGetSet getHealthData(String date, String ring_id) {
        HCalendarGetSet healthData = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT * FROM " + HEALTHCALENDAR_TABLE
                + " WHERE " + COLUMN_NOTE_DATE + " = ? AND "
                + COLUMN_RING_ID + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[] {date, ring_id});

        if (cursor.moveToFirst()) {
            int healthID = cursor.getInt(0);
            String noteDate = cursor.getString(1);
            String ringID = cursor.getString(2);
            String noteDescription = cursor.getString(3);
            String healthStatus = cursor.getString(4);
            String noteMedication = cursor.getString(5);
            String symptomsList = cursor.getString(6);
            int diseaseID = cursor.getInt(7);
            int profileID = cursor.getInt(8);

            healthData = new HCalendarGetSet(healthID, noteDate, ringID, noteDescription, healthStatus, noteMedication, symptomsList, diseaseID, profileID);
        }

        cursor.close();
        return healthData;
    }


    public int getDiseaseIdByName(String diseaseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int diseaseId = -1;

        // Define the table name and column names
        String tableName = DISEASES_TABLE;
        String[] columns = {COLUMN_DISEASE_ID};

        // Specify the selection criteria
        String selection = "DISEASE_NAME = ?";
        String[] selectionArgs = {diseaseName};

        // Execute the query
        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("DISEASE_ID");
            if (columnIndex != -1) {
                // Retrieve the disease ID from the cursor
                diseaseId = cursor.getInt(columnIndex);
            }
        }
        cursor.close();
        return diseaseId;
    }
    public String getDiseaseNameById(int diseaseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String diseaseName = "";

        // Define the table name and column names
        String tableName = DISEASES_TABLE;
        String[] columns = {COLUMN_DISEASE_NAME};

        // Specify the selection criteria
        String selection = "DISEASE_ID = ?";
        String[] selectionArgs = {String.valueOf(diseaseId)};

        // Execute the query
        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("DISEASE_NAME");
            if (columnIndex != -1) {
                // Retrieve the disease name from the cursor
                diseaseName = cursor.getString(columnIndex);
            }
        }
        cursor.close();
        return diseaseName;
    }

}
