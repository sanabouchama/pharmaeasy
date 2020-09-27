package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Data.MyAppDataBase;
import Data.MyDao;
import model.Medicine;
import model.Remainder;


public class MedList extends AppCompatActivity implements MyAdapter.OnMedListener {

    Toolbar toolbar;

    private MyAppDataBase dataBase;
    private MyDao myDao;
    MyAdapter adapter;
    RecyclerView recyclerView;
    List<Medicine> medicineList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_list2);


        recyclerView = findViewById(R.id.recycler_view);
        dataBase = MyAppDataBase.creatmydbInstance(this);
        medicineList = dataBase.getDao().getAllmed();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(MedList.this, medicineList, this);
        recyclerView.setAdapter(adapter);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });





    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addbtn:
                Intent intent = new Intent(MedList.this, AddMed.class);
                intent.putExtra("LIST", (Serializable) medicineList);
                startActivity(intent);
                finish();
                break;


            case  R.id.searchbtn:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
                break;

        }
        return true;
    }


    public void onBackPressed() {

        Intent intent5 = new Intent(MedList.this, HomeActivity.class);
        startActivity(intent5);
        finish();

    }

    @Override
    public void onMedClick(int position) {

        Intent intent = new Intent(this, Medicineinfo.class);
        intent.putExtra("mednameinfo", medicineList.get(position).getMedname());
        intent.putExtra("medlaboinfo", medicineList.get(position).getLabo());
        intent.putExtra("medvlminfo", medicineList.get(position).getVlm());
        intent.putExtra("medpriceinfo", medicineList.get(position).getPrice());
        intent.putExtra("medciinfo",medicineList.get(position).getCi());
        intent.putExtra("medcmininfo", medicineList.get(position).getCmin());
        intent.putExtra("medcmaxinfo", medicineList.get(position).getCmax());
        intent.putExtra("medstabinfo", medicineList.get(position).getStab());
        intent.putExtra("medpresinfo", medicineList.get(position).getPres());

        startActivity(intent);
    }
}