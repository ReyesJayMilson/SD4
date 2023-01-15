package com.example.breedermanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button btnAddProfile, btnSaveProfile, btnCancelProfile;
    ListView lvProfileList;
    EditText etProfileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProfileName = findViewById(R.id.et_ProfileName);
        btnAddProfile = findViewById(R.id.btn_AddProfile);

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

                        //Save the profile name to a database or file
                    }

                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}