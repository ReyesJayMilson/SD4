package com.example.breedermanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyPigeonsFragment extends Fragment {

    private RecyclerView pigeonsRecView;
    static PigeonsRecViewAdapter adapter;
    private DatabaseHelper dbhelper;
    private ArrayList<GetSetPigeons> pigeons = new ArrayList<>();
    private Button addPigeon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_pigeons, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        pigeonsRecView = view.findViewById(R.id.rc_Pigeons);

        // to pass the context to the databasehelper


        pigeons = dbhelper.getEveryPigeon();

        adapter = new PigeonsRecViewAdapter(view.getContext());
        adapter.setPigeons(pigeons);

        pigeonsRecView.setAdapter(adapter);

        pigeonsRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        addPigeon = view.findViewById(R.id.bt_AddPigeon);
        addPigeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be executed when the button is clicked
                Intent intent = new Intent(getContext(), AddingPigeon.class);
                startActivity(intent);


            }
        });

        return view;
    }


}