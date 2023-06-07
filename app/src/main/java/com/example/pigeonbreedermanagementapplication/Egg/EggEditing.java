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
    private Button btAddCage, btAddNest;
    private ImageView ivAddImage;
    private EditText etRingID;
    private EditText etName;
    private EditText etColor;
    private EditText etNotes;
    private Spinner spLayDate, spHatchDate;
    private Spinner spGender;
    private Spinner spBreed;
    private Spinner spCageNo, spNestNo, spStatus, spFather, spMother;
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
        setContentView(R.layout.egg_add);


        dbhelper = new DatabaseHelper(EggEditing.this);

        String eggId = getIntent().getStringExtra("egg_id");
        int cageNumber = getIntent().getIntExtra("cage_number", 0);
        int nestNumber = getIntent().getIntExtra("nest_number", 0);
        int layDate = getIntent().getIntExtra("laying_date", 0);
        int hatchDate = getIntent().getIntExtra("hatching_date", 0);
        String status = getIntent().getStringExtra("egg_status");
        String father = getIntent().getStringExtra("father");
        String mother = getIntent().getStringExtra("mother");




        btAddCage = findViewById(R.id.bt_AddCage);
        btAddNest = findViewById(R.id.bt_AddNest);
        btSave = findViewById(R.id.bt_Save);


        spCageNo = findViewById(R.id.sp_CageNo);
        spNestNo = findViewById(R.id.sp_NestNo);
//        etCageNumber = findViewById(R.id.et_CageNo);



        spStatus= findViewById(R.id.sp_Status);

        spFather = findViewById(R.id.sp_Father);
        spMother = findViewById(R.id.sp_Mother);


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

        btAddNest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbhelper.addNestNumber(profileId);
                if (success) {
                    Toast.makeText(EggEditing.this, "Nest Number added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EggEditing.this, "NestNumber not added", Toast.LENGTH_SHORT).show();
                }
                List<Integer> nestNumbers = dbhelper.getAllNestNumbers(profileId);
                ArrayAdapter<Integer> adapter = new ArrayAdapter<>(EggEditing.this, R.layout.spinner_adapter, nestNumbers);
                adapter.setDropDownViewResource(R.layout.spinner_adapter_dropdown);
                adapter.notifyDataSetChanged();
                spNestNo.setAdapter(adapter);
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
        spLayDate.setAdapter(yearsAdapter);

        spLayDate.setPrompt("Laying Date");
//        spBirthYear.setSelection(0);



        //adding the resources to the gender
        List<String> Status = new ArrayList<>();
        Status.add("Hatched");
        Status.add("Unhatched");


        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, R.layout.spinner_adapter, Status);
        statusAdapter.setDropDownViewResource(R.layout.spinner_adapter_dropdown);
        spStatus.setAdapter(statusAdapter);
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
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.BELOW, etNotes.getId());
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        spCageNo.setSelection(((ArrayAdapter<Integer>) spCageNo.getAdapter()).getPosition(cageNumber));
        spNestNo.setSelection(((ArrayAdapter<Integer>) spNestNo.getAdapter()).getPosition(nestNumber));

        spStatus.setSelection(((ArrayAdapter<String>) spStatus.getAdapter()).getPosition(status));


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedStatusID = rgStatus.getCheckedRadioButtonId();
                rbStatus = findViewById(selectedStatusID);
                selectedStatus = rbStatus.getText().toString();

                selectedGender = spGender.getSelectedItem().toString();
                selectedBreed = spBreed.getSelectedItem().toString();
                selectedCageNo = Integer.parseInt(spCageNo.getSelectedItem().toString());

                String name = etName.getText().toString();
                String color = etColor.getText().toString();
                String notes = etNotes.getText().toString();

//
//                    EggsGetSet eggs = new EggsGetSet(egg_Id, cage_number, nest_number, laying_date, hatching_date, egg_status, father, mother, profileId);
//
//
//                    boolean success = dbhelper.editPigeon(pigeons);

//                    if (success) {
//                        ArrayList<PigeonsGetSet> updatedList = dbhelper.getEveryPigeon(profileId);
//                        PigeonsFragment.pigeonadapter.setPigeons(updatedList);
//                        Toast.makeText(EggEditing.this, "Pigeon saved", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        Toast.makeText(EggEditing.this, "Pigeon not saved", Toast.LENGTH_SHORT).show();
//                    }

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

