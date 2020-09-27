package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import model.Medicine;

public class Medicineinfo extends AppCompatActivity {



    Toolbar toolbar;
    TextView name,labo,vlm,price,ci,cmin,cmax,stab,pres,rema;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicineinfo);

        name = findViewById(R.id.text_mednamev);
        labo = findViewById(R.id.text_labov);
        vlm = findViewById(R.id.text_vlmv);
        price = findViewById(R.id.text_prixv);
        ci = findViewById(R.id.text_civ);
        cmin = findViewById(R.id.text_cminv);
        cmax= findViewById(R.id.text_cmaxv);
        stab = findViewById(R.id.text_stabv);
        pres = findViewById(R.id.text_presv);



        toolbar = findViewById(R.id.toolbar2);
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



        Intent inte = getIntent();
        String medname =inte.getStringExtra("mednameinfo");
        String medlabo = inte.getStringExtra("medlaboinfo");
        String medvlm =  String.valueOf(inte.getDoubleExtra("medvlminfo",0));
        String medprice =  String.valueOf(inte.getFloatExtra("medpriceinfo",0));
        String medci =String.valueOf(inte.getFloatExtra("medciinfo",0));
        String medcmin =  String.valueOf(inte.getFloatExtra("medcmininfo",0));
        String medcmax=  String.valueOf(inte.getFloatExtra("medcmaxinfo",0));
        String medstab =inte.getStringExtra("medstabinfo");
        String medpre =inte.getStringExtra("medpresinfo");





        name.setText(medname);
        labo.setText(medlabo);
        vlm.setText(medvlm);
        price.setText(medprice);
        ci.setText(medci);
        cmin.setText(medcmin);
        cmax.setText(medcmax);
        stab.setText(medstab);
        pres.setText(medpre);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.close:
                Intent intent = new Intent(Medicineinfo.this, MedList.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }
}
