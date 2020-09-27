package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Data.MyAppDataBase;
import model.Remainder;

public class Rappelledj extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    MyAppDataBase dataBase;
    ExpiredAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rappelledj);

        toolbar = findViewById(R.id.toolbar3);
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
        recyclerView = findViewById(R.id.recycler_view3);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ExpiredAdapter(Rappelledj.this, reminderList);
        recyclerView.setAdapter(adapter);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.close:
                Intent intent = new Intent(Rappelledj.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }

    public void onBackPressed() {

        Intent intent5 = new Intent(Rappelledj.this, HomeActivity.class);
        startActivity(intent5);
        finish();

    }
}
