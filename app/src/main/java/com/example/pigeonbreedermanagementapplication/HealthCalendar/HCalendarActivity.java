package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;
import com.example.pigeonbreedermanagementapplication.Symptom;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HCalendarActivity extends AppCompatActivity implements HCalendarAdapter.OnItemListener{

    private int profileId = GlobalVariables.profileId;
    DatabaseHelper dbhelper;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private String selectedDisease, selectedSymptom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = new DatabaseHelper(this);
        setContentView(R.layout.hcalendar_calendarview);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }


    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.rc_CalendarView);
        monthYearText = findViewById(R.id.tv_monthYear);

    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        HCalendarAdapter calendarAdapter = new HCalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i =1 ; i <= 42; i++) {
            if( i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;

    }

    private String monthYearFromDate (LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MMMM yyyy"));
        return date.format(formatter);
    }

    private String standardFormatDate (LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MM/dd/yyyy"));
        return date.format(formatter);
    }

    private String formalFormatDate (LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MMMM dd, yyyy"));
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            // Inflate the custom layout
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.hcalendar_health_record_dialog, null);

            // Get references to the views in the layout
//            TextView dateTextView = dialogView.findViewById(R.id.dateTextView);
            EditText detailsEditText = dialogView.findViewById(R.id.detailsEditText);
            EditText medicationsEditText = dialogView.findViewById(R.id.medicationsEditText);
            RadioGroup healthRadioGroup = dialogView.findViewById(R.id.healthRadioGroup);
            final Spinner symptomsSpinner = dialogView.findViewById(R.id.symptomsSpinner);
            final Spinner diseasesSpinner = dialogView.findViewById(R.id.diseaseSpinner);

            ArrayList<String> symptomNames = dbhelper.getEverySymptomNames();
            ArrayList<String> diseaseNames = dbhelper.getEveryDiseaseNames();

            // Create an ArrayAdapter using the symptom and disease names
            ArrayAdapter<String> symptomsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, symptomNames);
            ArrayAdapter<String> diseasesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diseaseNames);


            // Set the ArrayAdapter for the Spinner
            symptomsSpinner.setAdapter(symptomsAdapter);
            diseasesSpinner.setAdapter(diseasesAdapter);

            // Update the TextView with the selected date
//            dateTextView.setText("Selected Date: " + standardFormatDate(selectedDate));

            // Set the onCheckedChangeListener for the RadioGroup
            healthRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radioHasSymptoms) {
                        symptomsSpinner.setVisibility(View.VISIBLE);
                    } else {
                        symptomsSpinner.setVisibility(View.GONE);
                    }

                    if (checkedId == R.id.radioContractedDisease) {
                        diseasesSpinner.setVisibility(View.VISIBLE);
                    } else {
                        diseasesSpinner.setVisibility(View.GONE);
                    }

                }
            });

            // Get the selected radio button ID from the RadioGroup
            int selectedRadioButtonId = healthRadioGroup.getCheckedRadioButtonId();

            // Get the selected spinner values
            String selectedSymptom = "";
            String selectedDisease = "";
            if (selectedRadioButtonId == R.id.radioHasSymptoms) {
                selectedSymptom = symptomsSpinner.getSelectedItem().toString();
            } else if (selectedRadioButtonId == R.id.radioContractedDisease) {
                selectedDisease = diseasesSpinner.getSelectedItem().toString();
            }

            // Get the details and medications values from the corresponding EditText views
            String details = detailsEditText.getText().toString();
            String medications = medicationsEditText.getText().toString();

            // Create an AlertDialog builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set the custom view for the builder
            builder.setView(dialogView);

            // Set the title
            builder.setTitle(formalFormatDate(selectedDate));

            // Add a positive button and set its click listener
            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Handle OK button click

                }
            });

            // Add a negative button and set its click listener
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Handle Cancel button click
                }
            });

            // Set other properties of the dialog
            builder.setCancelable(false); // Dialog cannot be dismissed by pressing outside of it

            // Create the AlertDialog instance and show it
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public ArrayList<String> getSymptomNames(ArrayList<Symptom> symptoms) {
        ArrayList<String> symptomNames = new ArrayList<>();
        for (Symptom symptom : symptoms) {
            symptomNames.add(symptom.getSymptomName());
        }
        return symptomNames;
    }

}
