package com.example.pigeonbreedermanagementapplication.Pigeon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.Home.HomeFragment;
import com.example.pigeonbreedermanagementapplication.R;


import java.util.ArrayList;


public class PigeonsFragment extends Fragment {

    private int profileId = GlobalVariables.profileId;
    private RecyclerView pigeonsRecView;
    public static PigeonsRecViewAdapter pigeonadapter;
    private DatabaseHelper dbhelper;
    private ArrayList<PigeonsGetSet> pigeons = new ArrayList<>();
    private Button addPigeon;
    private SearchView searchView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_pigeons, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        pigeonsRecView = view.findViewById(R.id.rc_Pigeons);

        // to pass the context to the databasehelper
        pigeons = dbhelper.getEveryPigeon(profileId);

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

                View rootView = LayoutInflater.from(getContext()).inflate(R.layout.pigeon_add, null);

                // Find the button within the inflated view
                Button addButton = rootView.findViewById(R.id.bt_Save);

                // Change the text of the button to "Add Pigeon"
                addButton.setText("Add Pigeon");
            }
        });

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actions_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Pigeons");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // filter the list with the search query
                ArrayList<PigeonsGetSet> filteredPigeons = filter(pigeons, newText);
                pigeonadapter.setPigeons(filteredPigeons);
                return true;
            }
        });

    

    }

    private ArrayList<PigeonsGetSet> filter(ArrayList<PigeonsGetSet> pigeons, String query) {
        // returns a filtered list based on the search query
        ArrayList<PigeonsGetSet> filteredPigeons = new ArrayList<>();
        for (PigeonsGetSet pigeon : pigeons) {
            if (pigeon.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredPigeons.add(pigeon);
            }
            else if (pigeon.getRing_id().toLowerCase().contains(query.toLowerCase())) {
                filteredPigeons.add(pigeon);
            }
            else if (pigeon.getBreed().toLowerCase().contains(query.toLowerCase())) {
                filteredPigeons.add(pigeon);
            }
            else if (pigeon.getGender().toLowerCase().contains(query.toLowerCase())) {
                filteredPigeons.add(pigeon);
            }
            else if (pigeon.getColor().toLowerCase().contains(query.toLowerCase())) {
                filteredPigeons.add(pigeon);
            }
        }

        return filteredPigeons;
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
