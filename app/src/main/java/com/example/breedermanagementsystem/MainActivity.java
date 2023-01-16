package com.example.breedermanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAddProfile, btnSaveProfile, btnCancelProfile;
    ListView lvProfileList;
    EditText etProfileName;
    ArrayAdapter userProfileAA;
    DatabaseHelper dbhelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new DatabaseHelper(MainActivity.this);

        lvProfileList = findViewById(R.id.lv_ProfileList);
        btnAddProfile = findViewById(R.id.btn_AddProfile);

        ShowProfileList();


        btnAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add New Profile");

                View dialogView = getLayoutInflater().inflate(R.layout.profiles,null);

                builder.setView(dialogView);
                final EditText etProfileName = dialogView.findViewById(R.id.et_ProfileName);

                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String profileName = etProfileName.getText().toString();
                        if (profileName.isEmpty()) {
                            etProfileName.setError("Profile name cannot be empty");
                            return;
                        }
                        Profiles profiles;
                        profiles = new Profiles(-1, etProfileName.getText().toString());
                        //Save the profile name to a database or file



                        boolean success = dbhelper.addOne((profiles));

                        if(success) {
                            ShowProfileList();
                            Toast.makeText(MainActivity.this, "Profile added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Profile not added", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    private void ShowProfileList() {
        userProfileAA = new ArrayAdapter<Profiles>(MainActivity.this, android.R.layout.simple_list_item_1, dbhelper.getEveryone());
        lvProfileList.setAdapter(userProfileAA);
    }


}