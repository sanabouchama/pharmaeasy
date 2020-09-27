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

import Data.MyAppDataBase;
import Data.MyDao;


public class AddMed extends AppCompatActivity {
    Toolbar toolbar;
    Button continuebtn;
    private MyDao myDao;
    EditText Medname, Medlabo, Medvlm, Medci, Medcmin, Medcmax, Medprix;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_med);

        myDao = MyAppDataBase.creatmydbInstance(this).getDao();


        final EditText Mednamee = findViewById(R.id.Medname);
        final EditText Medlaboo = findViewById(R.id.Medlabo);
        final EditText Medvlmm = findViewById(R.id.MedVolume);
        final EditText Medcii = findViewById(R.id.MedCI);
        final EditText Medcminn = findViewById(R.id.Medcmin);
        final EditText Medcmaxx = findViewById(R.id.Medcmax);
        final EditText Medprixx = findViewById(R.id.Medprix);


        Medname = findViewById(R.id.Medname);
        Medlabo = findViewById(R.id.Medlabo);
        Medvlm = findViewById(R.id.MedVolume);
        Medci = findViewById(R.id.MedCI);
        Medcmin = findViewById(R.id.Medcmin);
        Medcmax = findViewById(R.id.Medcmax);
        Medprix = findViewById(R.id.Medprix);


        continuebtn = findViewById(R.id.continuebtn);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isEmpty()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                String medname = Mednamee.getText().toString();
                String medlabo = Medlaboo.getText().toString();
                float medvlm = Float.parseFloat(Medvlmm.getText().toString());
                float medci = Float.parseFloat(Medcii.getText().toString());
                float medcmin = Float.parseFloat(Medcminn.getText().toString());
                float medcmax = Float.parseFloat(Medcmaxx.getText().toString());
                float medprix = Float.parseFloat(Medprixx.getText().toString());


                Intent inte = new Intent(AddMed.this, addmedsuit.class);
                inte.putExtra("medname", medname);
                inte.putExtra("medlabo", medlabo);
                inte.putExtra("medvlm", medvlm);
                inte.putExtra("medci", medci);
                inte.putExtra("medcmin", medcmin);
                inte.putExtra("medcmax", medcmax);
                inte.putExtra("medprix", medprix);
                startActivity(inte);



                finish();
                        }
                    }, 1000);
                }else {
                    Toast.makeText(AddMed.this, "Champ vide ", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(getApplicationContext(), MedList.class));


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

                        case R.id.nav_history:
                        startActivity(new Intent(getApplicationContext(),PreparationList.class));
                        overridePendingTransition(0,0);
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
                Intent intent = new Intent(AddMed.this, MedList.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }

    public void onBackPressed() {

        Intent intent9 = new Intent(AddMed.this, HomeActivity.class);
        startActivity(intent9);
        finish();

    }


    private boolean isEmpty() {
        if ((TextUtils.isEmpty(Medname.getText().toString()))||(TextUtils.isEmpty(Medlabo.getText().toString()))||
                (TextUtils.isEmpty(Medvlm.getText().toString()))||(TextUtils.isEmpty(Medci.getText().toString()))||
                (TextUtils.isEmpty(Medcmin.getText().toString()))||(TextUtils.isEmpty(Medcmax.getText().toString())) ||
                (TextUtils.isEmpty(Medprix.getText().toString()))) {
            return true;
        } else {
            return false;
        }
    }



}