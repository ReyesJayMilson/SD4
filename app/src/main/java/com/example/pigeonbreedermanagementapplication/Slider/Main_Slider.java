package com.example.pigeonbreedermanagementapplication.Slider;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.pigeonbreedermanagementapplication.R;
import com.google.android.material.slider.Slider;

public class Main_Slider extends AppCompatActivity {

    ViewPager viewPager;
    Slider_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newly_installed_screen);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager = findViewById(R.id.viewpager);
        adapter = new Slider_Adapter(this);
        viewPager.setAdapter(adapter);
    }
}
