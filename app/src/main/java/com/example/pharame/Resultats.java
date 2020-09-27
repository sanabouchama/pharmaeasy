package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultats extends AppCompatActivity {
    Toolbar toolbar;
    Button confbtn;
    TextView daa,vaa,nfla,reli,poche,prepa,prepadate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);


        daa=findViewById(R.id.daatx);
        vaa=findViewById(R.id.vaatx);
        nfla=findViewById(R.id.nbrftx);
        reli= findViewById(R.id.relitx);
        poche=findViewById(R.id.pdstx);
        prepa=findViewById(R.id.prepaid);
        prepadate=findViewById(R.id.prepadate);

        confbtn = findViewById(R.id.confirmerbtn);
        confbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));


            }
        });

        Intent inte = getIntent();
        final  String daaa = inte.getStringExtra("daa");
        final String vaaa= inte.getStringExtra("vaa");
        final String nflcc= inte.getStringExtra("nfla");
        final String relii= inte.getStringExtra("reli");
        final String pochee= inte.getStringExtra("poche");
        final  String prepaid=inte.getStringExtra("prepaid");
        final  String prepdate = inte.getStringExtra("prepadate");

        daa.setText(daaa);
        vaa.setText(vaaa);
        nfla.setText(nflcc);
        reli.setText(relii);
        poche.setText(pochee);
        prepa.setText("N°"+prepaid);
        prepadate.setText(prepdate);



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.close:
                Intent intent = new Intent(Resultats.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }
}
