package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Data.MyAppDataBase;
import model.Preparation;
import model.Remainder;

public class Rappellefj extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    Button button;
    LinearLayoutManager linearLayoutManager;
    MyAppDataBase dataBase;
    ExpiredAdapterfj adapter;

    List<Preparation> preparationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rappellefj);
        toolbar = findViewById(R.id.toolbar4);
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


        Date date = Calendar.getInstance().getTime();
        dataBase = MyAppDataBase.creatmydbInstance(this);
        List<Remainder> reminderList = dataBase.getDao().perimmed(date);
        recyclerView = findViewById(R.id.recycler_view4);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ExpiredAdapterfj(Rappellefj.this, reminderList);
        recyclerView.setAdapter(adapter);

        preparationList = dataBase.getDao().getAllpreparation();
        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.getDao().resetpreparation(preparationList);
                preparationList.clear();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
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
                Intent intent = new Intent(Rappellefj.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }

    public void onBackPressed() {

        Intent intent5 = new Intent(Rappellefj.this, HomeActivity.class);
        startActivity(intent5);
        finish();

    }
}
