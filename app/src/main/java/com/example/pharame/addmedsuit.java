package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.Date;
import java.util.List;


import Data.MyAppDataBase;
import Data.MyDao;
import model.Medicine;
import model.Remainder;


public class addmedsuit extends AppCompatActivity {
    Toolbar toolbar;
    Button addnewmed, addpre;
    EditText Mednamee, Medlaboo, Medvlmm, Medcii, Medcminn, Medcmaxx, Medprixx, Medstabb, Medpree;
    private MyDao myDao;
    List<Medicine> medicineList;
    MyAppDataBase dataBase;
    MyAdapter adapter;
    MyAdapter.OnMedListener onMedListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmedsuit);

        Mednamee = findViewById(R.id.Medname);
        Medlaboo = findViewById(R.id.Medlabo);
        Medvlmm = findViewById(R.id.MedVolume);
        Medcii = findViewById(R.id.MedCI);
        Medcminn = findViewById(R.id.Medcmin);
        Medcmaxx = findViewById(R.id.Medcmax);
        Medprixx = findViewById(R.id.Medprix);
        Medstabb = findViewById(R.id.Medstab);
        Medpree = findViewById(R.id.Medpre);


        myDao = MyAppDataBase.creatmydbInstance(this).getDao();


        addnewmed = findViewById(R.id.adding);
        addnewmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent inte = getIntent();


                            final String mednamee = inte.getStringExtra("medname");
                            final String medlaboo = inte.getStringExtra("medlabo");
                            final float medvlm = inte.getFloatExtra("medvlm", 0);
                            final float medci = inte.getFloatExtra("medci", 0);
                            final float medcmin = inte.getFloatExtra("medcmin", 0);
                            final float medcmax = inte.getFloatExtra("medcmax", 0);
                            final float medprix = inte.getFloatExtra("medprix", 0);


                            final String medpre = Medpree.getText().toString().trim();
                            final String medstab = Medstabb.getText().toString();


                            Intent i = getIntent();
                            medicineList = (List<Medicine>) i.getSerializableExtra("LIST");
                            adapter = new MyAdapter(addmedsuit.this, medicineList, onMedListener);
                            Medicine medicine = new Medicine(mednamee, medlaboo, medpre, medci, medcmin, medcmax, medprix, medvlm, medstab);
                            long mid = myDao.addmed(medicine);
                            Remainder remainder= new Remainder(mid,0.0,null,null);
                            myDao.addrem(remainder);
                            adapter.notifyDataSetChanged();
                            Intent inte1 = new Intent(addmedsuit.this, MedList.class);
                            startActivity(inte1);
                            Toast.makeText(addmedsuit.this, "Médicament ajouté", Toast.LENGTH_SHORT).show();
                        }
                    }, 1000);

                } else {
                    Toast.makeText(addmedsuit.this, "Champ vide ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddMed.class));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_medlist);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);

                        break;
                    case R.id.nav_medlist:
                        startActivity(new Intent(getApplicationContext(), MedList.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.close:
                Intent intent = new Intent(addmedsuit.this, MedList.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }

    public void onBackPressed() {

        Intent intent10 = new Intent(addmedsuit.this, AddMed.class);
        startActivity(intent10);
        finish();

    }

    private boolean isEmpty() {
        if ((TextUtils.isEmpty(Medpree.getText().toString())) || (TextUtils.isEmpty(Medstabb.getText().toString()))) {
            return true;
        } else {
            return false;
        }
    }


}

