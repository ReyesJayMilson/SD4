package com.example.pigeonbreedermanagementapplication.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;


public class HomeFragment extends Fragment {

    private int profileId = GlobalVariables.profileId;
    private DatabaseHelper dbhelper;
    private TextView banner, nestTitle, nestDesc, eggTitle, eggDesc, eggFailed, eggRating, eggTotal, eggCurrent;

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
        eggTitle = view.findViewById(R.id.idplaceholder_eggrate);
        eggDesc = view.findViewById(R.id.idplaceholder_eggdesc);
        eggFailed = view.findViewById(R.id.idplaceholder_failedegg);
        eggRating = view.findViewById(R.id.idplaceholder_rate);
        eggTotal = view.findViewById(R.id.idplaceholder_eggtotal);
        eggCurrent = view.findViewById(R.id.idplaceholder_eggcurrent);


        dbhelper = new DatabaseHelper(getActivity());

        String pigeon_count = String.valueOf(dbhelper.getTotalPigeons(profileId));


        banner.setText("Welcome to Pigeon Breeder Management. You currently have " + pigeon_count +
                " pigeons in your flock. Keep track of your breeding progress and elevate your " +
                "pigeon breeding process with Pigeon Breeder Management.");

        String totalcount = String.valueOf(dbhelper.getEggCount(profileId));
        eggTotal.setText("Total Eggs: \n" + totalcount);

        String laidcount = String.valueOf(dbhelper.getLaidEggCount(profileId));
        eggCurrent.setText("Current Unhatched Eggs: \n" + laidcount);

        nestTitle.setText("Hatched Eggs");

        String hatchcount = String.valueOf(dbhelper.getHatchedEggCount(profileId));
        nestDesc.setText("Successfully Hatched Eggs: " + hatchcount);

        String failcount = String.valueOf(dbhelper.getBotchedEggCount(profileId));
        eggFailed.setText("Unsuccessfully Hatched Eggs:" + failcount);

        eggTitle.setText("Success Rate");

        double hatched = dbhelper.getHatchedEggCount(profileId);
        double unhatched = dbhelper.getBotchedEggCount(profileId);
        double hatchRate = (hatched / (hatched + unhatched)) * 100;
        double hatchRateDecimal = Double.parseDouble(String.format("%.2f", hatchRate));
        eggDesc.setText("Success Rate: \n" + hatchRateDecimal + "%");

        if(hatched ==0 && unhatched ==0){
            nestDesc.setText("Successfully Hatched Eggs: \nNone Hatched Yet");
            eggFailed.setText("Unsuccessfully Hatched Eggs: \nNone Hatched Yet");
            eggRating.setText("None Hatched Yet");
        }else {
            if (hatchRateDecimal >= 90) {
                eggRating.setText("Exceptional");
                eggRating.setTextColor(Color.parseColor("#00ed01"));
            } else if (hatchRateDecimal > 70) {
                eggRating.setText("Great");
                eggRating.setTextColor(Color.YELLOW);
            } else if (hatchRateDecimal > 51) {
                eggRating.setText("Concerning");
                eggRating.setTextColor(Color.parseColor("#FFA500"));
            } else if (hatchRateDecimal > 31) {
                eggRating.setText("Poor");
                eggRating.setTextColor(Color.parseColor("#ff4500"));
            } else {
                eggRating.setText("Abysmal");
                eggRating.setTextColor(Color.RED);
            }
        }

        // Get a reference to the current success rating textview
        TextView eggRating = view.findViewById(R.id.idplaceholder_rate);

        // Set an OnClickListener on the textview to display Egg Rating Chart
        eggRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the popup layout as a view
                View popupView = getLayoutInflater().inflate(R.layout.popup_success_rating, null);

                // Create a new PopupWindow instance
                PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                Button closeButton = popupView.findViewById(R.id.closeButton);

                // Set an OnClickListener on the close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        popupWindow.dismiss();
                    }
                });

                // Show the popup window
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}