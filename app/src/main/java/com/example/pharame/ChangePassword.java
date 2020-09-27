package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
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
import model.User;

public class ChangePassword extends AppCompatActivity {
    private static final String TAG = "khra";
    private static final String TAG2 = "khra";
    Toolbar toolbar;
    EditText oldpswd, newpswd, confpswd;
    private MyDao myDao;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        myDao = MyAppDataBase.creatmydbInstance(this).getDao();

        toolbar = findViewById(R.id.toolbar10);
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


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
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
                        startActivity(new Intent(getApplicationContext(), PreparationList.class));
                        overridePendingTransition(0, 0);
                        break;
                }


                return false;
            }
        });


        oldpswd = findViewById(R.id.oldpsw);
        newpswd = findViewById(R.id.newpsw);
        confpswd = findViewById(R.id.confnewpsw);
        button = findViewById(R.id.buttonconf);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String oldpswrd = oldpswd.getText().toString().trim();
                final String newpwrd = newpswd.getText().toString().trim();
                final String confpswrd = confpswd.getText().toString().trim();

                if (!isEmpty()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (newpwrd.equals(confpswrd)) {
                                Intent intent = getIntent();
                               String user = intent.getStringExtra("passwordd");
                                Log.d(TAG, "run: "+user);
                                Log.d(TAG2, "run: "+oldpswrd);
                                if (oldpswrd.equals(user)){
                                    myDao.updatepassword(oldpswrd, newpwrd);
                                    Intent intent1 = new Intent(ChangePassword.this, HomeActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }else
                                {
                                    Toast.makeText(ChangePassword.this, "Erreur dans l'ancient mot de passe", Toast.LENGTH_SHORT).show();

                                }

                            }else{
                                Toast.makeText(ChangePassword.this, "Mot de passe ne corresspondant pas", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, 1000);
                } else {
                    Toast.makeText(ChangePassword.this, "Champ vide ", Toast.LENGTH_SHORT).show();
                }




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
                Intent intent = new Intent(ChangePassword.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }

    public void onBackPressed() {

        Intent intent9 = new Intent(ChangePassword.this, HomeActivity.class);
        startActivity(intent9);
        finish();

    }

    private boolean isEmpty() {
        if ((TextUtils.isEmpty(oldpswd.getText().toString())) || (TextUtils.isEmpty(newpswd.getText().toString())) ||
                (TextUtils.isEmpty(confpswd.getText().toString()))) {
            return true;
        } else {
            return false;
        }
    }
}
