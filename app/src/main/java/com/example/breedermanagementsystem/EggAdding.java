package com.example.breedermanagementsystem;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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


public class EggAdding extends AppCompatActivity {

    Button btSave;
    EditText etCageNumber, etNestNumber, etLayDate, etHatchDate, etQuantity;
    Spinner spMother, spFather;
    int selectedMotherID, selectedFatherID;
    String selectedFather, selectedMother;
    int indexFather, indexMother;
    DatabaseHelper dbhelper;

    private void showDatePickerDialog(final EditText et) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                EggAdding.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Format the date and set it in the EditText
                        String date = String.format("%d/%d/%d", month + 1, day, year);
                        et.setText(date);
                    }
                },
                // Set the current date as the default date in the picker
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.eggs_add);





        dbhelper = new DatabaseHelper(EggAdding.this);

//        String ringId = getIntent().getStringExtra("ring_id");
//        String name = getIntent().getStringExtra("name");
//
//        boolean isFromEditButton = getIntent().getBooleanExtra("isFromEditButton", false);
//        if (isFromEditButton) {
//            etRingID.setText(ringId);
//            etName.setText(name);
//            // set the other EditText fields here as well
//        }


        btSave = findViewById(R.id.bt_Save);
        etCageNumber = findViewById(R.id.et_CageNo);
        etNestNumber = findViewById(R.id.et_NestNo);
        etLayDate = findViewById(R.id.et_LayDate);
        etQuantity = findViewById(R.id.et_Quantity);
        spFather = findViewById(R.id.sp_Father);
        spMother = findViewById(R.id.sp_Mother);

        etLayDate.setFocusable(false);
        etLayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(etLayDate);
            }
        });
        //adding the resources to the birthyear
        List<String> ringIds = dbhelper.getAllRingIds();
        ArrayAdapter<String> ringIdsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ringIds);
        ringIdsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFather.setAdapter(ringIdsAdapter);
        spMother.setAdapter(ringIdsAdapter);


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCageNumber.getText().toString().trim().isEmpty() || etNestNumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(EggAdding.this, "Cage Number or Nest Number cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etQuantity.getText().toString().trim().isEmpty()) {
                    Toast.makeText(EggAdding.this, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                indexFather = spFather.getSelectedItemPosition();
                selectedFather = ringIds.get(indexFather);
                indexMother = spMother.getSelectedItemPosition();
                selectedMother = ringIds.get(indexMother);

                int quantity = Integer.parseInt(etQuantity.getText().toString());
                for (int i = 0; i < quantity; i++) {
                    EggsGetSet eggs = new EggsGetSet(-1, Integer.parseInt(etCageNumber.getText().toString()), Integer.parseInt(etNestNumber.getText().toString()), etLayDate.getText().toString(), etLayDate.getText().toString(), selectedFather, selectedMother);

                    dbhelper.addEgg(eggs);
                }

                finish();
//                if (success) {
//                    Toast.makeText(PigeonAdding.this, "Pigeon added", Toast.LENGTH_SHORT).show();
////                    int position = MyPigeonsFragment.pigeons.size();
////                    MyPigeonsFragment.pigeons.add(pigeons);
////                    MyPigeonsFragment.adapter.notifyItemInserted(position);
//                    finish();
//                } else {
//                    Toast.makeText(PigeonAdding.this, "Pigeon not added", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}
