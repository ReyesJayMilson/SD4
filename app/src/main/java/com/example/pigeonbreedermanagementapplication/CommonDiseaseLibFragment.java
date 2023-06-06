package com.example.pigeonbreedermanagementapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CommonDiseaseLibFragment extends Fragment {
    private RecyclerView recyclerView;

    private Spinner spinSymptom;
    private TextView txtSelectedSymptoms;
    ArrayList<Disease> diseaseList;
    ArrayList<String> symptomNameList;
    private DiseaseAdapter adapter;

    private DatabaseHelper databaseHelper;

    private SearchView searchView;

    private ImageView imageView;
    private Set<String> selectedSymptoms = new HashSet<>();
    private ArrayList<Disease> filteredDiseases = new ArrayList<>();
    private boolean isFirstSelection = true;


    private static final String SYMPTOMS_TABLE = "SYMPTOMS_TABLE";
    private static final String COLUMN_SYMP_NAME = "SYMPTOM_NAME";


    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true); // enable the fragment to contribute items to the options menu

        View view = inflater.inflate(R.layout.fragment_common_disease_lib, container, false);


        databaseHelper = new DatabaseHelper(getActivity());
        diseaseList = databaseHelper.getAllDisease();

        recyclerView = view.findViewById(R.id.Recycler_Library);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DiseaseAdapter(diseaseList, getActivity());
        recyclerView.setAdapter(adapter);

        spinSymptom = view.findViewById(R.id.spinSymptom);
        symptomNameList = databaseHelper.getEverySymptomNames();
        symptomNameList.add(0, "Select Symptom");
        ArrayAdapter<String> symptomAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, symptomNameList);
        symptomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSymptom.setAdapter(symptomAdapter);

        txtSelectedSymptoms = view.findViewById(R.id.txtSelectedSymptoms);

        spinSymptom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Do nothing for the dummy item
                    return;
                }
                String selectedSymptom = parent.getItemAtPosition(position).toString();
                if (!selectedSymptoms.contains(selectedSymptom)) {
                    selectedSymptoms.add(selectedSymptom);
                    txtSelectedSymptoms.setVisibility(View.VISIBLE);
                    updateSelectedSymptomsTextView();
                    adapter.setDisease(filter(diseaseList, searchView.getQuery().toString()));
                }
                spinSymptom.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        txtSelectedSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] symptomsArray = selectedSymptoms.toArray(new String[0]);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Remove Symptom")
                        .setItems(symptomsArray, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedSymptom = symptomsArray[which];
                                selectedSymptoms.remove(selectedSymptom);
                                updateSelectedSymptomsTextView();
                                if (selectedSymptoms.isEmpty()) {
                                    txtSelectedSymptoms.setVisibility(View.GONE);
                                }
                                adapter.setDisease(filter(diseaseList, searchView.getQuery().toString()));
                            }
                        });
                builder.create().show();
            }
        });



        // Inflate the layout for this fragment
        return view;


    }

    private void updateSelectedSymptomsTextView() {
        StringBuilder sb = new StringBuilder("Selected Symptoms: ");
        for (String symptom : selectedSymptoms) {
            sb.append(symptom).append(", ");
        }
        // Remove the last comma and space
        if (sb.length() > 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        txtSelectedSymptoms.setText(sb.toString());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actions_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // filter the list with the search query
                ArrayList<Disease> filteredDisease = filter(diseaseList, newText);
                adapter.setDisease(filteredDisease);
                return true;
            }
        });





    }

    private ArrayList<Disease> filter(ArrayList<Disease> diseases, String query) {
        ArrayList<Disease> filteredDiseases = new ArrayList<>();
        for (Disease disease : diseases) {
            String diseaseName = disease.getName();
            List<Symptom> symptoms = databaseHelper.getDiseaseSymptoms(disease.getId());

            // Check if disease name matches the query and if the disease contains all selected symptoms
            if ((diseaseName != null && diseaseName.toLowerCase().contains(query.toLowerCase())) &&
                    containsSelectedSymptoms(symptoms)) {
                filteredDiseases.add(disease);
            }

        }




        return filteredDiseases;
    }

    private boolean containsSelectedSymptoms(List<Symptom> symptoms) {
        for (String selectedSymptom : selectedSymptoms) {
            boolean found = false;
            for (Symptom symptom : symptoms) {
                if (symptom.getSymptomName().equals(selectedSymptom)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;  // If any selected symptom is not found, return false
        }
        return true;  // Only return true if all selected symptoms are found
    }


    private boolean containsSymptom(List<Symptom> symptoms, String query) {
        for (Symptom symptom : symptoms) {
            String symptomName = symptom.getSymptomName();
            if (symptomName != null && symptomName.toLowerCase().contains(query)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            // handle search icon click
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}