package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
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
import java.util.HashSet;
import java.util.Set;

public class HCalendarActivity extends AppCompatActivity implements HCalendarAdapter.OnItemListener{

    private int profileId = GlobalVariables.profileId;
    DatabaseHelper dbhelper;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private String selectedDisease, selectedSymptom;
    private Set<String> selectedSymptoms = new HashSet<>();
    private String ringId;
    private RadioButton rbStatus;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = new DatabaseHelper(this);
        setContentView(R.layout.hcalendar_calendarview);
        initWidgets();
        selectedDate = LocalDate.now();
        ringId = getIntent().getStringExtra("ring_id");
        dbhelper = new DatabaseHelper(this);
        setMonthView();
    }


    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.rc_CalendarView);
        monthYearText = findViewById(R.id.tv_monthYear);

    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        HCalendarAdapter calendarAdapter = new HCalendarAdapter(dbhelper, daysInMonth, this, ringId, monthYearFromDate(selectedDate));
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

    private int getDiseaseId(String diseaseName) {
        return dbhelper.getDiseaseIdByName(diseaseName);
    }
    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            // Inflate the custom layout
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.hcalendar_health_record_dialog, null);

            // Calculate the actual date from the clicked item
            LocalDate clickedDate = selectedDate.withDayOfMonth(Integer.parseInt(dayText));

            // Get references to the views in the layout
//            TextView dateTextView = dialogView.findViewById(R.id.dateTextView);
            final TextView symptomsListTextView = dialogView.findViewById(R.id.symptomslistTextView);
            final EditText detailsEditText = dialogView.findViewById(R.id.detailsEditText);
            final EditText medicationsEditText = dialogView.findViewById(R.id.medicationsEditText);
            final RadioGroup healthRadioGroup = dialogView.findViewById(R.id.healthRadioGroup);
            final Spinner symptomsSpinner = dialogView.findViewById(R.id.symptomsSpinner);
            final Spinner diseasesSpinner = dialogView.findViewById(R.id.diseaseSpinner);

            ArrayList<String> symptomNames = dbhelper.getEverySymptomNames();
            ArrayList<String> diseaseNames = dbhelper.getEveryDiseaseNames();
            symptomNames.add(0, "Select Symptom");

            // Create an ArrayAdapter using the symptom and disease names
            ArrayAdapter<String> symptomsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, symptomNames);
            ArrayAdapter<String> diseasesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diseaseNames);


            // Set the ArrayAdapter for the Spinner
            symptomsSpinner.setAdapter(symptomsAdapter);
            diseasesSpinner.setAdapter(diseasesAdapter);

            symptomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        // do nothing
                        return;
                    }
                    String selectedSymptom = parent.getItemAtPosition(position).toString();
                    if (!selectedSymptoms.contains(selectedSymptom)) {
                        selectedSymptoms.add(selectedSymptom);
                        symptomsListTextView.setVisibility(View.VISIBLE);
                        updateSelectedSymptomsTextView(symptomsListTextView);

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            symptomsListTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] symptomsArray = selectedSymptoms.toArray(new String[0]);
                    AlertDialog.Builder builder = new AlertDialog.Builder(HCalendarActivity.this);
                    builder.setTitle("Remove Symptom")
                            .setItems(symptomsArray, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String selectedSymptom = symptomsArray[which];
                                    selectedSymptoms.remove(selectedSymptom);
                                    updateSelectedSymptomsTextView(symptomsListTextView);
                                    if (selectedSymptoms.isEmpty()) {
                                        symptomsListTextView.setVisibility(View.GONE);
                                    }
                                }
                            });
                    builder.create().show();
                }
            });



            // Set the onCheckedChangeListener for the RadioGroup
            healthRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radioHasSymptoms) {
                        symptomsSpinner.setVisibility(View.VISIBLE);
                        symptomsListTextView.setVisibility(View.VISIBLE);
                    } else {
                        symptomsSpinner.setVisibility(View.GONE);
                        symptomsListTextView.setVisibility(View.GONE);
                    }

                    if (checkedId == R.id.radioContractedDisease) {
                        diseasesSpinner.setVisibility(View.VISIBLE);
                    } else {
                        diseasesSpinner.setVisibility(View.GONE);
                    }

                }
            });




            // Create an AlertDialog builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set the custom view for the builder
            builder.setView(dialogView);

            // Set the title
            builder.setTitle(formalFormatDate(clickedDate));

            // Add a positive button and set its click listener
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    int diseaseId=0;
                    // Get the selected radio button ID from the RadioGroup
                    int selectedRadioButtonId = healthRadioGroup.getCheckedRadioButtonId();
                    rbStatus = dialogView.findViewById(selectedRadioButtonId);
                    String selectedStatus = rbStatus.getText().toString();

                    // Get the selected spinner values
                    String selectedSymptomsString = "";
                    String selectedDisease = "";
                    if (selectedRadioButtonId == R.id.radioHasSymptoms) {
                        selectedSymptomsString = getSelectedSymptomsString();
                    } else if (selectedRadioButtonId == R.id.radioContractedDisease) {
                        selectedDisease = diseasesSpinner.getSelectedItem().toString();
                        diseaseId = getDiseaseId(selectedDisease);

                    }


                    // Get the details and medications values from the corresponding EditText views
                    String details = detailsEditText.getText().toString();
                    String medications = medicationsEditText.getText().toString();
                    // Handle OK button click
                    HCalendarGetSet health = new HCalendarGetSet(-1, standardFormatDate(clickedDate), ringId, details, selectedStatus, medications, selectedSymptomsString, diseaseId, profileId);
                    boolean success = dbhelper.addHealth(health);

                    if (success) {
                        ArrayList<HCalendarGetSet> updatedList = dbhelper.getEveryHealth(profileId);
//                        HealthCalendarFragment.hcalendaradapter.setProducts(updatedList);
                        Toast.makeText(HCalendarActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HCalendarActivity.this, "Note not added", Toast.LENGTH_SHORT).show();
                    }
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

    private void updateSelectedSymptomsTextView(TextView symptomsList) {
        StringBuilder sb = new StringBuilder("Selected Symptoms:\n");
        for (String symptom : selectedSymptoms) {
            sb.append(symptom).append("\n");
        }
        symptomsList.setText(sb.toString());
    }

    private String getSelectedSymptomsString() {
        StringBuilder sb = new StringBuilder();
        for (String symptom : selectedSymptoms) {
            sb.append(symptom).append(", ");
        }
        // Remove the trailing comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

}
