package com.example.pigeonbreedermanagementapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CommonDiseaseLibFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Disease> diseaseList;
    private DiseaseAdapter adapter;

    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_disease_lib, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        diseaseList = databaseHelper.getAllDisease();

        recyclerView = view.findViewById(R.id.Recycler_Library);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DiseaseAdapter(diseaseList);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }
}