package com.example.pigeonbreedermanagementapplication.Pigeon;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;


public class PigeonsFragment extends Fragment {

    private RecyclerView pigeonsRecView;
    public static PigeonsRecViewAdapter pigeonadapter;
    private DatabaseHelper dbhelper;
    private ArrayList<PigeonsGetSet> pigeons = new ArrayList<>();
    private Button addPigeon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_pigeons, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        pigeonsRecView = view.findViewById(R.id.rc_Pigeons);

        // to pass the context to the databasehelper


        pigeons = dbhelper.getEveryPigeon();

        pigeonadapter = new PigeonsRecViewAdapter(view.getContext());
        pigeonadapter.setPigeons(pigeons);

        pigeonsRecView.setAdapter(pigeonadapter);

        pigeonsRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        addPigeon = view.findViewById(R.id.bt_AddPigeon);
        addPigeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be executed when the button is clicked
                Intent intent = new Intent(getContext(), PigeonAdding.class);
                startActivity(intent);


            }
        });

        return view;
    }


}