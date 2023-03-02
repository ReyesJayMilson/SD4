package com.example.pigeonbreedermanagementapplication.Pigeon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
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
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class PigeonAdding extends AppCompatActivity {

    private int profileId = GlobalVariables.profileId;
    private Button btSave;
    private Button btAddCage;
    private ImageView ivAddImage;
    private EditText etRingID;
    private EditText etName;
    private EditText etColor;
    private EditText etNotes;
    private Spinner spBirthYear;
    private Spinner spGender;
    private Spinner spBreed;
    private Spinner spCageNo;
    private RadioGroup rgStatus;
    private RadioButton rbStatus;
    private int selectedYear;
    private int selectedStatusID;
    private int selectedCageNo;
    private String selectedStatus;
    private String selectedGender;
    private String selectedBreed;
    private String filePath;
    private DatabaseHelper dbhelper;
    private List<Integer> cageNumbers;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pigeon_add);

        dbhelper = new DatabaseHelper(PigeonAdding.this);

//        String ringId = getIntent().getStringExtra("ring_id");
//        String name = getIntent().getStringExtra("name");
//
//        boolean isFromEditButton = getIntent().getBooleanExtra("isFromEditButton", false);
//        if (isFromEditButton) {
//            etRingID.setText(ringId);
//            etName.setText(name);
//            // set the other EditText fields here as well
//        }
        ivAddImage = findViewById(R.id.iv_AddImage);
        btAddCage = findViewById(R.id.bt_AddCage);
        btSave = findViewById(R.id.bt_Save);
        etRingID = findViewById(R.id.et_RingID);
        etName = findViewById(R.id.et_Name);
        spCageNo = findViewById(R.id.sp_CageNo);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(PigeonAdding.this);
                builder.setTitle("Add Photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);
                        } else if (options[item].equals("Choose from Gallery")) {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
        cageNumbers = Collections.singletonList(dbhelper.getAllCageNumbers(profileId).size());
        ArrayAdapter<Integer> cageadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cageNumbers);
        cageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCageNo.setAdapter(cageadapter);

        btAddCage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbhelper.addCageNumber(profileId);
                if (success) {
                    Toast.makeText(PigeonAdding.this, "Cage Number added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PigeonAdding.this, "Cage Number not added", Toast.LENGTH_SHORT).show();
                }
                cageNumbers = dbhelper.getAllCageNumbers(profileId);
                ArrayAdapter<Integer> cageadapter = new ArrayAdapter<>(PigeonAdding.this, android.R.layout.simple_spinner_item, cageNumbers);
                cageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cageadapter.notifyDataSetChanged();
                spCageNo.setAdapter(cageadapter);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(PigeonAdding.this);
                    builder.setTitle("Enter Breed");

                    final EditText input = new EditText(PigeonAdding.this);
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


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etRingID.getText().toString().trim().isEmpty()) {
                    Toast.makeText(PigeonAdding.this, "Ring ID cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (etCageNumber.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(PigeonAdding.this, "Cage Number cannot be empty", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                selectedStatusID = rgStatus.getCheckedRadioButtonId();
                rbStatus = findViewById(selectedStatusID);
                selectedStatus = rbStatus.getText().toString();

                selectedGender = spGender.getSelectedItem().toString();
                selectedBreed = spBreed.getSelectedItem().toString();
                selectedYear = Integer.parseInt(spBirthYear.getSelectedItem().toString());
                selectedCageNo = Integer.parseInt(spCageNo.getSelectedItem().toString());

                String ringId = etRingID.getText().toString();
                String name = etName.getText().toString();
                String color = etColor.getText().toString();
                String notes = etNotes.getText().toString();



//                if (ringId.equals("")){
//                    Toast.makeText(PigeonAdding.this, "Ring ID cannot be empty.", Toast.LENGTH_SHORT).show();
//                }
//                else {
                    if (filePath != null) {
                        filePath = saveImageToInternalStorage(imageBitmap, ringId);
                    }
                    PigeonsGetSet pigeons = new PigeonsGetSet(ringId, name, selectedCageNo, selectedYear, selectedBreed, selectedGender, color, selectedStatus, notes, filePath, profileId);

                int result = dbhelper.addPigeon(pigeons);
                switch (result) {
                    case 0:
                        Toast.makeText(PigeonAdding.this, "Failed to add pigeon", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(PigeonAdding.this, "Pigeon added successfully", Toast.LENGTH_SHORT).show();
                        ArrayList<PigeonsGetSet> updatedList = dbhelper.getEveryPigeon(profileId);
                        PigeonsFragment.pigeonadapter.notifyDataSetChanged();
                        break;
                    case 2:
                        Toast.makeText(PigeonAdding.this, "Ring ID already exists", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(PigeonAdding.this, "Ring ID cannot be empty", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

                }
//            }
        });
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                // for taking a photo
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                ivAddImage.setImageBitmap(imageBitmap);
            } else if (requestCode == 1) {
                // for selecting an image from the gallery
                Uri selectedImage = data.getData();
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    ivAddImage.setImageBitmap(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //save image to local storage
    private String saveImageToInternalStorage (Bitmap imageBitmap, String imageName){
        File directory = this.getDir("imageDir", Context.MODE_PRIVATE);
        File myPath = new File(directory, imageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myPath.getAbsolutePath();
    }
}
