package com.example.pigeonbreedermanagementapplication.Pigeon;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PigeonEditing extends AppCompatActivity {

    Button btSave, btAddCage;
    ImageView ivAddImage;
    EditText etRingID, etName, etColor, etNotes, etCageNumber;
    Spinner spBirthYear, spGender, spBreed, spCageNo;
    int selectedYear, selectedStatusID,selectedCageNo;
    RadioGroup rgStatus;
    RadioButton rbStatus;
    String selectedStatus, selectedGender, selectedBreed;
    int indexYear, indexGender, indexBreed, indexCageNo;
    byte[] pigeonImage;
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
        byte[] image = getIntent().getByteArrayExtra("image");


        ivAddImage = findViewById(R.id.iv_AddImage);
        btAddCage = findViewById(R.id.bt_AddCage);
        btSave = findViewById(R.id.bt_Save);
        etRingID = findViewById(R.id.et_RingID);
        etName = findViewById(R.id.et_Name);
        spCageNo = findViewById(R.id.sp_CageNo);
//        etCageNumber = findViewById(R.id.et_CageNo);
        spBirthYear = findViewById(R.id.sp_BirthYear);
        spBreed = findViewById(R.id.sp_Breed);
        etColor = findViewById(R.id.et_Color);
        spGender = findViewById(R.id.sp_Gender);
        etNotes = findViewById(R.id.et_Notes);
        rgStatus = findViewById(R.id.rg_Status);


        ivAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

                AlertDialog.Builder builder = new AlertDialog.Builder(PigeonEditing.this);
                builder.setTitle("Add Photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);
                        } else if (options[item].equals("Choose from Gallery")) {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, 1);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        //adding to cageNo
        List<Integer> cageNumbers = dbhelper.getAllCageNumbers();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cageNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCageNo.setAdapter(adapter);

        btAddCage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbhelper.addCageNumber();
                if (success) {
                    Toast.makeText(PigeonEditing.this, "Cage Number added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PigeonEditing.this, "Cage Number not added", Toast.LENGTH_SHORT).show();
                }
                List<Integer> cageNumbers = dbhelper.getAllCageNumbers();
                ArrayAdapter<Integer> adapter = new ArrayAdapter<>(PigeonEditing.this, android.R.layout.simple_spinner_item, cageNumbers);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter.notifyDataSetChanged();
                spCageNo.setAdapter(adapter);
            }
        });


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


        List<String> breeds = new ArrayList<>();
        breeds.add("Blue Bar");
        breeds.add("Grizzle");
        breeds.add("Brown");
        breeds.add("Others...");

        ArrayAdapter<String> breedsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, breeds);
        breedsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBreed.setAdapter(breedsAdapter);
        spBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBreed = breeds.get(position);
                if (selectedBreed.equals("Others...")) {
                    // Show a dialog to prompt the user for input
                    AlertDialog.Builder builder = new AlertDialog.Builder(PigeonEditing.this);
                    builder.setTitle("Enter Breed");

                    final EditText input = new EditText(PigeonEditing.this);
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String newBreed = input.getText().toString();
                            // Add the new breed to the list of breeds
                            breeds.add(breeds.size() - 1, newBreed);
                            // Notify the adapter that the data has changed
                            breedsAdapter.notifyDataSetChanged();
                            // Set the selected item to the newly entered breed
                            spBreed.setSelection(breeds.indexOf(newBreed));
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        etRingID.setVisibility(View.GONE);
        rgStatus.setVisibility(View.VISIBLE);
        etName.setText(name);
        spCageNo.setSelection(((ArrayAdapter<Integer>) spCageNo.getAdapter()).getPosition(cageNumber));
        spBirthYear.setSelection(((ArrayAdapter<Integer>) spBirthYear.getAdapter()).getPosition(birthYear));
        spBreed.setSelection(((ArrayAdapter<String>) spBreed.getAdapter()).getPosition(breed));
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
                indexCageNo = spCageNo.getSelectedItemPosition();
                selectedCageNo = cageNumbers.get(indexCageNo);
                indexYear = spBirthYear.getSelectedItemPosition();
                selectedYear = years.get(indexYear);
                indexBreed = spBreed.getSelectedItemPosition();
                selectedBreed = breeds.get(indexBreed);
                indexGender = spGender.getSelectedItemPosition();
                selectedGender = Listgender.get(indexGender);
                selectedStatusID = rgStatus.getCheckedRadioButtonId();
                rbStatus = findViewById(selectedStatusID);
                selectedStatus = rbStatus.getText().toString();
                PigeonsGetSet pigeons = new PigeonsGetSet(etRingID.getText().toString(), etName.getText().toString(), selectedCageNo, selectedYear, selectedBreed, selectedGender, etColor.getText().toString(), selectedStatus, etNotes.getText().toString(), pigeonImage);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        byte[] imageInByte = byteArrayOutputStream.toByteArray();
                        pigeonImage = imageInByte;
                        // You can now store imageInByte in your database as a BLOB
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                            byte[] imageInByte = byteArrayOutputStream.toByteArray();
                            pigeonImage = imageInByte;

                            // You can now store imageInByte in your database as a BLOB
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }


}

