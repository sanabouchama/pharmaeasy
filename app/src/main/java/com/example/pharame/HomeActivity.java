package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import Data.MyAppDataBase;
import model.Medicine;
import model.Patient;
import model.Preparation;
import model.Remainder;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button calculbtn;

    MyAppDataBase dataBase;
    List<Preparation> preparationList = new ArrayList<>();
    List<Medicine> medicineList = new ArrayList<>();
    List<Patient> patientList = new ArrayList<>();
    List<Remainder> reminderList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* ---------------hooks-------------------*/
        drawerLayout = findViewById(R.id.draw_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        calculbtn = findViewById(R.id.btn_calcul);
        /* ---------------Tool Bar-------------------*/
        setSupportActionBar(toolbar);

        /* ---------------Navigation Drawer Menu-------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_medlist:
                        startActivity(new Intent(getApplicationContext(), MedList.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_history:
                        startActivity(new Intent(getApplicationContext(), PreparationList.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });


        calculbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Calculation.class));
            }
        });


        dataBase = MyAppDataBase.creatmydbInstance(this);
        preparationList = dataBase.getDao().getAllpreparation();
        patientList = dataBase.getDao().getAllpatient();
        medicineList = dataBase.getDao().getAllmed();
        reminderList=dataBase.getDao().getAllremainder();

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void showWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(HomeActivity.this).inflate(
                R.layout.warning,
                (ConstraintLayout) findViewById(R.id.layout_warning)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText((getResources().getString(R.string.attention)));
        ((TextView) view.findViewById(R.id.textMessage)).setText((getResources().getString(R.string.dummy_text)));
        ((Button) view.findViewById(R.id.Btnyes)).setText((getResources().getString(R.string.yes)));
        ((Button) view.findViewById(R.id.BtnNo)).setText((getResources().getString(R.string.no)));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.Btnyes).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               dataBase.getDao().resetremainder(reminderList);
                dataBase.getDao().resetpreparation(preparationList);
                preparationList.clear();
                preparationList.addAll(dataBase.getDao().getAllpreparation());
                dataBase.getDao().resetpatient(patientList);
                patientList.clear();
                patientList.addAll(dataBase.getDao().getAllpatient());
                dataBase.getDao().resetmed(medicineList);
                medicineList.clear();
                medicineList.addAll(dataBase.getDao().getAllmed());
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(HomeActivity.this, "les Données sont supprimée", Toast.LENGTH_SHORT).show();

            }
        });
        view.findViewById(R.id.BtnNo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Renidesdonnées:
                showWarning();
                break;

            case R.id.logout:
                finish();
                Intent in = new Intent(getApplicationContext(), SingInActivity.class);
                startActivity(in);
                break;

            case R.id.updatepassword:
                Intent intent = getIntent();
                String user = intent.getStringExtra("password");
                Intent intent1 = new Intent(HomeActivity.this, ChangePassword.class);
                intent1.putExtra("passwordd", user);
                startActivity(intent1);
                finish();
                break;
            case R.id.reminder:
                Intent intent2 = new Intent(HomeActivity.this, Reminder.class);
                startActivity(intent2);
                finish();
                break;
        }


        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addbtn:
                Intent intent = new Intent(HomeActivity.this, AddMed.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }


}