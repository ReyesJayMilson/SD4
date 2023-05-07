package com.example.pigeonbreedermanagementapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pigeonbreedermanagementapplication.Egg.EggTrackerFragment;
import com.example.pigeonbreedermanagementapplication.HealthCalendar.HealthCalendarFragment;
import com.example.pigeonbreedermanagementapplication.Home.HomeFragment;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsFragment;
import com.example.pigeonbreedermanagementapplication.Product.ProductFragment;
import com.example.pigeonbreedermanagementapplication.Transaction.TransactionFragment;
import com.google.android.material.navigation.NavigationView;


public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().show();
        setContentView(R.layout.navigationactivity);
//        String username = getIntent().getStringExtra("item");
//        MainActivity.this.finish();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PigeonsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_mypigeons);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                setTitle("Home");
                break;

            case R.id.nav_mypigeons:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PigeonsFragment()).commit();
                setTitle("My Pigeons");
                break;

            case R.id.nav_healthcalendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HealthCalendarFragment()).commit();
                setTitle("Health Calendar");
                break;

            case R.id.nav_commondiseaselib:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CommonDiseaseLibFragment()).commit();
                setTitle("Common Disease Library");
                break;

            case R.id.nav_eggtracker:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EggTrackerFragment()).commit();
                setTitle("Egg Monitoring");
                break;

            case R.id.nav_transactions:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TransactionFragment()).commit();
                setTitle("Transactions");
                break;

            case R.id.nav_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductFragment()).commit();
                setTitle("Products");
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }
}
