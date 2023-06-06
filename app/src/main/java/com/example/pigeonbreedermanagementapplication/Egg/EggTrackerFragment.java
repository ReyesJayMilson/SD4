package com.example.pigeonbreedermanagementapplication.Egg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;


public class EggTrackerFragment extends Fragment {

    private int profileId = GlobalVariables.profileId;
    private RecyclerView eggRecView, eggHatchedRecView, eggUnhatchedRecView;
    public static EggsRecViewAdapter eggadapter, egghatchedadapter, eggunhatchedadapter;
    private DatabaseHelper dbhelper;
    private ArrayList<EggsGetSet> eggs = new ArrayList<>();
    private ArrayList<EggsGetSet> eggsHatched = new ArrayList<>();
    private ArrayList<EggsGetSet> eggsUnhatched = new ArrayList<>();
    private Button addEgg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_egg_tracker, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        int eggsCount = dbhelper.getEggCount(profileId);
        int hatchedEggsCount = dbhelper.getHatchedEggCount(profileId);
        int unhatchedEggsCount = dbhelper.getBotchedEggCount(profileId);
        eggRecView = view.findViewById(R.id.rc_Eggs);
        eggHatchedRecView = view.findViewById(R.id.rc_hatched);
        eggUnhatchedRecView = view.findViewById(R.id.rc_unhatched);
        CardView cvEggs = view.findViewById(R.id.cv_eggs);
        CardView cvHatched = view.findViewById(R.id.cv_hatched);
        CardView cvUnhatched = view.findViewById(R.id.cv_unhatched);
        // to pass the context to the databasehelper

        if (eggsCount > 0){
            cvEggs.setVisibility(View.VISIBLE);
        } else {
            cvEggs.setVisibility(View.GONE);
        }
        if (hatchedEggsCount > 0){
            cvHatched.setVisibility(View.VISIBLE);
        } else {
            cvHatched.setVisibility(View.GONE);
        }
        if (unhatchedEggsCount > 0){
            cvUnhatched.setVisibility(View.VISIBLE);
        } else {
            cvUnhatched.setVisibility(View.GONE);
        }

        eggs = dbhelper.getLaidEgg(profileId);
        eggsHatched = dbhelper.getHatchedEgg(profileId);
        eggsUnhatched = dbhelper.getUnhatchedEgg(profileId);
        Log.d("TAG", "Egglist" + dbhelper.getEveryEgg(profileId));
        eggadapter = new EggsRecViewAdapter(view.getContext());
        egghatchedadapter = new EggsRecViewAdapter(view.getContext());
        eggunhatchedadapter = new EggsRecViewAdapter(view.getContext());
        eggadapter.setEggs(eggs);
        egghatchedadapter.setEggs(eggsHatched);
        eggunhatchedadapter.setEggs(eggsUnhatched);

        eggRecView.setAdapter(eggadapter);
        eggHatchedRecView.setAdapter(egghatchedadapter);
        eggUnhatchedRecView.setAdapter(eggunhatchedadapter);

        eggRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 5));
        eggHatchedRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 5));
        eggUnhatchedRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 5));
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