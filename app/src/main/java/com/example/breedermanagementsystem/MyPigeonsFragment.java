package com.example.breedermanagementsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyPigeonsFragment extends Fragment {

    private RecyclerView pigeonsRecView;
    private Button addPigeon;
    DatabaseHelper dbhelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_pigeons, container, false);
        dbhelper = new DatabaseHelper(view.getContext());
        pigeonsRecView = view.findViewById(R.id.rc_Pigeons);

        ArrayList<GetSetPigeons> pigeons = new ArrayList<>();
        pigeons.add(new GetSetPigeons("1a2", "examplename", 2015, "examplebreed", "examplecolor", "male", "alive","examplenotes"));
        pigeons.add(new GetSetPigeons("20a", "examplename2", 2015, "examplebreed2", "examplecolor2", "male", "dead","examplenotes2"));

        PigeonsRecViewAdapter adapter = new PigeonsRecViewAdapter(view.getContext());
        adapter.setPigeons(pigeons);

        pigeonsRecView.setAdapter((adapter));

        pigeonsRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        addPigeon = view.findViewById(R.id.bt_AddPigeon);
        addPigeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be executed when the button is clicked
                Intent intent = new Intent(getContext(), AddingPigeon.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}