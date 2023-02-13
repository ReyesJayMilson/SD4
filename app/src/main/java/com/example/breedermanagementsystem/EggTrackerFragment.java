package com.example.breedermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class EggTrackerFragment extends Fragment {

    private RecyclerView eggRecView;
    static EggsRecViewAdapter adapter;
    private DatabaseHelper dbhelper;
    private ArrayList<EggsGetSet> eggs = new ArrayList<>();
    private Button addEgg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_egg_tracker, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        eggRecView = view.findViewById(R.id.rc_Eggs);

        // to pass the context to the databasehelper


        eggs = dbhelper.getEveryEgg();

        adapter = new EggsRecViewAdapter(view.getContext());
        adapter.setEggs(eggs);

        eggRecView.setAdapter(adapter);

        eggRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 5));
        addEgg = view.findViewById(R.id.bt_AddEgg);
        addEgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be executed when the button is clicked
                Intent intent = new Intent(getContext(), EggAdding.class);
                startActivity(intent);


            }
        });

        return view;
    }


}