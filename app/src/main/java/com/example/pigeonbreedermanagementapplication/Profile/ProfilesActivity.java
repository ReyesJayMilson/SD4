package com.example.pigeonbreedermanagementapplication.Profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.NavigationActivity;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonAdding;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsRecViewAdapter;
import com.example.pigeonbreedermanagementapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilesActivity extends AppCompatActivity {

    private RecyclerView profilesRecView;
    public static ProfilesRecViewAdapter profileadapter;
    private DatabaseHelper dbhelper;
    private ArrayList<ProfilesGetSet> profiles = new ArrayList<>();
    private Button addProfile;

    private Bitmap imageBitmap;
    private String filePath;
    private ImageView ivProfileImage;
    private TextView etProfileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sharedPreferences = getSharedPreferences("user_preference", MODE_PRIVATE);
        dbhelper = new DatabaseHelper(ProfilesActivity.this);

        addProfile = findViewById(R.id.bt_AddProfile);

        profilesRecView = findViewById(R.id.rc_Profiles);

        // to pass the context to the databasehelper
        profiles = dbhelper.getEveryProfile();


        profileadapter = new ProfilesRecViewAdapter(this);

        profileadapter.setProfiles(profiles);

        profilesRecView.setAdapter(profileadapter);

        profilesRecView.setLayoutManager(new LinearLayoutManager(this));


        //adding a new profile
        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProfilesActivity", "Add Profile button clicked");


                AlertDialog.Builder builder = new AlertDialog.Builder(ProfilesActivity.this);
                builder.setTitle("Add New Profile");

                View dialogView = getLayoutInflater().inflate(R.layout.profiles, null);

                builder.setView(dialogView);
                etProfileName = dialogView.findViewById(R.id.et_ProfileName);
                ivProfileImage = dialogView.findViewById(R.id.iv_displayProfile);

                ivProfileImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfilesActivity.this);
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

                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String profileName = etProfileName.getText().toString();
                        if (profileName.isEmpty()){
                            etProfileName.setError("Profile name cannot be empty");
                         return;
                        } else {
                            String filePath = null;
                            if (imageBitmap != null) {
                                filePath = saveImageToInternalStorage(imageBitmap, etProfileName.getText().toString());
                            }
                            ProfilesGetSet addprofiles = new ProfilesGetSet(-1, etProfileName.getText().toString(), filePath);

                            boolean success = dbhelper.addProfile(addprofiles);

                            if (success) {
                                ArrayList<ProfilesGetSet> updatedList = dbhelper.getEveryProfile();
                                profileadapter.setProfiles(updatedList);

                                Toast.makeText(ProfilesActivity.this, "Profile added successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProfilesActivity.this, "Profile not added", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();

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
                ivProfileImage.setImageBitmap(imageBitmap);
            } else if (requestCode == 1) {
                // for selecting an image from the gallery
                Uri selectedImage = data.getData();
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    ivProfileImage.setImageBitmap(imageBitmap);
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