package com.example.pigeonbreedermanagementapplication.Egg;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonAdding;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class EggAdding extends AppCompatActivity {

    private int profileId = GlobalVariables.profileId;
    private Button btSave, btAddCage, btAddNest;
    private EditText etLayDate, etHatchDate, etQuantity;
    private Spinner spMother, spFather, spCageNo, spNestNo;
    private int selectedMotherID, selectedFatherID;
    private int selectedCageNo, selectedNestNo;
    private String selectedFather, selectedMother;
    private int indexFather, indexMother;
    private  DatabaseHelper dbhelper;
    private List<Integer> cageNumbers;
    private List<Integer> nestNumbers;

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

        btAddCage = findViewById(R.id.bt_AddCage);
        btAddNest = findViewById(R.id.bt_AddNest);
        btAddCage = findViewById(R.id.bt_AddCage);
        btSave = findViewById(R.id.bt_Save);
        spCageNo = findViewById(R.id.sp_CageNo);
        spNestNo = findViewById(R.id.sp_NestNo);
        etLayDate = findViewById(R.id.et_LayDate);
        etQuantity = findViewById(R.id.et_Quantity);
        spFather = findViewById(R.id.sp_Father);
        spMother = findViewById(R.id.sp_Mother);

        //adding to cageNo
        cageNumbers = dbhelper.getAllCageNumbers(profileId);
        ArrayAdapter<Integer> cageadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cageNumbers);
        cageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCageNo.setAdapter(cageadapter);

        btAddCage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbhelper.addCageNumber(profileId);
                if (success) {
                    Toast.makeText(EggAdding.this, "Cage Number added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EggAdding.this, "Cage Number not added", Toast.LENGTH_SHORT).show();
                }
                cageNumbers = dbhelper.getAllCageNumbers(profileId);
                ArrayAdapter<Integer> cageadapter = new ArrayAdapter<>(EggAdding.this, android.R.layout.simple_spinner_item, cageNumbers);
                cageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cageadapter.notifyDataSetChanged();
                spCageNo.setAdapter(cageadapter);
            }
        });

        //adding to nestNo
        nestNumbers = dbhelper.getAllNestNumbers(profileId);
        ArrayAdapter<Integer> nestadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nestNumbers);
        nestadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNestNo.setAdapter(nestadapter);

        btAddNest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbhelper.addNestNumber(profileId);
                if (success) {
                    Toast.makeText(EggAdding.this, "Cage Number added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EggAdding.this, "Cage Number not added", Toast.LENGTH_SHORT).show();
                }
                nestNumbers = dbhelper.getAllNestNumbers(profileId);
                ArrayAdapter<Integer> nestadapter = new ArrayAdapter<>(EggAdding.this, android.R.layout.simple_spinner_item, cageNumbers);
                nestadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                nestadapter.notifyDataSetChanged();
                spNestNo.setAdapter(nestadapter);
            }
        });

        etLayDate.setFocusable(false);
        etLayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(etLayDate);
            }
        });
        //adding the resources to the birthyear
        List<String> ringIds = dbhelper.getAllRingIds(profileId);
        ArrayAdapter<String> ringIdsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ringIds);
        ringIdsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFather.setAdapter(ringIdsAdapter);
        spMother.setAdapter(ringIdsAdapter);


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCageNo = Integer.parseInt(spCageNo.getSelectedItem().toString());
                selectedNestNo = Integer.parseInt(spNestNo.getSelectedItem().toString());

                String laydate = etLayDate.getText().toString();
                String hatchdate = etLayDate.getText().toString();

                selectedFather = spFather.getSelectedItem().toString();
                selectedMother = spMother.getSelectedItem().toString();

                int quantity = Integer.parseInt(etQuantity.getText().toString());
                for (int i = 0; i < quantity; i++) {
                    EggsGetSet eggs = new EggsGetSet(-1, selectedCageNo, selectedNestNo, laydate, hatchdate, selectedFather, selectedMother, profileId);

                    dbhelper.addEgg(eggs);
                                ArrayList<EggsGetSet> updatedList = dbhelper.getEveryEgg(profileId);
                                EggTrackerFragment.eggadapter.setEggs(updatedList);
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
