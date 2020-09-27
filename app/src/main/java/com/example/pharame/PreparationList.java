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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Data.MyAppDataBase;
import model.Preparation;

public class PreparationList extends AppCompatActivity {
    Toolbar toolbar;
    TextView tv;
    List<Preparation> preparationList = new ArrayList<>();
    MyAppDataBase dataBase;
    PrepaAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation_list);

        toolbar=findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));


            }

        });

        tv=findViewById(R.id.textView6);
        Date datte = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String  datii = df.format(datte);
        tv.setText(datii);

        recyclerView= findViewById(R.id.recycler_view2);
        dataBase = MyAppDataBase.creatmydbInstance(this);
        preparationList = dataBase.getDao().getAllpreparation();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PrepaAdapter(this,preparationList);
        recyclerView.setAdapter(adapter);




        Intent intent = new Intent("INTENT_NAME").putExtra("LIST2", (Serializable) preparationList);
        LocalBroadcastManager.getInstance(PreparationList.this).sendBroadcast(intent);




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.close:
                Intent intent = new Intent(PreparationList.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }
    public void onBackPressed() {

        Intent intent5 = new Intent(PreparationList.this, HomeActivity.class);
        startActivity(intent5);
        finish();

    }
    }





