package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import model.Preparation;

public class PreparationInfo extends AppCompatActivity {
Toolbar toolbar;
TextView nomtv,pretv,poidtv,tailletv,sctv,daatv,vaatv;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation_info);
        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PreparationList.class));


            }
        });







        nomtv=findViewById(R.id.text_patnamev);
        pretv=findViewById(R.id.patprev);
        poidtv=findViewById(R.id.poidv);
        tailletv=findViewById(R.id.pattaillev);
        sctv= findViewById(R.id.patscv);
        daatv=findViewById(R.id.text_daav);
        vaatv=findViewById(R.id.text_vaav);




        Intent inte = getIntent();
        String nom=inte.getStringExtra("nom");
        String pre = inte.getStringExtra("pre");
        String poid = inte.getStringExtra("weight");
        String taille =inte.getStringExtra("height");
        String sc = inte.getStringExtra("bs");
        String daa= inte.getStringExtra("daa");
        String vaa = inte.getStringExtra("vaa");




        nomtv.setText(nom);
        pretv.setText(pre);
        poidtv.setText(poid);
        tailletv.setText(taille);
        sctv.setText(sc);
        daatv.setText(daa);
        vaatv.setText(vaa);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.close:
                Intent intent = new Intent(PreparationInfo.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }
    public void onBackPressed() {

        Intent intent5 = new Intent(PreparationInfo.this, PreparationList.class);
        startActivity(intent5);
        finish();

    }

    }


