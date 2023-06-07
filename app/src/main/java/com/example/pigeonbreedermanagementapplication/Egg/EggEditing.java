package com.example.pigeonbreedermanagementapplication.Egg;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsFragment;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class EggEditing extends AppCompatActivity {

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
    private ArrayList<EggsGetSet> eggs = new ArrayList<>();
    private File myPath;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pigeon_add);


        dbhelper = new DatabaseHelper(EggEditing.this);

        String ringId = getIntent().getStringExtra("ring_id");
        String name = getIntent().getStringExtra("name");
        int cageNumber = getIntent().getIntExtra("cage_number", 0);
        int birthYear = getIntent().getIntExtra("birth_year", 0);
        String breed = getIntent().getStringExtra("breed");
        String gender = getIntent().getStringExtra("gender");
        String color = getIntent().getStringExtra("color");
        String status = getIntent().getStringExtra("status");
        String notes = getIntent().getStringExtra("notes");
        String image = getIntent().getStringExtra("image");
        Log.d("Image String", "file path" + image);


        ivAddImage = findViewById(R.id.iv_AddImage);
        btAddCage = findViewById(R.id.bt_AddCage);
        btSave = findViewById(R.id.bt_Save);
        etRingID = findViewById(R.id.et_RingID);

        etName = findViewById(R.id.et_Name);
        SpannableString spannableString_etName = new SpannableString("Name");
        // Create a ForegroundColorSpan with the desired color
        ForegroundColorSpan colorSpan_etName = new ForegroundColorSpan(Color.parseColor("#ebe8e8"));
        // Apply the color span to the spannable string
        spannableString_etName.setSpan(colorSpan_etName, 0, spannableString_etName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable string as the hint for the EditText
        etName.setHint(spannableString_etName);

        spCageNo = findViewById(R.id.sp_CageNo);
//        etCageNumber = findViewById(R.id.et_CageNo);
        spBirthYear = findViewById(R.id.sp_BirthYear);
        spBreed = findViewById(R.id.sp_Breed);

        etColor = findViewById(R.id.et_Color);
        etColor = findViewById(R.id.et_Color);
        SpannableString spannableString_etColor = new SpannableString("Color");
        // Create a ForegroundColorSpan with the desired color
        ForegroundColorSpan colorSpan_etColor = new ForegroundColorSpan(Color.parseColor("#ebe8e8"));
        // Apply the color span to the spannable string
        spannableString_etColor.setSpan(colorSpan_etColor, 0, spannableString_etColor.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable string as the hint for the EditText
        etColor.setHint(spannableString_etColor);

        spGender = findViewById(R.id.sp_Gender);

        etNotes = findViewById(R.id.et_Notes);
        SpannableString spannableString_etNotes = new SpannableString("Notes");
        // Create a ForegroundColorSpan with the desired color
        ForegroundColorSpan colorSpan_etNotes = new ForegroundColorSpan(Color.parseColor("#ebe8e8"));
        // Apply the color span to the spannable string
        spannableString_etNotes.setSpan(colorSpan_etNotes, 0, spannableString_etNotes.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable string as the hint for the EditText
        etNotes.setHint(spannableString_etNotes);

        rgStatus = findViewById(R.id.rg_Status);


        ivAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

                AlertDialog.Builder builder = new AlertDialog.Builder(EggEditing.this);
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
        List<Integer> cageNumbers = dbhelper.getAllCageNumbers(profileId);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, R.layout.spinner_adapter, cageNumbers);
        adapter.setDropDownViewResource(R.layout.spinner_adapter_dropdown);
        spCageNo.setAdapter(adapter);

        btAddCage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbhelper.addCageNumber(profileId);
                if (success) {
                    Toast.makeText(EggEditing.this, "Cage Number added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EggEditing.this, "Cage Number not added", Toast.LENGTH_SHORT).show();
                }
                List<Integer> cageNumbers = dbhelper.getAllCageNumbers(profileId);
                ArrayAdapter<Integer> adapter = new ArrayAdapter<>(EggEditing.this, R.layout.spinner_adapter, cageNumbers);
                adapter.setDropDownViewResource(R.layout.spinner_adapter_dropdown);
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

        ArrayAdapter<Integer> yearsAdapter = new ArrayAdapter<>(this, R.layout.spinner_adapter, years);
        yearsAdapter.setDropDownViewResource(R.layout.spinner_adapter_dropdown);
        spBirthYear.setAdapter(yearsAdapter);

        spBirthYear.setPrompt("Birth Year");
//        spBirthYear.setSelection(0);



        //adding the resources to the gender
        List<String> Listgender = new ArrayList<>();
        Listgender.add("Male");
        Listgender.add("Female");
        Listgender.add("Young");

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.spinner_adapter, Listgender);
        genderAdapter.setDropDownViewResource(R.layout.spinner_adapter_dropdown);
        spGender.setAdapter(genderAdapter);
//        spGender.setSelection(0);


        List<String> breeds = new ArrayList<>();
        breeds.add("Blue Bar");
        breeds.add("Grizzle");
        breeds.add("Brown");
        breeds.add("Others...");

        ArrayAdapter<String> breedsAdapter = new ArrayAdapter<>(this, R.layout.spinner_adapter, breeds);
        breedsAdapter.setDropDownViewResource(R.layout.spinner_adapter_dropdown);
        spBreed.setAdapter(breedsAdapter);
        spBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBreed = breeds.get(position);
                if (selectedBreed.equals("Others...")) {
                    // Show a dialog to prompt the user for input
                    AlertDialog.Builder builder = new AlertDialog.Builder(EggEditing.this);
                    builder.setTitle("Enter Breed");

                    final EditText input = new EditText(EggEditing.this);
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



        //setting the existing fields
        if (image != null) {
            ivAddImage.setImageBitmap(BitmapFactory.decodeFile(image));
        }
        etRingID.setVisibility(View.GONE);
        rgStatus.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.BELOW, etNotes.getId());
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        rgStatus.setLayoutParams(params);
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
                selectedStatusID = rgStatus.getCheckedRadioButtonId();
                rbStatus = findViewById(selectedStatusID);
                selectedStatus = rbStatus.getText().toString();

                selectedGender = spGender.getSelectedItem().toString();
                selectedBreed = spBreed.getSelectedItem().toString();
                selectedYear = Integer.parseInt(spBirthYear.getSelectedItem().toString());
                selectedCageNo = Integer.parseInt(spCageNo.getSelectedItem().toString());

                String name = etName.getText().toString();
                String color = etColor.getText().toString();
                String notes = etNotes.getText().toString();


                if (imageBitmap != null) {
                    // Check if the image string is not empty
                    if (!TextUtils.isEmpty(image)) {
                        File oldFile = new File(image);
                        if (oldFile.exists()) {
                            oldFile.delete();
                        }
                    }
                    filePath = saveImageToInternalStorage(imageBitmap, ringId);
                } else {
                    filePath = image;
                }
                    PigeonsGetSet pigeons = new PigeonsGetSet(ringId, name, selectedCageNo, selectedYear, selectedBreed, selectedGender, color, selectedStatus, notes, filePath, profileId);


                    boolean success = dbhelper.editPigeon(pigeons);

                    if (success) {
                        ArrayList<PigeonsGetSet> updatedList = dbhelper.getEveryPigeon(profileId);
                        PigeonsFragment.pigeonadapter.setPigeons(updatedList);
                        Toast.makeText(EggEditing.this, "Pigeon saved", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EggEditing.this, "Pigeon not saved", Toast.LENGTH_SHORT).show();
                    }

            }
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
        myPath = new File(directory, imageName);

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

