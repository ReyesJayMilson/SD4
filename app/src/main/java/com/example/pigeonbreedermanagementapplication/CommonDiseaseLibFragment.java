package com.example.pigeonbreedermanagementapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommonDiseaseLibFragment extends Fragment {
    private RecyclerView recyclerView;

    private Spinner spinSymptom;
    ArrayList<Disease> diseaseList;
    List<Symptom> symptomList;
    private DiseaseAdapter adapter;

    private DatabaseHelper databaseHelper;

    private SearchView searchView;

    private ImageView imageView;

    private SymptomCheckboxAdapter symptomCheckboxAdapter;

    private static final String SYMPTOMS_TABLE = "SYMPTOMS_TABLE";
    private static final String COLUMN_SYMP_NAME = "SYMPTOM_NAME";

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true); // enable the fragment to contribute items to the options menu

        View view = inflater.inflate(R.layout.fragment_common_disease_lib, container, false);

        spinSymptom = view.findViewById(R.id.spinSymptom);

        databaseHelper = new DatabaseHelper(getActivity());
        diseaseList = databaseHelper.getAllDisease();

        // Fetch all the symptom_name from SymptomsTable and ignore duplicates
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT DISTINCT " + COLUMN_SYMP_NAME + " FROM " + SYMPTOMS_TABLE, null);
        List<String> symptomName = new ArrayList<>();
        while (cursor.moveToNext()) {
            symptomName.add(cursor.getString(cursor.getColumnIndex(COLUMN_SYMP_NAME)));
        }
        cursor.close();

        // Add the symptom names to the spinner
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, symptomNames);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SymptomCheckboxAdapter adapter2 = new SymptomCheckboxAdapter(getActivity(), R.layout.spinner_item_checkbox, symptomName);
        spinSymptom.setAdapter(adapter2);


        recyclerView = view.findViewById(R.id.Recycler_Library);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DiseaseAdapter(diseaseList);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;


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
        // returns a filtered list based on the search query
        ArrayList<Disease> filteredDiseases = new ArrayList<>();
        for (Disease disease : diseases) {
            if (disease.getName() != null && disease.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredDiseases.add(disease);
            }
        }
        return filteredDiseases;
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