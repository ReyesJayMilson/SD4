package com.example.pigeonbreedermanagementapplication.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsRecViewAdapter;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private int profileId = GlobalVariables.profileId;
    private DatabaseHelper dbhelper;
    private TextView banner, nestTitle, nestDesc;

    @Override
    public void onResume() {
        super.onResume();
        String pigeon_count = String.valueOf(dbhelper.getTotalPigeons(profileId));
        banner.setText("Welcome to Pigeon Breeder Management. You currently have " + pigeon_count +
                " pigeons in your flock. Keep track of your breeding progress and elevate your " +
                "pigeon breeding process with Pigeon Breeder Management.");    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        banner = view.findViewById(R.id.idplaceholder_banner);
        nestTitle = view.findViewById(R.id.idplaceholder_hatchedeggs);
        nestDesc = view.findViewById(R.id.idplaceholder_nestdescr);
        dbhelper = new DatabaseHelper(getActivity());

        displayDashboad();


        // Inflate the layout for this fragment
        return view;
    }

    private void displayDashboad() {
        String pigeon_count = String.valueOf(dbhelper.getTotalPigeons(profileId));
        banner.setText("Welcome to Pigeon Breeder Management. You currently have " + pigeon_count +
                " pigeons in your flock. Keep track of your breeding progress and elevate your " +
                "pigeon breeding process with Pigeon Breeder Management.");
        nestTitle.setText("Nest Statistics");

        String hatchcount = String.valueOf(dbhelper.getHatchedEggCount(profileId));
        nestDesc.setText("Successfully Hatched Eggs: " + hatchcount);
    }
}