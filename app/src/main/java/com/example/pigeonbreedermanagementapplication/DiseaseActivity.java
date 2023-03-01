package com.example.pigeonbreedermanagementapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class DiseaseActivity extends AppCompatActivity {

    private static final String SYMPTOMS_TABLE = "SYMPTOMS_TABLE";
    private static final String COLUMN_SYMP_ID = "SYMPTOM_ID";
    private static final String COLUMN_SYMP_NAME = "SYMPTOM_NAME";
    private static final String COLUMN_DIS_ID = "DISEASE_ID";
    public int diseaseId;
    TextView disName, disDesc, disSymptom;
    private List<Symptom> symptomList;

    private List<Boolean> symptomChecked;
    private DatabaseHelper databaseHelper;

    public DiseaseActivity() {
        // Empty constructor
    }

    private void add(Symptom symptom) {
        symptomList.add(symptom);
    }

    public DiseaseActivity(List<Symptom> symptomList) {
        this.symptomList = symptomList;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common_disease_lib);

        disName = findViewById(R.id.disName);
        disDesc = findViewById(R.id.disDesc);
        disSymptom = findViewById(R.id.disSymptom);
        databaseHelper = new DatabaseHelper(this);


        Intent intent = getIntent();
        int diseaseId = intent.getIntExtra("disease_id", 0);

        symptomList = getSymptomsForDisease(diseaseId);
        String symptoms = "";
        for (Symptom symptom : symptomList) {
            symptoms += "- " + symptom.getSymptomName() + "\n";
        }
        if (symptoms.isEmpty()) {
            disSymptom.setText("No symptoms found for this disease");
        } else {
            disSymptom.setText(symptoms);
        }

        String diseaseName = intent.getStringExtra("disease_name");
        disName.setText(diseaseName);

        String diseaseDesc = intent.getStringExtra("disease_desc");
        disDesc.setText(diseaseDesc);


    }

    private List<Symptom> getSymptomsForDisease(int diseaseId) {
        List<Symptom> symptomList = new ArrayList<>();

        // Define a query to retrieve the symptoms for the selected disease
        String query = "SELECT * FROM " + SYMPTOMS_TABLE + " WHERE " + COLUMN_DIS_ID + " = " + diseaseId;

        // Open the database for reading
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        // Execute the query and retrieve the result set
        Cursor cursor = database.rawQuery(query, null);

        // Check if the query returned the expected columns
        String[] columns = cursor.getColumnNames();
        for (String column : columns) {
            if (!column.equals(COLUMN_SYMP_ID) && !column.equals(COLUMN_SYMP_NAME) && !column.equals(COLUMN_DIS_ID)) {
                throw new IllegalStateException("Unexpected column in result set: " + column);
            }
        }

        // Iterate over the result set and add each symptom to the list
        if (cursor.moveToFirst()) {
            do {
                // Check that the column indices being requested are within the valid range
                int symptomIdIndex = cursor.getColumnIndex(COLUMN_SYMP_ID);
                if (symptomIdIndex == -1) {
                    throw new IllegalStateException("Column " + COLUMN_SYMP_ID + " not found in result set");
                }
                int symptomNameIndex = cursor.getColumnIndex(COLUMN_SYMP_NAME);
                if (symptomNameIndex == -1) {
                    throw new IllegalStateException("Column " + COLUMN_SYMP_NAME + " not found in result set");
                }

                // Retrieve the values from the result set
                int symptomId = cursor.getInt(symptomIdIndex);
                String symptomName = cursor.getString(symptomNameIndex);

                // Create a symptom object and add it to the list
                Symptom symptom = new Symptom(symptomId, symptomName);
                symptomList.add(symptom);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        return symptomList;
    }
}
