package com.example.pigeonbreedermanagementapplication.Pigeon;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PigeonEditing extends AppCompatActivity {

    Button btSave;
    EditText etRingID, etName, etBreed, etColor, etNotes, etCageNumber;
    Spinner spBirthYear, spGender;
    int selectedYear, selectedStatusID;
    RadioGroup rgStatus;
    RadioButton rbStatus;
    String selectedStatus, selectedGender;
    int indexYear, indexGender;
    DatabaseHelper dbhelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pigeon_add);


        dbhelper = new DatabaseHelper(PigeonEditing.this);

        String ringId = getIntent().getStringExtra("ring_id");
        String name = getIntent().getStringExtra("name");
        int cageNumber = getIntent().getIntExtra("cage_number", 0);
        int birthYear = getIntent().getIntExtra("birth_year", 0);
        String breed = getIntent().getStringExtra("breed");
        String gender = getIntent().getStringExtra("gender");
        String color = getIntent().getStringExtra("color");
        String status = getIntent().getStringExtra("status");
        String notes = getIntent().getStringExtra("notes");


        btSave = findViewById(R.id.bt_Save);
        etRingID = findViewById(R.id.et_RingID);
        etName = findViewById(R.id.et_Name);etCageNumber = findViewById(R.id.et_CageNo);
        spBirthYear = findViewById(R.id.sp_BirthYear);
        etBreed = findViewById(R.id.sp_Breed);
        spGender = findViewById(R.id.sp_Gender);
        etColor = findViewById(R.id.et_Color);
        rgStatus = findViewById(R.id.rg_Status);
        etNotes = findViewById(R.id.et_Notes);


        //adding the resources to the birthyear
        List<Integer> Listyears = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for (int i = currentYear; i > currentYear - 40; i--) {
            Listyears.add(i);
        }

        ArrayAdapter<Integer> yearsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Listyears);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBirthYear.setAdapter(yearsAdapter);

//        spBirthYear.setSelection(0);

        //adding the resources to the gender
        List<String> Listgender = new ArrayList<>();
        Listgender.add("Male");
        Listgender.add("Female");
        Listgender.add("Young");

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Listgender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(genderAdapter);
//        spGender.setSelection(0);


        etRingID.setVisibility(View.GONE);
        etName.setText(name);
        etCageNumber.setText(Integer.toString(cageNumber));
        spBirthYear.setSelection(((ArrayAdapter<Integer>) spBirthYear.getAdapter()).getPosition(birthYear));
        etBreed.setText(breed);
        spGender.setSelection(((ArrayAdapter<String>) spGender.getAdapter()).getPosition(gender));
        etColor.setText(color);
        switch (status) {
            case "Alive":
                rgStatus.check(R.id.rb_Alive);
                break;
            case "Dead":
                rgStatus.check(R.id.rb_Dead);
                break;
            case "Sold":
                rgStatus.check(R.id.rb_Sold);
                break;
        }
        etNotes.setText(notes);


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCageNumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(PigeonEditing.this, "Cage Number cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                indexYear = spBirthYear.getSelectedItemPosition();
                selectedYear = Listyears.get(indexYear);
                indexGender = spGender.getSelectedItemPosition();
                selectedGender = Listgender.get(indexGender);
                selectedStatusID = rgStatus.getCheckedRadioButtonId();
                rbStatus = findViewById(selectedStatusID);
                selectedStatus = rbStatus.getText().toString();
                PigeonsGetSet pigeons = new PigeonsGetSet(ringId, etName.getText().toString(), Integer.parseInt(etCageNumber.getText().toString()), selectedYear, etBreed.getText().toString(), selectedGender, etColor.getText().toString(), selectedStatus, etNotes.getText().toString());

                boolean success = dbhelper.editPigeon(pigeons);

                if (success) {
                    Toast.makeText(PigeonEditing.this, "Pigeon saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PigeonEditing.this, "Pigeon not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
