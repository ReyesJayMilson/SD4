package com.example.pigeonbreedermanagementapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class CommonDiseaseLibFragment extends Fragment {
    private RecyclerView recyclerView;

    private Spinner spinSymptom;
    ArrayList<Disease> diseaseList;
    List<Symptom> symptomList;
    private DiseaseAdapter adapter;

    private DatabaseHelper databaseHelper;

    private static final String SYMPTOMS_TABLE = "SYMPTOMS_TABLE";
    private static final String COLUMN_SYMP_NAME = "SYMPTOM_NAME";

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_disease_lib, container, false);

        spinSymptom = view.findViewById(R.id.spinSymptom);
        databaseHelper = new DatabaseHelper(getActivity());
        diseaseList = databaseHelper.getAllDisease();

        // Fetch all the symptom_name from SymptomsTable and ignore duplicates
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT DISTINCT " + COLUMN_SYMP_NAME + " FROM " + SYMPTOMS_TABLE, null);
        List<String> symptomNames = new ArrayList<>();
        while (cursor.moveToNext()) {
            symptomNames.add(cursor.getString(cursor.getColumnIndex(COLUMN_SYMP_NAME)));
        }
        cursor.close();

        // Add the symptom names to the spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, symptomNames);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSymptom.setAdapter(adapter1);

        recyclerView = view.findViewById(R.id.Recycler_Library);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DiseaseAdapter(diseaseList);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }
}