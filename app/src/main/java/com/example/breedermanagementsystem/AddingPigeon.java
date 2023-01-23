package com.example.breedermanagementsystem;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddingPigeon extends AppCompatActivity {

    Button btSave;
    EditText etRingID, etName, etBreed, etColor, etNotes;
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

        setContentView(R.layout.add_pigeon);

        dbhelper = new DatabaseHelper(AddingPigeon.this);

        String ringId = getIntent().getStringExtra("ring_id");
        String name = getIntent().getStringExtra("name");
        int birthYear = getIntent().getIntExtra("birth_year", 0);
        String breed = getIntent().getStringExtra("breed");
        String gender = getIntent().getStringExtra("gender");
        String color = getIntent().getStringExtra("color");
        String status = getIntent().getStringExtra("status");
        String notes = getIntent().getStringExtra("notes");

        boolean isFromEditButton = getIntent().getBooleanExtra("isFromEditButton", false);
        if (isFromEditButton) {
            etRingID.setText(ringId);
            etName.setText(name);
            // set the other EditText fields here as well
        }


        btSave = findViewById(R.id.bt_Save);
        etRingID = findViewById(R.id.et_RingID);
        etName = findViewById(R.id.et_Name);
        spBirthYear = findViewById(R.id.sp_BirthYear);
        etBreed = findViewById(R.id.et_Breed);
        etColor = findViewById(R.id.et_Color);
        spGender = findViewById(R.id.sp_Gender);
        etNotes = findViewById(R.id.et_Notes);
        rgStatus = findViewById(R.id.rg_Status);

        //adding the resources to the birthyear
        List<Integer> years = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for (int i = currentYear; i > currentYear - 40; i--) {
            years.add(i);
        }

        ArrayAdapter<Integer> yearsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBirthYear.setAdapter(yearsAdapter);

        spBirthYear.setPrompt("Birth Year");
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


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexYear = spBirthYear.getSelectedItemPosition();
                selectedYear = years.get(indexYear);
                indexGender = spGender.getSelectedItemPosition();
                selectedGender = Listgender.get(indexGender);
                selectedStatusID = rgStatus.getCheckedRadioButtonId();
                rbStatus = findViewById(selectedStatusID);
                selectedStatus = rbStatus.getText().toString();
                GetSetPigeons pigeons = new GetSetPigeons(etRingID.getText().toString(), etName.getText().toString(), selectedYear, etBreed.getText().toString(), selectedGender, etColor.getText().toString(), selectedStatus, etNotes.getText().toString());

                boolean success = dbhelper.addPigeon(pigeons);

                if (success) {
                    Toast.makeText(AddingPigeon.this, "Pigeon added", Toast.LENGTH_SHORT).show();
//                    int position = MyPigeonsFragment.pigeons.size();
//                    MyPigeonsFragment.pigeons.add(pigeons);
//                    MyPigeonsFragment.adapter.notifyItemInserted(position);
                    finish();
                } else {
                    Toast.makeText(AddingPigeon.this, "Pigeon not added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
