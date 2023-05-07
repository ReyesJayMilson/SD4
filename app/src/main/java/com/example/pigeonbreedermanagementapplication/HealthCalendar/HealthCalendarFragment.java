package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;


public class HealthCalendarFragment extends Fragment {

    private int profileId = GlobalVariables.profileId;
    private RecyclerView hcalendarRecView;
    public static HCPigeonsRecViewAdapter hcalendaradapter;
    private DatabaseHelper dbhelper;
    private ArrayList<PigeonsGetSet> pigeons = new ArrayList<>();
    private ArrayList<HCalendarGetSet> hcalendar = new ArrayList<>();


    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_calender, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        hcalendarRecView = view.findViewById(R.id.rc_HCalendar);

        // to pass the context to the databasehelper
        pigeons = dbhelper.getEveryPigeon(profileId);

        hcalendaradapter = new HCPigeonsRecViewAdapter(view.getContext());

        hcalendaradapter.setPigeons(pigeons);

        hcalendarRecView.setAdapter(hcalendaradapter);

        hcalendarRecView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        setHasOptionsMenu(true);

        return view;
    }
}