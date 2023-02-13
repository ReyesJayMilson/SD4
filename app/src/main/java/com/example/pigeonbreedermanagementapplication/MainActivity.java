package com.example.pigeonbreedermanagementapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.Profile.ProfilesGetSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAddProfile, btnSaveProfile, btnCancelProfile;
    ListView lvProfileList;
    EditText etProfileName;
    TextView tvTitle, tvSelectBreed;
    ArrayAdapter<String> userProfileAA;
    DatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        dbhelper = new DatabaseHelper(MainActivity.this);

        tvTitle = findViewById(R.id.tv_Title);
        tvSelectBreed = findViewById(R.id.tv_SelectBreed);
        lvProfileList = findViewById(R.id.lv_ProfileList);
        btnAddProfile = findViewById(R.id.btn_AddProfile);

        tvSelectBreed.setText("Choose a Profile");
        tvTitle.setText("Pigeon Breeder Management System");
        ShowProfileList();

        //selecting a profile
        lvProfileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userprofilename = parent.getItemAtPosition(position).toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selected_profile", userprofilename);
                editor.apply();
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
//                intent.putExtra("item", userprofilename);
                startActivity(intent);
//                MainActivity.this.finish();

            }
        });


        btnAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add New Profile");

                View dialogView = getLayoutInflater().inflate(R.layout.profiles, null);

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
                        ProfilesGetSet profiles;
                        profiles = new ProfilesGetSet(-1, etProfileName.getText().toString());
                        //Save the profile name to a database or file


                        boolean success = dbhelper.addProfile((profiles));

                        if (success) {
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
        List<String> profileNames = new ArrayList<>();
        List<ProfilesGetSet> profiles = dbhelper.getEveryProfile();
        for (ProfilesGetSet profile : profiles) {
            profileNames.add(profile.getName());
        }
        userProfileAA = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, profileNames);
        lvProfileList.setAdapter(userProfileAA);
    }
    //selecting a profile
}